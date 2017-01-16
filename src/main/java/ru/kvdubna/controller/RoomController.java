package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.RoomDAO;
import ru.kvdubna.service.RealtyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

//@SessionAttributes("pageCounter")
@Controller
public class RoomController {
    @Autowired
    private RealtyService realtyService; // инжекция ссылки на компонент RealtyService
    @Autowired
    private RoomDAO roomDAO; // инжекция ссылки на компонент RoomDAO

    /* Метод showRooms с помощью сервисных методов формирует контент для представления rooms.jsp  */
    @RequestMapping("/room")
    public ModelAndView showRooms(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Комнаты");
        modelAndView.addObject("adsList", realtyService.getAds(roomDAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.setViewName("rooms");
        return modelAndView;
    }
}