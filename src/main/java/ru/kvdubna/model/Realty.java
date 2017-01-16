package ru.kvdubna.model;

import org.springframework.stereotype.Component;

@Component
public class Realty {
    private int id;
    private String address;
    private String price;
    private String type;
    private String photo1;
    Boolean forsale;

    public Realty() {
    }

    public Realty(
            int id,
            String address,
            String price,
            String type,
            String photo1,
            Boolean forsale
    ) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.type = type;
        this.photo1 = photo1;
        this.forsale = forsale;
    }

    public int getId() {
        return id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
    public Boolean getForsale() {
        return forsale;
    }

    public void setForsale(Boolean forsale) {
        this.forsale = forsale;
    }

}

