package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.*;
import ru.kvdubna.model.Agency;

import javax.servlet.http.HttpServletRequest;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

@Controller
public class AdminController {
    @Autowired
    private CommercialDAO commercialDAO;
    @Autowired
    private LandDAO landDAO;
    @Autowired
    private CottageDAO cottageDAO;
    @Autowired
    private GarageDAO garageDAO;
    @Autowired
    private TownhouseDAO townhouseDAO;
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private FlatDAO flatDAO;
    @Autowired
    private Agency agency;

    @RequestMapping("/admin")
    public ModelAndView panel(HttpServletRequest request, ModelAndView modelAndView) {
        userIPAndDate(request, "Панель администратора");

        String adminAction = request.getParameter("adminAction");
        if (adminAction != null) {
            String realtyType = request.getParameter("realtyType");
// В панели администратора клик на выпадающий список "Показать список объектов":
            if (adminAction.equals("showList")) {
                if (realtyType.equals("дача/участок")) {
                    modelAndView.addObject("landList", landDAO.getAllAds());
                    modelAndView.setViewName("adminLandList");
                }
                if (realtyType.equals("коммерческая")) {
                    modelAndView.addObject("commercialList", commercialDAO.getAllAds());
                    modelAndView.setViewName("adminCommercialList");
                }
                if (realtyType.equals("коттедж")) {
                    modelAndView.addObject("cottageList", cottageDAO.getAllAds());
                    modelAndView.setViewName("adminCottageList");
                }
                if (realtyType.equals("гараж")) {
                    modelAndView.addObject("garageList", garageDAO.getAllAds());
                    modelAndView.setViewName("adminGarageList");
                }
                if (realtyType.equals("таунхаус")) {
                    modelAndView.addObject("townhouseList", townhouseDAO.getAllAds());
                    modelAndView.setViewName("adminTownhouseList");
                }
                if (realtyType.equals("комната")) {
                    modelAndView.addObject("roomList", roomDAO.getAllAds());
                    modelAndView.setViewName("adminRoomList");
                }
                if (realtyType.equals("квартира")) {
                    modelAndView.addObject("flatList", flatDAO.getAllAds());
                    modelAndView.setViewName("adminFlatList");
                }
            }
// В панели администратора клик на выпадающий список "Открыть форму для заполнения":
            if (adminAction.equals("openBlank")) {
                if (realtyType.equals("дача/участок")) {
                    modelAndView.setViewName("adminLandInsert");
                }
                if (realtyType.equals("коммерческая")) {
                    modelAndView.setViewName("adminCommercialInsert");
                }
                if (realtyType.equals("коттедж")) {
                    modelAndView.setViewName("adminCottageInsert");
                }
                if (realtyType.equals("гараж")) {
                    modelAndView.setViewName("adminGarageInsert");
                }
                if (realtyType.equals("таунхаус")) {
                    modelAndView.setViewName("adminTownhouseInsert");
                }
                if (realtyType.equals("комната")) {
                    modelAndView.setViewName("adminRoomInsert");
                }
                if (realtyType.equals("квартира")) {
                    modelAndView.setViewName("adminFlatInsert");
                }
            }


/******************** ОБ АГЕНТСТВЕ **************************************/

// В панели администратора клик на кнопку "Открыть страницу редактирования" :
            if (adminAction.equals("aboutAgency")) {
                modelAndView.addObject("agency", agency);
                modelAndView.setViewName("adminAgencyChange");
            }
        }
        return modelAndView;
    }
}