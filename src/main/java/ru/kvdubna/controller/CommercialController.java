package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.CommercialDAO;
import ru.kvdubna.service.RealtyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

@Controller
//@SessionAttributes("pageCounter")
/*    CommercialController обрабатывает данные, идущие от пользовательских кликов на
      странице с информацией по коммерческим объектам */
public class CommercialController {
    @Autowired
    private RealtyService realtyService; // инжекция ссылки на компонент DAOService
    @Autowired
    private CommercialDAO commercialDAO; // инжекция ссылки на компонент CommercialDAO

    /* Метод showCommercials с помощью сервисных методов формирует контент для представления commercials.jsp  */
    @RequestMapping("/commercial")
    public ModelAndView showCommercials(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Коммерческая");
        modelAndView.addObject("adsList", realtyService.getAds(commercialDAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.setViewName("commercials");
        return modelAndView;
    }
}