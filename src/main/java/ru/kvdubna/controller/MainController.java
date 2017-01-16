package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.RealtyDAO;
import ru.kvdubna.service.RealtyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

/*    MainController формирует контент главной страницы, где отображаются
      объявления по всем типам недвижимости, в зависимости от аренды или продажи.
      Код этого контроллера в точности повторяет код контроллеров конкретных типов недвижимости,
      поскольку по сути главная страница сайта не меняется, меняется только выводимый контент.
      Принципиальная разница с другими контроллерами состоит только в коде используемого
      объекта RealtyDAO - он сам не содержит запросы к базе данных, а использует для этого
      другие DAO-объекты (хотя при этом реализует интерфейс DAO, как и другие DAO-объекты).   */
@Controller
//@SessionAttributes("pageCounter")
@RequestMapping("/")
public class MainController {
    @Autowired
    private RealtyDAO realtyDAO; // инжекция ссылки на компонент RealtyDAO
    @Autowired
    private RealtyService realtyService; // инжекция ссылки на компонент DAOService

    /* Метод showAllObjects с помощью сервисных методов формирует контент для представления index.jsp  */
    @RequestMapping({"/","/allobjects"})
    public ModelAndView showAllObjects(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        userIPAndDate(request, "Главная страница");
        modelAndView.addObject("adsList", realtyService.getAds(realtyDAO, request, session));
        modelAndView.addObject("totalPages", realtyService.getTotalPages());
        modelAndView.setViewName("index");
        return modelAndView;
    }
}