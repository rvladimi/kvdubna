package ru.kvdubna.dao;

import ru.kvdubna.model.*;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class TownhouseDAO implements DAO {  //  DAO для таунхаусов
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

    public TownhouseDAO() {
        String driver = "com.mysql.jdbc.Driver";
        String login = "kvadrat";
        String password = "b02081927";
        String url = "jdbc:mysql://localhost:3306/kvdubna"; // kvdubna - название базы данных объектов недвижимости
                try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
        try {
            conn = DriverManager.getConnection(url, login, password);
            objectFullInfo = conn.prepareStatement("select * from townhouse where id = ?");
            portionOfObjects = conn.prepareStatement("select id,address,price,type,photo1,forsale from townhouse where hide = 0 and forsale = ? limit ?,?");
            numberOfAdsForSaleOrRent = conn.prepareStatement("select count(*) from townhouse where hide = 0 and forsale = ?");
            allObjectsForSaleOrRent = conn.prepareStatement("select id,address,price,type,photo1,forsale from townhouse where hide = 0 and forsale = ?");
            allObjects = conn.createStatement();
            insertObject = conn.prepareStatement("insert into townhouse (forsale,address,price,fullarea,livingarea,kitchenarea,roomsarea,description,photo1,photo2,photo3,photo4,photo5,photo6,photo7,photo8,photo9,photo10,hide,type) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            deleteObject = conn.prepareStatement("delete from townhouse where id = ?");
            updateObject = conn.prepareStatement("update townhouse set forsale=?,address=?,price=?,fullarea=?,livingarea=?,kitchenarea=?,roomsarea=?,description=?,photo1=?,photo2=?,photo3=?,photo4=?,photo5=?,photo6=?,photo7=?,photo8=?,photo9=?,photo10=? where id=?");
            hideObject = conn.prepareStatement("update townhouse set hide = 1 where id = ?");
            activateObject = conn.prepareStatement("update townhouse set hide = 0 where id = ?");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }

    // подробная информация по объекту недвижимости:
    public Townhouse getTownhouse(int gotId) {
        Townhouse townhouse = null;
        try {
            objectFullInfo.setInt(1,gotId);
            ResultSet rs = objectFullInfo.executeQuery();
            rs.next();
            boolean forSale = rs.getBoolean("forsale");
            String address = rs.getString("address");
            String price = rs.getString("price");
            double fullHomeArea = rs.getDouble("fullarea");
            double livingArea = rs.getDouble("livingarea");
            double kitchenArea = rs.getDouble("kitchenarea");
            String roomsArea = rs.getString("roomsarea");
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

            townhouse = new Townhouse(gotId, forSale, address, price, fullHomeArea, livingArea, kitchenArea, roomsArea, description, photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getTownhouse method of TownhouseDAO");
        }
        return townhouse;
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
            System.out.println("Exception in getNumberOfAds method of TownhouseDAO");
            e.printStackTrace();
        }
        return count;
    }

    // порции короткой инфы (по фильтру, который соответствует определенному виду sql-запроса):
    public ArrayList<Realty> getPortionOfAds(int startAdsIndex, int adsPerPage, boolean forSale) {
        ArrayList<Realty> activeTownhouses = new ArrayList<Realty>();
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
                activeTownhouses.add(realty);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getPotionAds method of TownhouseDAO");
        }
        return activeTownhouses;
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
            Boolean forsale = rs.getBoolean("forsale");

            Realty realty = new Realty(id, address, price, type, photo1, forsale);
            list.add(realty);
        }
        rs.close();
    } catch (SQLException e) {
        System.out.println("Exception in getAllAdsForSaleOrRent method of TownhouseDAO");
    }
    return list;
}
    // подробная информация по всем имеющимся объявлениям (без фильтров):
    public ArrayList<Townhouse> getAllAds() {
        ArrayList<Townhouse> list = new ArrayList<Townhouse>();
        try {
            ResultSet rs = allObjects.executeQuery("select * from townhouse");
            while (rs.next()) {
                int id = rs.getInt("id");
                boolean forSale = rs.getBoolean("forsale");
                String address = rs.getString("address");
                String price = rs.getString("price");
                double fullHomeArea = rs.getDouble("fullarea");
                double livingArea = rs.getDouble("livingarea");
                double kitchenArea = rs.getDouble("kitchenarea");
                String roomsArea = rs.getString("roomsarea");
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

                Townhouse townhouse = new Townhouse(id, forSale, address, price, fullHomeArea, livingArea, kitchenArea, roomsArea, description, photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
                list.add(townhouse);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllAds method of TownhouseDAO");
        }
        return list;
    }

    //  добавить объект недвижимости в базу данных:
    public void add(boolean forsale, String address, String price, double fullarea, double livingarea, double kitchenarea, String roomsarea, String description, String photo1, String photo2, String photo3, String photo4, String photo5, String photo6, String photo7, String photo8, String photo9, String photo10, boolean hide, String type) {
        try {
            insertObject.setBoolean(1, forsale);
            insertObject.setString(2, address);
            insertObject.setString(3, price);
            insertObject.setDouble(4, fullarea);
            insertObject.setDouble(5, livingarea);
            insertObject.setDouble(6, kitchenarea);
            insertObject.setString(7, roomsarea);
            insertObject.setString(8, description);
            insertObject.setString(9, photo1);
            insertObject.setString(10, photo2);
            insertObject.setString(11, photo3);
            insertObject.setString(12, photo4);
            insertObject.setString(13, photo5);
            insertObject.setString(14, photo6);
            insertObject.setString(15, photo7);
            insertObject.setString(16, photo8);
            insertObject.setString(17, photo9);
            insertObject.setString(18, photo10);
            insertObject.setBoolean(19, hide);
            insertObject.setString(20, type);

        insertObject.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQL exception in method add Townhouse");
    }
}

    //  удалить объект недвижимости из базы данных:
    public void delete(int townhouseId) {
        try {
            deleteObject.setInt(1, townhouseId);
            deleteObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method delete Townhouse");
        }
    }

    //  изменить информацию об объекте:
    public void update(boolean forsale, String address, String price, double fullarea, double livingarea, double kitchenarea, String roomsarea, String description, String photo1, String photo2, String photo3, String photo4, String photo5, String photo6, String photo7, String photo8, String photo9, String photo10, int townhouseId) {
        try {
            updateObject.setBoolean(1, forsale);
            updateObject.setString(2, address);
            updateObject.setString(3, price);
            updateObject.setDouble(4, fullarea);
            updateObject.setDouble(5, livingarea);
            updateObject.setDouble(6, kitchenarea);
            updateObject.setString(7, roomsarea);
            updateObject.setString(8, description);
            updateObject.setString(9, photo1);
            updateObject.setString(10, photo2);
            updateObject.setString(11, photo3);
            updateObject.setString(12, photo4);
            updateObject.setString(13, photo5);
            updateObject.setString(14, photo6);
            updateObject.setString(15, photo7);
            updateObject.setString(16, photo8);
            updateObject.setString(17, photo9);
            updateObject.setString(18, photo10);
            updateObject.setInt(19, townhouseId);

            updateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method update Townhouse");
        }
    }

    //  скрыть объект от показа на сайте, не удаляя из базы данных:
    public void hide(int id) {
        try {
            hideObject.setInt(1, id);
            hideObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method hide Townhouse");
        }
    }

    //  открыть для показа (активировать) ранее скрытый объект:
    public void activate(int id) {
        try {
            activateObject.setInt(1, id);
            activateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method activate Townhouse");
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

