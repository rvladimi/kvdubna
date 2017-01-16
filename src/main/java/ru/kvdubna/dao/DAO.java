package ru.kvdubna.dao;

import ru.kvdubna.model.Realty;

import java.util.ArrayList;

public interface DAO {
    String driver = "com.mysql.jdbc.Driver";
    String login = ".....";
    String password = ".....";
    String url = "jdbc:mysql://localhost:3306/kvdubna"; // kvdubna - название базы данных объектов недвижимости

    ArrayList<Realty> getPortionOfAds(int startAdsIndex, int adsPerPage, boolean forSale);
    int getNumberOfAds(boolean forSale);
}

