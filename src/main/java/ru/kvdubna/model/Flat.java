package ru.kvdubna.model;
import org.springframework.stereotype.Component;

@Component
public class Flat {
    private int id;
    private boolean forSale;
    private int roomsNumber;
    private String address;
    private String price;
    private double fullHomeArea;
    private double livingArea;
    private double kitchenArea;
    private String roomsArea;
    private int floor;
    private int floorsNumber;
    private String balcony;
    private String buildingType;
    private String description;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String photo5;
    private String photo6;
    private String photo7;
    private String photo8;
    private String photo9;
    private String photo10;
    private boolean hide;
    private String type;

    public Flat() {}

    public Flat(
            int id,
            boolean forSale,
            int roomsNumber,
            String address,
            String price,
            double fullHomeArea,
            double livingArea,
            double kitchenArea,
            String roomsArea,
            int floor,
            int floorsNumber,
            String balcony,
            String buildingType,
            String description,
            String photo1,
            String photo2,
            String photo3,
            String photo4,
            String photo5,
            String photo6,
            String photo7,
            String photo8,
            String photo9,
            String photo10,
            boolean hide,
            String type) {
        this.id = id;
        this.forSale = forSale;
        this.roomsNumber = roomsNumber;
        this.address = address;
        this.price = price;
        this.fullHomeArea = fullHomeArea;
        this.livingArea = livingArea;
        this.kitchenArea = kitchenArea;
        this.roomsArea = roomsArea;
        this.floor = floor;
        this.floorsNumber = floorsNumber;
        this.balcony = balcony;
        this.buildingType = buildingType;
        this.description = description;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.photo5 = photo5;
        this.photo6 = photo6;
        this.photo7 = photo7;
        this.photo8 = photo8;
        this.photo9 = photo9;
        this.photo10 = photo10;
        this.hide = hide;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getFullHomeArea() {
        return fullHomeArea;
    }

    public void setFullHomeArea(double fullHomeArea) {
        this.fullHomeArea = fullHomeArea;
    }

    public double getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(double livingArea) {
        this.livingArea = livingArea;
    }

    public double getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(double kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public String getRoomsArea() {
        return roomsArea;
    }

    public void setRoomsArea(String roomsArea) {
        this.roomsArea = roomsArea;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    public void setFloorsNumber(int floorsNumber) {
        this.floorsNumber = floorsNumber;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2)
    {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3)
    {
        this.photo3 = photo3;
    }

    public String getPhoto4()
    {
        return photo4;
    }

    public void setPhoto4(String photo4)
    {
        this.photo4 = photo4;
    }

    public String getPhoto5()
    {
        return photo5;
    }

    public void setPhoto5(String photo5)
    {
        this.photo5 = photo5;
    }

    public String getPhoto6()
    {
        return photo6;
    }

    public void setPhoto6(String photo6)
    {
        this.photo6 = photo6;
    }

    public String getPhoto7() {
        return photo7;
    }

    public void setPhoto7(String photo7) {
        this.photo7 = photo7;
    }

    public String getPhoto8() {
        return photo8;
    }

    public void setPhoto8(String photo8) {
        this.photo8 = photo8;
    }

    public String getPhoto9() {
        return photo9;
    }

    public void setPhoto9(String photo9) {
        this.photo9 = photo9;
    }

    public String getPhoto10() {
        return photo10;
    }

    public void setPhoto10(String photo10) {
        this.photo10 = photo10;
    }

    public boolean isHide()
    {
        return hide;
    }

    public void setHide(boolean hide)
    {
        this.hide = hide;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}