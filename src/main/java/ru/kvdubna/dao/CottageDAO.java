package ru.kvdubna.dao;

import ru.kvdubna.model.*;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
/*  DAO для коттеджей
    Отличается от дач/участков только наличием дополнительного поля в модели для площади дома.
    Соответственно в DAO отличаются только в двух вещах - добавление нового объекта и изменение объекта */
public class CottageDAO implements DAO {
    private Connection conn;
    // подробная информация по объекту недвижимости:
    private PreparedStatement objectFullInfo;
    // короткая инфа по всем имеющимся объявлениям (с фильтром аренда/продажа):
    private PreparedStatement allObjectsForSaleOrRent;
    // подробная информация по всем имеющимся объявлениям (без фильтров):
    private Statement allObjects;
    // порции короткой инфы (по более сложному фильтру):
    private PreparedStatement portionOfObjects;
    // общее количество объявлений данного типа недвижимости в базе (по фильтру френда/продажа):
    private PreparedStatement numberOfAdsForSaleOrRent;
    //  добавить объект недвижимости в базу данных:
    private PreparedStatement insertObject;
    //  удалить объект недвижимости из базы данных:
    private PreparedStatement deleteObject;
    //  изменить информацию об объекте:
    private PreparedStatement updateObject;
    //  скрыть объект от показа на сайте, не удаляя из базы данных:
    private PreparedStatement hideObject;
    //  открыть для показа (активировать) ранее скрытый объект:
    private PreparedStatement activateObject;

