package ru.kvdubna.dao;

import ru.kvdubna.model.*;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
/*  DAO для гаражей и машиномест.
    Отличается от коммерческой только отсутствием поля electricPower
    (по хорошему говоря, оно и в коммерческой лишнее, а так бы полностью совпадали)  */
public class GarageDAO implements DAO {
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

    public GarageDAO() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
        try {
            conn = DriverManager.getConnection(url, login, password);
            objectFullInfo = conn.prepareStatement("select * from garage where id = ?");
            portionOfObjects = conn.prepareStatement("select id,address,price,type,photo1,forsale from garage where hide = 0 and forsale = ? limit ?,?");
            numberOfAdsForSaleOrRent = conn.prepareStatement("select count(*) from garage where hide = 0 and forsale = ?");
            allObjectsForSaleOrRent = conn.prepareStatement("select id,address,price,type,photo1,forsale from garage where hide = 0 and forsale = ?");
            allObjects = conn.createStatement();
            insertObject = conn.prepareStatement("insert into garage (forsale,address,price,area,description,photo1,photo2,photo3,photo4,photo5,photo6,photo7,photo8,photo9,photo10,hide,type) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            deleteObject = conn.prepareStatement("delete from garage where id = ?");
            updateObject = conn.prepareStatement("update garage set forsale=?,address=?,price=?,area=?,description=?,photo1=?,photo2=?,photo3=?,photo4=?,photo5=?,photo6=?,photo7=?,photo8=?,photo9=?,photo10=? where id=?");
            hideObject = conn.prepareStatement("update garage set hide = 1 where id = ?");
            activateObject = conn.prepareStatement("update garage set hide = 0 where id = ?");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }

    // подробная информация по объекту недвижимости:
    public Garage getGarage(int gotId) {
        Garage garage = null;
        try {
            objectFullInfo.setInt(1,gotId);
            ResultSet rs = objectFullInfo.executeQuery();
            rs.next();
            boolean forSale = rs.getBoolean("forsale");
            String address = rs.getString("address");
            String price = rs.getString("price");
            double area = rs.getDouble("area");
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
            garage = new Garage(gotId, forSale, address, price, area, description, photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getGarage method of GarageDAO");
        }
        return garage;
    }

    // общее количество объявлений данного типа недвижимости в базе (по фильтру френда/продажа):
    public int getNumberOfAds(boolean forSale) {
        int count=0;
        try {
            numberOfAdsForSaleOrRent.setBoolean(1,forSale);
            ResultSet rs = numberOfAdsForSaleOrRent.executeQuery();
            rs.next();
                count = rs.getInt("count(*)");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getNumberOfAds method of GarageDAO");
            e.printStackTrace();
        }
        return count;
    }

    // порции короткой инфы (по фильтру, который соответствует определенному виду sql-запроса):
    public ArrayList<Realty> getPortionOfAds(int startAdsIndex, int adsPerPage, boolean forSale) {
        ArrayList<Realty> activeGarages = new ArrayList<Realty>();
        try {
            portionOfObjects.setBoolean(1,forSale);
            portionOfObjects.setInt(2,startAdsIndex);
            portionOfObjects.setInt(3,adsPerPage);
            ResultSet rs = portionOfObjects.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String address = rs.getString("address");
                String price = rs.getString("price");
                String type = rs.getString("type");
                String photo1 = rs.getString("photo1");
                boolean forsale1 = rs.getBoolean("forsale");

                Realty realty = new Realty(id, address, price, type, photo1, forsale1);
                activeGarages.add(realty);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getPotionAds method of GarageDAO");
        }
        return activeGarages;
    }

////////////////// АДМИНСКАЯ ЧАСТЬ //////////////////////////////////////////

    // короткая инфа по всем имеющимся объявлениям (с фильтром аренда/продажа):
public ArrayList<Realty> getAllAdsForSaleOrRent(boolean forSale) {
    ArrayList<Realty> list = new ArrayList<Realty>();
    try {
        allObjectsForSaleOrRent.setBoolean(1,forSale);
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
        System.out.println("Exception in getAllAdsForSaleOrRent method of GarageDAO");
    }
    return list;
}
    // подробная информация по всем имеющимся объявлениям (без фильтров):
    public ArrayList<Garage> getAllAds() {
        ArrayList<Garage> list = new ArrayList<Garage>();
        try {
            ResultSet rs = allObjects.executeQuery("select * from garage");
            while (rs.next()) {
                int id = rs.getInt("id");
                boolean forSale = rs.getBoolean("forsale");
                String address = rs.getString("address");
                String price = rs.getString("price");
                double area = rs.getDouble("area");
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

                Garage garage = new Garage(id, forSale, address, price, area, description, photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
                list.add(garage);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllAds method of GarageDAO");
        }
        return list;
    }

    //  добавить объект недвижимости в базу данных:
public void add(boolean forsale, String address, String price, double area, String description, String photo1, String photo2, String photo3, String photo4, String photo5, String photo6, String photo7, String photo8, String photo9, String photo10, boolean hide, String type) {
    try {
        insertObject.setBoolean(1, forsale);
        insertObject.setString(2, address);
        insertObject.setString(3, price);
        insertObject.setDouble(4, area);
        insertObject.setString(5, description);
        insertObject.setString(6, photo1);
        insertObject.setString(7, photo2);
        insertObject.setString(8, photo3);
        insertObject.setString(9, photo4);
        insertObject.setString(10, photo5);
        insertObject.setString(11, photo6);
        insertObject.setString(12, photo7);
        insertObject.setString(13, photo8);
        insertObject.setString(14, photo9);
        insertObject.setString(15, photo10);
        insertObject.setBoolean(16, hide);
        insertObject.setString(17, type);

        insertObject.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQL exception in method add Garage");
    }
}

    //  удалить объект недвижимости из базы данных:
    public void delete(int garageId) {
        try {
            deleteObject.setInt(1, garageId);
            deleteObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method delete Garage");
        }
    }

    //  изменить информацию об объекте:
    public void update(boolean forsale, String address, String price, double area, String description, String photo1, String photo2, String photo3, String photo4, String photo5, String photo6, String photo7, String photo8, String photo9, String photo10, int id) {
        try {
            updateObject.setBoolean(1, forsale);
            updateObject.setString(2, address);
            updateObject.setString(3, price);
            updateObject.setDouble(4, area);
            updateObject.setString(5, description);
            updateObject.setString(6, photo1);
            updateObject.setString(7, photo2);
            updateObject.setString(8, photo3);
            updateObject.setString(9, photo4);
            updateObject.setString(10, photo5);
            updateObject.setString(11, photo6);
            updateObject.setString(12, photo7);
            updateObject.setString(13, photo8);
            updateObject.setString(14, photo9);
            updateObject.setString(15, photo10);
            updateObject.setInt(16, id);

            updateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method update Garage");
        }
    }

    //  скрыть объект от показа на сайте, не удаляя из базы данных:
    public void hide(int id) {
        try {
            hideObject.setInt(1, id);
            hideObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method hide Garage");
        }
    }

    //  открыть для показа (активировать) ранее скрытый объект:
    public void activate(int id) {
        try {
            activateObject.setInt(1, id);
            activateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method activate Garage");
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

