package ru.kvdubna.service;

import org.springframework.stereotype.Service;
import ru.kvdubna.dao.DAO;
import ru.kvdubna.model.Realty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/*  В данном классе определены сервисные методы, которые используются всеми контроллерами
    конкретных типов недвижимости. Основной метод getAds на основе информации от
    пользовательских кликов вычисляет номер текущей страницы и формирует для нее порцию
    объявлений с краткой информацией по объектам недвижимости.   */
@Service
//@SessionAttributes("pageCounter")
public class RealtyService {

    /*  Константа adsPerPage задает максимальное количество объявлений на главной странице
        (в коде ниже оно может быть любым, но дизайн и верстка jsp-страниц сейчас
        предполагает только значение 4) */
    private final int adsPerPage = 4;
    private int totalAds;  // Переменная для общего количества объявлений

    public ArrayList<Realty> getAds(DAO dao, HttpServletRequest request, HttpSession session) {

        boolean forSale;
        if (request.getParameter("forsale") != null) {
            forSale = Boolean.parseBoolean(request.getParameter("forsale"));
        } else {
            forSale = true;  // значение по умолчанию
        }

        int pageСounter = 0;
        String pagination = request.getParameter("pagination");
        if (pagination != null) {
            if (session.getAttribute("pageCounter") != null) {
                pageСounter = (Integer) session.getAttribute("pageCounter");
            }
            if (pagination.equals("Вперед")) {
                pageСounter++;
                session.setAttribute("pageCounter", pageСounter);
            }
            if (pagination.equals("Назад")) {
                pageСounter--;
                session.setAttribute("pageCounter", pageСounter);
            }
        } else {
            session.setAttribute("pageCounter", 0);
        }

        ArrayList<Realty> adsList = new ArrayList<Realty>();
        /* Размер порции объявлений зависит от количества объявлений в базе, максимального
        числа объявлений на странице, и от общего числа страниц. Вычисляем нужные величины:  */

        // Количество непустых записей во всех таблицах базы данных (с фильтром аренда/продажа):
        totalAds = dao.getNumberOfAds(forSale);

        if (totalAds == 0) { // Если объявлений в базе нет, метод возвращает пустой список
            return adsList;
        }

        /*  Для извлечения информации из базы используем sql-запрос SELECT ... FROM table_name LIMIT x,z
        который выводит z непустых записей, начиная с x. При каждом клике "вперед" или "назад"
        вычисляем значения параметров этого запроса (в коде ниже x это startAdsIndex, z - adsOnThisPage). */

        // Номер начального объявления в очередной порции объявлений для показа:
        int startAdsIndex = pageСounter * adsPerPage;

        /* Вычисляем количество объявлений на текущей странице.
        Если прибавление максимального числа (adsPerPage) к текущему стартовому номеру
        объявления не выводит за пределы общего числа объявлений, то текущую страницу
        заполняем полностью. Иначе вычисляем остаток */
        int adsOnThisPage;
        if ((startAdsIndex + adsPerPage) <= totalAds) {
            adsOnThisPage = adsPerPage;
        } else {
            adsOnThisPage = totalAds - startAdsIndex;
        }

        adsList = dao.getPortionOfAds(startAdsIndex, adsOnThisPage, forSale);
        return adsList;
    }


    /****************************************************************************************
     * Метод getTotalPages вычисляет общее количество страниц. Просто делим общее
     * число объявлений на максимальное число объявлений на одной странице
     */
    public int getTotalPages() {
        int totalPages = totalAds / adsPerPage; // При таком делении остаток отбрасывается. Это ок, если его и нет.
        if (totalAds % adsPerPage > 0) {        // А если есть, то добавляем еще одну страницу.
            totalPages = totalPages + 1;
        }
        return totalPages;
    }

}