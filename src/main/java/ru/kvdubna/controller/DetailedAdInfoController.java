package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.*;

import javax.servlet.http.HttpServletRequest;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

@Controller
public class DetailedAdInfoController {
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

    //  Метод manager обрабатывает КЛИК НА ОБЛАСТЬ С ОБЪЯВЛЕНИЕМ. При клике генерируется
    //  параметр adRealtyType, который определяет тип недвижимости для данного объявления
    //  и id объявления в соответствующей таблице базы данных. На основе этой информации
    //  получаем данные из базы и отправляем в представление.
    //  Кроме adRealtyType и id, генерируется еще параметр source, который используется
    //  для возвращения к нужному списку объектов недвижимости после просмотра страницы
    //  с подробной информацией по объекту.
    @RequestMapping("/detailedAdInfo")
    public ModelAndView manager(HttpServletRequest request, ModelAndView modelAndView) {

        String adRealtyType = request.getParameter("adRealtyType");
        int id = Integer.parseInt(request.getParameter("id"));
        String source = request.getParameter("source");

        userIPAndDate(request, "Объявление " + adRealtyType);

        if (adRealtyType.equals("Квартира 1-комн.")) {
            modelAndView.addObject("source", source);
            modelAndView.addObject("ad", flat1DAO.getFlat(id));
            modelAndView.setViewName("detailedFlat");
            return modelAndView;
        }
        if (adRealtyType.equals("Квартира 2-комн.")) {
            modelAndView.addObject("source", source);
            modelAndView.addObject("ad", flat2DAO.getFlat(id));
            modelAndView.setViewName("detailedFlat");
            return modelAndView;
        }
        if (adRealtyType.equals("Квартира 3-комн.")) {
            modelAndView.addObject("source", source);
            modelAndView.addObject("ad", flat3DAO.getFlat(id));
            modelAndView.setViewName("detailedFlat");
            return modelAndView;
        }
        if (adRealtyType.equals("Квартира 4+ комн.")) {
            modelAndView.addObject("source", source);
            modelAndView.addObject("ad", flat4DAO.getFlat(id));
            modelAndView.setViewName("detailedFlat");
            return modelAndView;
        }
        if (adRealtyType.equals("Коммерческая недвижимость")) {
            modelAndView.addObject("source", source);
            modelAndView.addObject("ad", commercialDAO.getCommercial(id));
            modelAndView.setViewName("detailedCommercial");
            return modelAndView;
        }
        if (adRealtyType.equals("Дача/участок")) {
            modelAndView.addObject("source", source);
            modelAndView.addObject("ad", landDAO.getLand(id));
            modelAndView.setViewName("detailedLand");
            return modelAndView;
        }
        if (adRealtyType.equals("Таунхаус")) {
            modelAndView.addObject("source", source);
            modelAndView.addObject("ad", townhouseDAO.getTownhouse(id));
            modelAndView.setViewName("detailedTownhouse");
            return modelAndView;
        }
        if (adRealtyType.equals("Коттедж")) {
            modelAndView.addObject("source", source);
            modelAndView.addObject("ad", cottageDAO.getCottage(id));
            modelAndView.setViewName("detailedCottage");
            return modelAndView;
        }
        if (adRealtyType.equals("Гараж")) {
            modelAndView.addObject("source", source);
            modelAndView.addObject("ad", garageDAO.getGarage(id));
            modelAndView.setViewName("detailedGarage");
            return modelAndView;
        }
        if (adRealtyType.equals("Комната")) {
                modelAndView.addObject("source", source);
                modelAndView.addObject("ad", roomDAO.getRoom(id));
                modelAndView.setViewName("detailedRoom");
        }
        return modelAndView;
    }
}