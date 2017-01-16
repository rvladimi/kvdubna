package ru.kvdubna.model;
import org.springframework.stereotype.Component;
import javax.validation.constraints.*;

@Component
public class Agency {
    private String address = "Московская обл., г.Дубна, ул.Понтекорво, д.27/2";
    private String phone1 = "8 (09621) 222 98";
    private String phone2 = "+7 903 254 4110";
    private String email = "kvdubna@yandex.ru";
    private String description = "<li>Купля-продажа недвижимости на вторичном рынке г. Дубна</li>"
            + "<li>Продажа квартир в новостройках</li>"
            + "<li>Аренда жилой и коммерческой недвижимости</li>"
            + "<li>Сопровождение сделок</li>";
    private String directorAbout = "Сертифицированный член гильдии риэлторов Московской области, <br>" +
            "член дубненской гильдии риэлторов. <br> 18 лет на рынке недвижимости Дубны.";
    private String directorPhoto = "tanjamini.jpg";
    private String logo = "kvadratfull.jpg";
    private String logoNNK = "NNKlogo.png";

    public Agency() {}

    public Agency(
            String address,
            String phone1,
            String phone2,
            String email,
            String description,
            String directorAbout,
            String directorPhoto,
            String logo,
            String logoNNK) {
        this.address = address;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.description = description;
        this.directorAbout = directorAbout;
        this.directorPhoto = directorPhoto;
        this.logo = logo;
        this.logoNNK = logoNNK;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirectorAbout() {
        return directorAbout;
    }

    public void setDirectorAbout(String directorAbout) {
        this.directorAbout = directorAbout;
    }

    public String getDirectorPhoto() {
        return directorPhoto;
    }

    public void setDirectorPhoto(String directorPhoto) {
        this.directorPhoto = directorPhoto;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogoNNK() {
        return logoNNK;
    }

    public void setLogoNNK(String logoNNK) {
        this.logoNNK = logoNNK;
    }

}