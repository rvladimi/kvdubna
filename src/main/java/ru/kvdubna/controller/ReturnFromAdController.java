package ru.kvdubna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/* Данный контроллер помогает пользователю вернуться на страницу со списком объектов,
   из которой он уходил на просмотр подробной информации по конретному объявлению.
   Для этого используется параметр source (см. также пояснения к DetailedAdInfoController)  */
@Controller
public class ReturnFromAdController {
    @RequestMapping("/returnToObjectsList")
    public ModelAndView manager(HttpServletRequest request, ModelAndView modelAndView) {

        String source = request.getParameter("source");

        // Кроме информации о типе недвижимости на странице, с которой пользователь
        // заходил на страницу с подробной информацией, передаем также информацию о том,
        // относился ли тот тип к аренде или продаже:
        modelAndView.addObject("forsale", request.getParameter("forsale"));

        if (source.equals("Квартира")) {
            modelAndView.setViewName("redirect:/allflats");
            return modelAndView;
        }
        if (source.equals("Квартира 1-комн.")) {
            modelAndView.setViewName("redirect:/flat1");
            return modelAndView;
        }
        if (source.equals("Квартира 2-комн.")) {
            modelAndView.setViewName("redirect:/flat2");
            return modelAndView;
        }
        if (source.equals("Квартира 3-комн.")) {
            modelAndView.setViewName("redirect:/flat3");
            return modelAndView;
        }
        if (source.equals("Квартира 4+ комн.")) {
            modelAndView.setViewName("redirect:/flat4");
            return modelAndView;
        }
        if (source.equals("Коммерческая недвижимость")) {
            modelAndView.setViewName("redirect:/commercial");
            return modelAndView;
        }
        if (source.equals("Дача/участок")) {
            modelAndView.setViewName("redirect:/land");
            return modelAndView;
        }
        if (source.equals("Таунхаус")) {
            modelAndView.setViewName("redirect:/townhouse");
            return modelAndView;
        }
        if (source.equals("Коттедж")) {
            modelAndView.setViewName("redirect:/cottage");
            return modelAndView;
        }
        if (source.equals("Гараж")) {
            modelAndView.setViewName("redirect:/garage");
            return modelAndView;
        }
        if (source.equals("Комната")) {
            modelAndView.setViewName("redirect:/room");
            return modelAndView;
        }
// если не попали под предыдущие условия, значит источником была страница
// с объектами всех типов (source = main), туда и передаем управление:
        modelAndView.setViewName("redirect:/allobjects");
        return modelAndView;
    }
}