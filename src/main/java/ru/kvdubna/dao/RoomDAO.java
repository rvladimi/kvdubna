package ru.kvdubna.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kvdubna.model.*;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class RoomDAO implements DAO {  //  DAO для комнат
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

    public RoomDAO() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
        try {
            conn = DriverManager.getConnection(url, login, password);
            objectFullInfo = conn.prepareStatement("select * from room where id = ?");
            portionOfObjects = conn.prepareStatement("select id,address,price,type,photo1,forsale from room where hide = 0 and forsale = ? limit ?,?");
            numberOfAdsForSaleOrRent = conn.prepareStatement("select count(*) from room where hide = 0 and forsale = ?");
            allObjectsForSaleOrRent = conn.prepareStatement("select id,address,price,type,photo1,forsale from room where hide = 0 and forsale = ?");
            allObjects = conn.createStatement();
            insertObject = conn.prepareStatement("insert into room (forsale,roomsnumber,address,price,fullarea,livingarea,kitchenarea,roomarea,floor,floors,balcony,buildingtype,description,photo1,photo2,photo3,photo4,photo5,photo6,photo7,photo8,photo9,photo10,hide,type) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            updateObject = conn.prepareStatement("update room set forsale=?,roomsnumber=?,address=?,price=?,fullarea=?,livingarea=?,kitchenarea=?,roomarea=?,floor=?,floors=?,balcony=?,buildingtype=?,description=?,photo1=?,photo2=?,photo3=?,photo4=?,photo5=?,photo6=?,photo7=?,photo8=?,photo9=?,photo10=? where id=?");
            deleteObject = conn.prepareStatement("delete from room where id = ?");
            hideObject = conn.prepareStatement("update room set hide = 1 where id = ?");
            activateObject = conn.prepareStatement("update room set hide = 0 where id = ?");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }

    // подробная информация по объекту недвижимости:
    public Room getRoom(int gotId) {
        Room room = null;
        try {
            objectFullInfo.setInt(1,gotId);
            ResultSet rs = objectFullInfo.executeQuery();
            rs.next();
            boolean forSale = rs.getBoolean("forsale");
            int roomsNumber = rs.getInt("roomsnumber");
            String address = rs.getString("address");
            String price = rs.getString("price");
            double fullHomeArea = rs.getDouble("fullarea");
            double livingArea = rs.getDouble("livingarea");
            double kitchenArea = rs.getDouble("kitchenarea");
            double roomArea = rs.getDouble("roomarea");
            int floor = rs.getInt("floor");
            int floorsNumber = rs.getInt("floors");
            String balcony = rs.getString("balcony");
            String buildingType = rs.getString("buildingtype");
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

            room = new Room(gotId, forSale, roomsNumber, address, price, fullHomeArea, livingArea, kitchenArea,
                    roomArea, floor, floorsNumber, balcony, buildingType, description, photo1, photo2, photo3,
                    photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getRoom method of RoomDAO");
        }
        return room;
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
            System.out.println("Exception in getNumberOfAds method of RoomDAO");
            e.printStackTrace();
        }
        return count;
    }

    // порции короткой инфы (по фильтру, который соответствует определенному виду sql-запроса):
    public ArrayList<Realty> getPortionOfAds(int startAdsIndex, int adsPerPage, boolean forSale) {
        ArrayList<Realty> activeRooms = new ArrayList<Realty>();
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
                Boolean forsale1 = rs.getBoolean("forsale");

                Realty realty = new Realty(id, address, price, type, photo1, forsale1);
                activeRooms.add(realty);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getPotionAds method of RoomDAO");
        }
        return activeRooms;
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
        System.out.println("Exception in getAllAdsForSaleOrRent method of RoomDAO");
    }
    return list;
}
    // подробная информация по всем имеющимся объявлениям (без фильтров):
    public ArrayList<Room> getAllAds() {
        ArrayList<Room> list = new ArrayList<Room>();
        try {
            ResultSet rs = allObjects.executeQuery("select * from room");
            while (rs.next()) {
                int id = rs.getInt("id");
                boolean forSale = rs.getBoolean("forsale");
                int roomsNumber = rs.getInt("roomsnumber");
                String address = rs.getString("address");
                String price = rs.getString("price");
                double fullHomeArea = rs.getDouble("fullarea");
                double livingArea = rs.getDouble("livingarea");
                double kitchenArea = rs.getDouble("kitchenarea");
                double roomArea = rs.getDouble("roomarea");
                int floor = rs.getInt("floor");
                int floorsNumber = rs.getInt("floors");
                String balcony = rs.getString("balcony");
                String buildingType = rs.getString("buildingtype");
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

                Room room = new Room(id, forSale, roomsNumber, address, price, fullHomeArea, livingArea, kitchenArea,
                        roomArea, floor, floorsNumber, balcony, buildingType, description, photo1, photo2, photo3,
                        photo4, photo5, photo6, photo7, photo8, photo9, photo10, hide, type);
                list.add(room);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Exception in getAllAds method of RoomDAO");
        }
        return list;
    }

    //  добавить объект недвижимости в базу данных:
    public void add(boolean forsale, int roomsnumber, String address, String price, double fullarea,
                            double livingarea, double kitchenarea, double roomarea, int floor, int floors,
                            String balcony, String buildingtype, String description, String photo1, String photo2,
                            String photo3, String photo4, String photo5, String photo6, String photo7, String photo8,
                            String photo9, String photo10, boolean hide, String type) {
        try {
            insertObject.setBoolean(1, forsale);
            insertObject.setInt(2, roomsnumber);
            insertObject.setString(3, address);
            insertObject.setString(4, price);
            insertObject.setDouble(5, fullarea);
            insertObject.setDouble(6, livingarea);
            insertObject.setDouble(7, kitchenarea);
            insertObject.setDouble(8, roomarea);
            insertObject.setInt(9, floor);
            insertObject.setInt(10, floors);
            insertObject.setString(11, balcony);
            insertObject.setString(12, buildingtype);
            insertObject.setString(13, description);
            insertObject.setString(14, photo1);
            insertObject.setString(15, photo2);
            insertObject.setString(16, photo3);
            insertObject.setString(17, photo4);
            insertObject.setString(18, photo5);
            insertObject.setString(19, photo6);
            insertObject.setString(20, photo7);
            insertObject.setString(21, photo8);
            insertObject.setString(22, photo9);
            insertObject.setString(23, photo10);
            insertObject.setBoolean(24, hide);
            insertObject.setString(25, type);

        insertObject.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQL exception in method add Room");
    }
}

    //  удалить объект недвижимости из базы данных:
    public void delete(int roomId) {
        try {
            deleteObject.setInt(1, roomId);
            deleteObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method delete Room");
        }
    }

    //  изменить информацию об объекте:
    public void update(boolean forsale, int roomsnumber, String address, String price, double fullarea,
                               double livingarea, double kitchenarea, double roomarea, int floor, int floors,
                               String balcony, String buildingtype, String description, String photo1, String photo2,
                               String photo3, String photo4, String photo5, String photo6, String photo7, String photo8,
                               String photo9, String photo10, int flatroomId) {
        try {
            updateObject.setBoolean(1, forsale);
            updateObject.setInt(2, roomsnumber);
            updateObject.setString(3, address);
            updateObject.setString(4, price);
            updateObject.setDouble(5, fullarea);
            updateObject.setDouble(6, livingarea);
            updateObject.setDouble(7, kitchenarea);
            updateObject.setDouble(8, roomarea);
            updateObject.setInt(9, floor);
            updateObject.setInt(10, floors);
            updateObject.setString(11, balcony);
            updateObject.setString(12, buildingtype);
            updateObject.setString(13, description);
            updateObject.setString(14, photo1);
            updateObject.setString(15, photo2);
            updateObject.setString(16, photo3);
            updateObject.setString(17, photo4);
            updateObject.setString(18, photo5);
            updateObject.setString(19, photo6);
            updateObject.setString(20, photo7);
            updateObject.setString(21, photo8);
            updateObject.setString(22, photo9);
            updateObject.setString(23, photo10);
            updateObject.setInt(24, flatroomId);

            updateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method update Room");
        }
    }

    //  скрыть объект от показа на сайте, не удаляя из базы данных:
    public void hide(int id) {
        try {
            hideObject.setInt(1, id);
            hideObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method hide Room");
        }
    }

    //  открыть для показа (активировать) ранее скрытый объект:
    public void activate(int id) {
        try {
            activateObject.setInt(1, id);
            activateObject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL exception in method activate Room");
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

