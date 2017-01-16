package ru.kvdubna.dao;

import ru.kvdubna.model.*;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class CommercialDAO implements DAO {  //  DAO для коммерческой недвижимости
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

    public CommercialDAO() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
        try {
            conn = DriverManager.getConnection(url, login, password);
            objectFullInfo = conn.prepareStatement("select * from commercial where id = ?");
            portionOfObjects = conn.prepareStatement("select id,address,price,type,photo1,forsale from commercial where hide = 0 and forsale = ? limit ?,?");
            numberOfAdsForSaleOrRent = conn.prepareStatement("select count(*) from commercial where hide = 0 and forsale = ?");
            allObjectsForSaleOrRent = conn.prepareStatement("select id,address,price,type,photo1,forsale from commercial where hide = 0 and forsale = ?");
            allObjects = conn.createStatement();
            insertObject = conn.prepareStatement("insert into commercial (forsale,address,price,area,electricpower,description,photo1,photo2,photo3,photo4,photo5,photo6,photo7,photo8,photo9,photo10,hide,type) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            deleteObject = conn.prepareStatement("delete from commercial where id = ?");
            updateObject = conn.prepareStatement("update commercial set forsale=?,address=?,price=?,area=?,electricpower=?,description=?,photo1=?,photo2=?,photo3=?,photo4=?,photo5=?,photo6=?,photo7=?,photo8=?,photo9=?,photo10=? where id=?");
            hideObject = conn.prepareStatement("update commercial set hide = 1 where id = ?");
            activateObject = conn.prepareStatement("update commercial set hide = 0 where id = ?");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }

    // подробная информация по объекту недвижимости:
    public Commercial getCommercial(int gotId) {
        Commercial commercial = null;
        try {
            objectFullInfo.setInt(1,gotId);
            ResultSet rs = objectFullInfo.executeQuery();
            rs.next();
            boolean forSale = rs.getBoolean("forsale");
            String address = rs.getString("address");
            String price = rs.getString("price");
            double area = rs.getDouble("area");
            double electricPower = rs.getDouble("electricpower");
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
            commercial = new Commercial(gotId, forSale, address, price, area, electricPower, description, photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getCommercial method of CommercialDAO");
        }
        return commercial;
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
            System.out.println("Exception in getNumberOfAds method of CommercialDAO");
            e.printStackTrace();
        }
        return count;
    }

    // порции короткой инфы (по фильтру, который соответствует определенному виду sql-запроса):
    public ArrayList<Realty> getPortionOfAds(int startAdsIndex, int adsPerPage, boolean forSale) {
        ArrayList<Realty> activeCommercials = new ArrayList<Realty>();
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
                activeCommercials.add(realty);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getPotionAds method of CommercialDAO");
        }
        return activeCommercials;
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
        System.out.println("Exception in getAllAdsForSaleOrRent method of CommercialDAO");
    }
    return list;
}
    // подробная информация по всем имеющимся объявлениям (без фильтров):
    public ArrayList<Commercial> getAllAds() {
        ArrayList<Commercial> list = new ArrayList<Commercial>();
        try {
            ResultSet rs = allObjects.executeQuery("select * from commercial");
            while (rs.next()) {
                int id = rs.getInt("id");
                boolean forSale = rs.getBoolean("forsale");
                String address = rs.getString("address");
                String price = rs.getString("price");
                double area = rs.getDouble("area");
                double electricPower = rs.getDouble("electricpower");
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

                Commercial commercial = new Commercial(id, forSale, address, price, area, electricPower, description, photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
                list.add(commercial);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllAds method of CommercialDAO");
        }
        return list;
    }

    //  добавить объект недвижимости в базу данных:
public void add(boolean forsale, String address, String price, double area, double electricPower, String description, String photo1, String photo2, String photo3, String photo4, String photo5, String photo6, String photo7, String photo8, String photo9, String photo10, boolean hide, String type) {
    try {
        insertObject.setBoolean(1, forsale);
        insertObject.setString(2, address);
        insertObject.setString(3, price);
        insertObject.setDouble(4, area);
        insertObject.setDouble(5, electricPower);
        insertObject.setString(6, description);
        insertObject.setString(7, photo1);
        insertObject.setString(8, photo2);
        insertObject.setString(9, photo3);
        insertObject.setString(10, photo4);
        insertObject.setString(11, photo5);
        insertObject.setString(12, photo6);
        insertObject.setString(13, photo7);
        insertObject.setString(14, photo8);
        insertObject.setString(15, photo9);
        insertObject.setString(16, photo10);
        insertObject.setBoolean(17, hide);
        insertObject.setString(18, type);

        insertObject.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQL exception in method add Commercial");
    }
}

    //  удалить объект недвижимости из базы данных:
    public void delete(int commercialId) {
        try {
            deleteObject.setInt(1, commercialId);
            deleteObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method delete Commercial");
        }
    }

    //  изменить информацию об объекте:
    public void update(boolean forsale, String address, String price, double area, double electricPower, String description, String photo1, String photo2, String photo3, String photo4, String photo5, String photo6, String photo7, String photo8, String photo9, String photo10, int id) {
        try {
            updateObject.setBoolean(1, forsale);
            updateObject.setString(2, address);
            updateObject.setString(3, price);
            updateObject.setDouble(4, area);
            updateObject.setDouble(5, electricPower);
            updateObject.setString(6, description);
            updateObject.setString(7, photo1);
            updateObject.setString(8, photo2);
            updateObject.setString(9, photo3);
            updateObject.setString(10, photo4);
            updateObject.setString(11, photo5);
            updateObject.setString(12, photo6);
            updateObject.setString(13, photo7);
            updateObject.setString(14, photo8);
            updateObject.setString(15, photo9);
            updateObject.setString(16, photo10);
            updateObject.setInt(17, id);

            updateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method update Commercial");
        }
    }

    //  скрыть объект от показа на сайте, не удаляя из базы данных:
    public void hide(int id) {
        try {
            hideObject.setInt(1, id);
            hideObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method hide Commercial");
        }
    }

    //  открыть для показа (активировать) ранее скрытый объект:
    public void activate(int id) {
        try {
            activateObject.setInt(1, id);
            activateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method activate Commercial");
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

