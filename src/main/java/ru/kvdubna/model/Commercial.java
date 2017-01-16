package ru.kvdubna.model;
import org.springframework.stereotype.Component;

@Component
public class Commercial {
    private int id;
    private boolean forSale;
    private String address;
    private String price;
    private double area;
    private double electricPower;
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

    public Commercial() {}

    public Commercial(
            int id,
            boolean forSale,
            String address,
            String price,
            double area,
            double electricPower,
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
        this.address = address;
        this.price = price;
        this.area = area;
        this.electricPower = electricPower;
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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getElectricPower() {
        return electricPower;
    }

    public void setElectricPower(double electricPower) {
        this.electricPower = electricPower;
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

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}