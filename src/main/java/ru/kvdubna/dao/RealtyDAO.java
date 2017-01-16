package ru.kvdubna.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kvdubna.model.Realty;

import java.util.ArrayList;

@Component
public class RealtyDAO implements DAO {
    @Autowired
    private FlatDAO flatDAO; // инжекция ссылки на компонент Flat1DAO
    @Autowired
    private Flat1DAO flat1DAO; // инжекция ссылки на компонент Flat1DAO
    @Autowired
    private Flat2DAO flat2DAO; // инжекция ссылки на компонент Flat2DAO
    @Autowired
    private Flat3DAO flat3DAO; // инжекция ссылки на компонент Flat3DAO
    @Autowired
    private Flat4DAO flat4DAO; // инжекция ссылки на компонент Flat4DAO
    @Autowired
    private RoomDAO roomDAO; // инжекция ссылки на компонент RoomDAO
    @Autowired
    private TownhouseDAO townhouseDAO; // инжекция ссылки на компонент TownhouseDAO
    @Autowired
    private LandDAO landDAO; // инжекция ссылки на компонент LandDAO
    @Autowired
    private GarageDAO garageDAO; // инжекция ссылки на компонент GarageDAO
    @Autowired
    private CottageDAO cottageDAO; // инжекция ссылки на компонент CottageDAO
    @Autowired
    private CommercialDAO commercialDAO; // инжекция ссылки на компонент CommercialDAO

    public RealtyDAO() {}

    public int getNumberOfAds(boolean forSale) {
        int totalFlatAds = flatDAO.getNumberOfAds(forSale);
        int totalCommercialAds = commercialDAO.getNumberOfAds(forSale);
        int totalLandAds = landDAO.getNumberOfAds(forSale);
        int totalCottageAds = cottageDAO.getNumberOfAds(forSale);
        int totalGarageAds = garageDAO.getNumberOfAds(forSale);
        int totalTownhouseAds = townhouseDAO.getNumberOfAds(forSale);
        int totalRoomAds = roomDAO.getNumberOfAds(forSale);
        return totalFlatAds + totalCommercialAds + totalLandAds + totalCottageAds + totalGarageAds + totalTownhouseAds + totalRoomAds;
    }

    public ArrayList<Realty> getPortionOfAds(int startAdsIndex, int adsPerPage, boolean forSale) {
        ArrayList<Realty> flat1List = flat1DAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> flat2List = flat2DAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> flat3List = flat3DAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> flat4List = flat4DAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> commercialList = commercialDAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> landList = landDAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> cottageList = cottageDAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> garageList = garageDAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> townhouseList = townhouseDAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> roomList = roomDAO.getAllAdsForSaleOrRent(forSale);
        ArrayList<Realty> totalList = new ArrayList<Realty>();
        ArrayList<Realty> portion = new ArrayList<Realty>();
        Realty realty;
        // от очередности блоков for зависит очередность показа типов объявлений на главной странице //
        for(int i=0; i < flat1List.size(); i++) {
            realty = flat1List.get(i);
            totalList.add(realty);
        }
        for(int i=0; i < flat2List.size(); i++) {
            realty = flat2List.get(i);
            totalList.add(realty);
        }
        for(int i=0; i < flat3List.size(); i++) {
            realty = flat3List.get(i);
            totalList.add(realty);
        }
        for(int i=0; i < flat4List.size(); i++) {
            realty = flat4List.get(i);
            totalList.add(realty);
        }
        for(int i=0; i < roomList.size(); i++) {
            realty = roomList.get(i);
            totalList.add(realty);
        }
        for(int i=0; i < landList.size(); i++) {
            realty = landList.get(i);
            totalList.add(realty);
        }
        for(int i=0; i < garageList.size(); i++) {
            realty = garageList.get(i);
            totalList.add(realty);
        }
        for(int i=0; i < commercialList.size(); i++) {
            realty = commercialList.get(i);
            totalList.add(realty);
        }
        for(int i=0; i < cottageList.size(); i++) {
            realty = cottageList.get(i);
            totalList.add(realty);
        }
        for(int i=0; i < townhouseList.size(); i++) {
            realty = townhouseList.get(i);
            totalList.add(realty);
        }
        for(int i = 0; i < adsPerPage; i++) {
            realty = totalList.get(i + startAdsIndex);
            portion.add(realty);
        }
        return portion;
    }

}

