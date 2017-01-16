package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.*;
import ru.kvdubna.service.RealtyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

@Controller
//@SessionAttributes("pageCounter")
public class FlatController {
    @Autowired
    private RealtyService realtyService; // инжекция ссылки на компонент FlatService
    @Autowired
    private FlatDAO flatDAO; // инжекция ссылки на компонент FlatDAO
    @Autowired
    private Flat1DAO flat1DAO; // инжекция ссылки на компонент FlatDAO
    @Autowired
    private Flat2DAO flat2DAO; // инжекция ссылки на компонент FlatDAO
    @Autowired
    private Flat3DAO flat3DAO; // инжекция ссылки на компонент FlatDAO
    @Autowired
    private Flat4DAO flat4DAO; // инжекция ссылки на компонент FlatDAO

    /* Методы ниже с помощью сервисных методов формируют контент для представлений
       квартир (либо всех сразу, либо отдельно по количеству комнат) */

    @RequestMapping("/allflats")
    public ModelAndView showAllFlats(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Квартиры");
        modelAndView.addObject("adsList", realtyService.getAds(flatDAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.setViewName("allFlats");
        return modelAndView;
    }

    //*********************************************************************************************
    @RequestMapping("/flat1")
    public ModelAndView showOneRoomFlats(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Квартиры 1-комн.");
        modelAndView.addObject("adsList", realtyService.getAds(flat1DAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.addObject("numberOfRooms", 1);
        modelAndView.setViewName("nRoomFlats");
        return modelAndView;
    }

    //*********************************************************************************************
    @RequestMapping("/flat2")
    public ModelAndView showTwoRoomFlats(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Квартиры 2-комн.");
        modelAndView.addObject("adsList", realtyService.getAds(flat2DAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.addObject("numberOfRooms", 2);
        modelAndView.setViewName("nRoomFlats");
        return modelAndView;
    }

    //*********************************************************************************************
    @RequestMapping("/flat3")
    public ModelAndView showThreeRoomFlats(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Квартиры 3-комн.");
        modelAndView.addObject("adsList", realtyService.getAds(flat3DAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.addObject("numberOfRooms", 3);
        modelAndView.setViewName("nRoomFlats");
        return modelAndView;
    }

    //*********************************************************************************************
    @RequestMapping("/flat4")
    public ModelAndView showFourRoomFlats(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Квартиры 4+ комн.");
        modelAndView.addObject("adsList", realtyService.getAds(flat4DAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.addObject("numberOfRooms", 4);
        modelAndView.setViewName("nRoomFlats");
        return modelAndView;
    }
}