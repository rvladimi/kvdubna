package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.TownhouseDAO;
import ru.kvdubna.service.RealtyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

@Controller
//@SessionAttributes("pageCounter")
public class TownhouseController {
    @Autowired
    private RealtyService realtyService; // инжекция ссылки на компонент DAOService
    @Autowired
    private TownhouseDAO townhouseDAO; // инжекция ссылки на компонент TownhouseDAO

    /* Метод showTownhouses с помощью сервисных методов формирует контент для представления townhouses.jsp  */
    @RequestMapping("/townhouse")
    public ModelAndView showTownhouses(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Таунхаусы");
        modelAndView.addObject("adsList", realtyService.getAds(townhouseDAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.setViewName("townhouses");
        return modelAndView;
    }
}