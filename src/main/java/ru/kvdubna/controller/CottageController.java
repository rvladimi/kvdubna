package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.CottageDAO;
import ru.kvdubna.service.RealtyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

@Controller
//@SessionAttributes("pageCounter")
/*    CottageController обрабатывает данные, идущие от пользовательских кликов на
      странице с информацией по коммерческим объектам */
public class CottageController {
    @Autowired
    private RealtyService realtyService; // инжекция ссылки на компонент DAOService
    @Autowired
    private CottageDAO cottageDAO; // инжекция ссылки на компонент CottageDAO

    /* Метод showCottages с помощью сервисных методов формирует контент для представления cottages.jsp  */
    @RequestMapping("/cottage")
    public ModelAndView showCottages(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Коттеджи");
        modelAndView.addObject("adsList", realtyService.getAds(cottageDAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.setViewName("cottages");
        return modelAndView;
    }
}