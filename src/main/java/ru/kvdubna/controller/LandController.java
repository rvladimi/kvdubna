package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.LandDAO;
import ru.kvdubna.service.RealtyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

@Controller
//@SessionAttributes("pageCounter")
/*    LandController обрабатывает данные, идущие от пользовательских кликов
      по кнопкам, связанным с дачами/участками  */
public class LandController {
    @Autowired
    private RealtyService realtyService; // инжекция ссылки на компонент DAOService
    @Autowired
    private LandDAO landDAO; // инжекция ссылки на компонент LandDAO

    /* Метод showLands с помощью сервисных методов формирует контент для представления lands.jsp  */
    @RequestMapping("/land")
    public ModelAndView showLands(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Дачи/участки");
        modelAndView.addObject("adsList", realtyService.getAds(landDAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.setViewName("lands");
        return modelAndView;
    }
}