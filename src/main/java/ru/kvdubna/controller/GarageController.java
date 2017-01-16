package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.GarageDAO;
import ru.kvdubna.service.RealtyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

@Controller
//@SessionAttributes("pageCounter")
/*    GarageController обрабатывает данные, идущие от пользовательских кликов
      по кнопкам, связанным с гаражами  */
public class GarageController {
    @Autowired
    private RealtyService realtyService; // инжекция ссылки на компонент DAOService
    @Autowired
    private GarageDAO garageDAO; // инжекция ссылки на компонент GarageDAO

    /* Метод showGarages с помощью сервисных методов формирует контент для представления garages.jsp  */
    @RequestMapping("/garage")
    public ModelAndView showGarages(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Гаражи");
        modelAndView.addObject("adsList", realtyService.getAds(garageDAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.setViewName("garages");
        return modelAndView;
    }
}