    public CottageDAO() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
        try {
            conn = DriverManager.getConnection(url, login, password);
            objectFullInfo = conn.prepareStatement("select * from cottage where id = ?");
            portionOfObjects = conn.prepareStatement("select id,address,price,type,photo1,forsale from cottage where hide = 0 and forsale = ? limit ?,?");
            numberOfAdsForSaleOrRent = conn.prepareStatement("select count(*) from cottage where hide = 0 and forsale = ?");
            allObjectsForSaleOrRent = conn.prepareStatement("select id,address,price,type,photo1,forsale from cottage where hide = 0 and forsale = ?");
            allObjects = conn.createStatement();
            insertObject = conn.prepareStatement("insert into cottage (forsale,address,price,homearea,landarea,electricity,water,gas,sewerage,description,photo1,photo2,photo3,photo4,photo5,photo6,photo7,photo8,photo9,photo10,hide,type) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            deleteObject = conn.prepareStatement("delete from cottage where id = ?");
            updateObject = conn.prepareStatement("update cottage set forsale=?,address=?,price=?,homearea=?,landarea=?,electricity=?,water=?,gas=?,sewerage=?,description=?,photo1=?,photo2=?,photo3=?,photo4=?,photo5=?,photo6=?,photo7=?,photo8=?,photo9=?,photo10=? where id=?");
            hideObject = conn.prepareStatement("update cottage set hide = 1 where id = ?");
            activateObject = conn.prepareStatement("update cottage set hide = 0 where id = ?");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }

    // подробная информация по объекту недвижимости:
    public Cottage getCottage(int gotId) {
        Cottage cottage = null;
        try {
            objectFullInfo.setInt(1, gotId);
            ResultSet rs = objectFullInfo.executeQuery();
            rs.next();
            boolean forSale = rs.getBoolean("forsale");
            String address = rs.getString("address");
            String price = rs.getString("price");
            double homeArea = rs.getDouble("homearea");
            double landArea = rs.getDouble("landarea");
            String electricity = rs.getString("electricity");
            String water = rs.getString("water");
            String gas = rs.getString("gas");
            String sewerage = rs.getString("sewerage");
            String description = rs.getString("description");
            String photo1 = rs.getString("photo1");
            String photo2 = rs.getString("photo2");
            String photo3 = rs.getString("photo3");
            String photo4 = rs.getString("photo4");
            String photo5 = rs.getString("photo5");
            String photo6 = rs.getString("photo6");
            String photo7 = rs.getString("photo7");
            String photo8 = rs.getString("photo8");
            String photo9 = rs.getString("photo9");
            String photo10 = rs.getString("photo10");
            boolean hide = rs.getBoolean("hide");
            String type = rs.getString("type");

            cottage = new Cottage(gotId, forSale, address, price, homeArea, landArea, electricity, water, gas, sewerage, description,
                    photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getCottage method of CottageDAO");
        }
        return cottage;
    }

    // общее количество объявлений данного типа недвижимости в базе (по фильтру френда/продажа):
    public int getNumberOfAds(boolean forSale) {
        int count = 0;
        try {
            numberOfAdsForSaleOrRent.setBoolean(1, forSale);
            ResultSet rs = numberOfAdsForSaleOrRent.executeQuery();
            rs.next();
            count = rs.getInt("count(*)");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getNumberOfAds method of CottageDAO");
            e.printStackTrace();
        }
        return count;
    }

    // порции короткой инфы (по фильтру, который соответствует определенному виду sql-запроса):
    public ArrayList<Realty> getPortionOfAds(int startAdsIndex, int adsPerPage, boolean forSale) {
        ArrayList<Realty> activeCottages = new ArrayList<Realty>();
        try {
            portionOfObjects.setBoolean(1, forSale);
            portionOfObjects.setInt(2, startAdsIndex);
            portionOfObjects.setInt(3, adsPerPage);
            ResultSet rs = portionOfObjects.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String address = rs.getString("address");
                String price = rs.getString("price");
                String type = rs.getString("type");
                String photo1 = rs.getString("photo1");
                boolean forsale1 = rs.getBoolean("forsale");

                Realty realty = new Realty(id, address, price, type, photo1, forsale1);
                activeCottages.add(realty);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getPotionAds method of CottageDAO");
        }
        return activeCottages;
    }

////////////////// АДМИНСКАЯ ЧАСТЬ //////////////////////////////////////////

    // короткая инфа по всем имеющимся объявлениям (с фильтром аренда/продажа):
    public ArrayList<Realty> getAllAdsForSaleOrRent(boolean forSale) {
        ArrayList<Realty> list = new ArrayList<Realty>();
        try {
            allObjectsForSaleOrRent.setBoolean(1, forSale);
            ResultSet rs = allObjectsForSaleOrRent.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String address = rs.getString("address");
                String price = rs.getString("price");
                String type = rs.getString("type");
                String photo1 = rs.getString("photo1");
                boolean forsale = rs.getBoolean("forsale");

                Realty realty = new Realty(id, address, price, type, photo1, forsale);
                list.add(realty);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllAdsForSaleOrRent method of CottageDAO");
        }
        return list;
    }

    // подробная информация по всем имеющимся объявлениям (без фильтров):
    public ArrayList<Cottage> getAllAds() {
        ArrayList<Cottage> list = new ArrayList<Cottage>();
        try {
            ResultSet rs = allObjects.executeQuery("select * from cottage");
            while (rs.next()) {
                int id = rs.getInt("id");
                boolean forSale = rs.getBoolean("forsale");
                String address = rs.getString("address");
                String price = rs.getString("price");
                double homeArea = rs.getDouble("homearea");
                double landArea = rs.getDouble("landarea");
                String electricity = rs.getString("electricity");
                String water = rs.getString("water");
                String gas = rs.getString("gas");
                String sewerage = rs.getString("sewerage");
                String description = rs.getString("description");
                String photo1 = rs.getString("photo1");
                String photo2 = rs.getString("photo2");
                String photo3 = rs.getString("photo3");
                String photo4 = rs.getString("photo4");
                String photo5 = rs.getString("photo5");
                String photo6 = rs.getString("photo6");
                String photo7 = rs.getString("photo7");
                String photo8 = rs.getString("photo8");
                String photo9 = rs.getString("photo9");
                String photo10 = rs.getString("photo10");
                boolean hide = rs.getBoolean("hide");
                String type = rs.getString("type");

                Cottage cottage = new Cottage(id, forSale, address, price, homeArea, landArea, electricity, water, gas, sewerage, description,
                        photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
                list.add(cottage);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllAds method of CottageDAO");
        }
        return list;
    }

    //  добавить объект недвижимости в базу данных:
    public void add(boolean forsale, String address, String price, double homeArea, double landArea, String electricity,
                    String water, String gas, String sewerage, String description, String photo1, String photo2, String photo3,
                    String photo4, String photo5, String photo6, String photo7, String photo8, String photo9,
                    String photo10, boolean hide, String type) {
        try {
            insertObject.setBoolean(1, forsale);
            insertObject.setString(2, address);
            insertObject.setString(3, price);
            insertObject.setDouble(4, homeArea);
            insertObject.setDouble(5, landArea);
            insertObject.setString(6, electricity);
            insertObject.setString(7, water);
            insertObject.setString(8, gas);
            insertObject.setString(9, sewerage);
            insertObject.setString(10, description);
            insertObject.setString(11, photo1);
            insertObject.setString(12, photo2);
            insertObject.setString(13, photo3);
            insertObject.setString(14, photo4);
            insertObject.setString(15, photo5);
            insertObject.setString(16, photo6);
            insertObject.setString(17, photo7);
            insertObject.setString(18, photo8);
            insertObject.setString(19, photo9);
            insertObject.setString(20, photo10);
            insertObject.setBoolean(21, hide);
            insertObject.setString(22, type);

            insertObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method add Cottage");
        }
    }

    //  удалить объект недвижимости из базы данных:
    public void delete(int id) {
        try {
            deleteObject.setInt(1, id);
            deleteObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method delete Cottage");
        }
    }

    //  изменить информацию об объекте:
    public void update(boolean forsale, String address, String price, double homeArea, double landArea, String electricity, String water,
                       String gas, String sewerage, String description, String photo1, String photo2, String photo3,
                       String photo4, String photo5, String photo6, String photo7, String photo8, String photo9,
                       String photo10, int id) {
        try {
            updateObject.setBoolean(1, forsale);
            updateObject.setString(2, address);
            updateObject.setString(3, price);
            updateObject.setDouble(4, homeArea);
            updateObject.setDouble(5, landArea);
            updateObject.setString(6, electricity);
            updateObject.setString(7, water);
            updateObject.setString(8, gas);
            updateObject.setString(9, sewerage);
            updateObject.setString(10, description);
            updateObject.setString(11, photo1);
            updateObject.setString(12, photo2);
            updateObject.setString(13, photo3);
            updateObject.setString(14, photo4);
            updateObject.setString(15, photo5);
            updateObject.setString(16, photo6);
            updateObject.setString(17, photo7);
            updateObject.setString(18, photo8);
            updateObject.setString(19, photo9);
            updateObject.setString(20, photo10);
            updateObject.setInt(21, id);

            updateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method update Cottage");
        }
    }

    //  скрыть объект от показа на сайте, не удаляя из базы данных:
    public void hide(int id) {
        try {
            hideObject.setInt(1, id);
            hideObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method hide Cottage");
        }
    }

    //  открыть для показа (активировать) ранее скрытый объект:
    public void activate(int id) {
        try {
            activateObject.setInt(1, id);
            activateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method activate Cottage");
        }
    }

    public void closeConnection() {
        try {
            objectFullInfo.close();
            portionOfObjects.close();
            numberOfAdsForSaleOrRent.close();
            allObjectsForSaleOrRent.close();
            allObjects.close();
            insertObject.close();
            updateObject.close();
            deleteObject.close();
            hideObject.close();
            activateObject.close();

            conn.close();
        } catch (SQLException e) {
            System.out.println("SQL exception in method closeConnection Room");
        }
    }

}

