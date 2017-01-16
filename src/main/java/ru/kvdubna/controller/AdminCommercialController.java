package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.dao.CommercialDAO;
import ru.kvdubna.model.Commercial;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;


@Controller
public class AdminCommercialController {
    @Autowired
    private CommercialDAO commercialDAO;

    @RequestMapping("/admincommercial")
    public ModelAndView panel(HttpServletRequest request, ModelAndView modelAndView) {
        userIPAndDate(request,"AdminCommercialController");

        String adminAction = request.getParameter("adminAction");
        if (adminAction != null) {
// Внутри страниц, которые открываются по клику "Показать список объектов",
// "Открыть форму для заполнения" или "Изменить объявление" клик на кнопку "Панель администратора":
            if (adminAction.equals("showAdminPanel")) {
                modelAndView.setViewName("admin");
            }
// В панели администратора внутри страницы, которая открывается по клику
// "Показать список объектов", клик на кнопку "изменить":
            if (adminAction.equals("изменить")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Commercial commercial = commercialDAO.getCommercial(id);
                modelAndView.addObject("commercial", commercial);
                modelAndView.setViewName("adminCommercialChange");
            }
// В панели администратора внутри страницы, которая открывается по клику
// "Показать список объектов", клик на кнопку "удалить":
            if (adminAction.equals("удалить")) {
                int id = Integer.parseInt(request.getParameter("id"));
                commercialDAO.delete(id);
                modelAndView.addObject("commercialList", commercialDAO.getAllAds());
                modelAndView.setViewName("adminCommercialList");
            }
// В панели администратора внутри страницы, которая открывается по клику
// "Показать список объектов", клик на кнопку "снять":
            if (adminAction.equals("снять")) {
                int id = Integer.parseInt(request.getParameter("id"));
                commercialDAO.hide(id);
                modelAndView.addObject("commercialList", commercialDAO.getAllAds());
                modelAndView.setViewName("adminCommercialList");
            }
// В панели администратора внутри страницы, которая открывается по клику
// "Показать список объектов", клик на кнопку "активировать":
            if (adminAction.equals("активировать")) {
                int id = Integer.parseInt(request.getParameter("id"));
                commercialDAO.activate(id);
                modelAndView.addObject("commercialList", commercialDAO.getAllAds());
                modelAndView.setViewName("adminCommercialList");
            }
        }
        return modelAndView;
    }

    /********************************** большой метод ***********************************************/
// В панели администратора внутри страницы, которая открывается по клику
// "Показать список объектов", далее "изменить". В ней клик на кнопку "сохранить изменения"
    @RequestMapping("/commercialSaveChanges")
/*  Метод change выполняет сразу три операции - удаление файлов изображений, их изменение,
    либо вставку новых изображений (плюс всё то же для текстовой информации). Здесь нет
    разделения на более специализированные методы, т.к. пользователь нажимает одну кнопку
    "сохранить изменения" для всех возможных действий. Дополнительное усложнение возникает
     из-за того что приходится использовать один параметр метода (например photo1) на две ситуации -
     изменение существующего изображения и добавления нового.

     Кроме того, используется (временно) загрузка нескольких файлов как одиночных, что также
     увеличивает объем кода  */
    public ModelAndView change(HttpServletRequest request,
                               @RequestParam CommonsMultipartFile photo1,
                               @RequestParam CommonsMultipartFile photo2,
                               @RequestParam CommonsMultipartFile photo3,
                               @RequestParam CommonsMultipartFile photo4,
                               @RequestParam CommonsMultipartFile photo5,
                               @RequestParam CommonsMultipartFile photo6,
                               @RequestParam CommonsMultipartFile photo7,
                               @RequestParam CommonsMultipartFile photo8,
                               @RequestParam CommonsMultipartFile photo9,
                               @RequestParam CommonsMultipartFile photo10) {
        ModelAndView modelAndView = new ModelAndView();
//  Имена файлов изображений будут использоваться в разных частях кода, получаем их
//  либо из формы (если пользователь менял фото), либо из базы данных (если не менял):
        String photo1Name, photo2Name, photo3Name, photo4Name, photo5Name, photo6Name, photo7Name, photo8Name, photo9Name, photo10Name;
        int id = Integer.parseInt(request.getParameter("id"));
        Commercial object = commercialDAO.getCommercial(id);
        if (photo1.getOriginalFilename().length() == 0) {
            photo1Name = object.getPhoto1();            // Если из базы
        } else {
            photo1Name = photo1.getOriginalFilename();  //  Если из формы
        }
        if (photo2.getOriginalFilename().length() == 0) {
            photo2Name = object.getPhoto2();
        } else {
            photo2Name = photo2.getOriginalFilename();
        }
        if (photo3.getOriginalFilename().length() == 0) {
            photo3Name = object.getPhoto3();
        } else {
            photo3Name = photo3.getOriginalFilename();
        }
        if (photo4.getOriginalFilename().length() == 0) {
            photo4Name = object.getPhoto4();
        } else {
            photo4Name = photo4.getOriginalFilename();
        }
        if (photo5.getOriginalFilename().length() == 0) {
            photo5Name = object.getPhoto5();
        } else {
            photo5Name = photo5.getOriginalFilename();
        }
        if (photo6.getOriginalFilename().length() == 0) {
            photo6Name = object.getPhoto6();
        } else {
            photo6Name = photo6.getOriginalFilename();
        }
        if (photo7.getOriginalFilename().length() == 0) {
            photo7Name = object.getPhoto7();
        } else {
            photo7Name = photo7.getOriginalFilename();
        }
        if (photo8.getOriginalFilename().length() == 0) {
            photo8Name = object.getPhoto8();
        } else {
            photo8Name = photo8.getOriginalFilename();
        }
        if (photo9.getOriginalFilename().length() == 0) {
            photo9Name = object.getPhoto9();
        } else {
            photo9Name = photo9.getOriginalFilename();
        }
        if (photo10.getOriginalFilename().length() == 0) {
            photo10Name = object.getPhoto10();
        } else {
            photo10Name = photo10.getOriginalFilename();
        }
//  Определяем абсолютный путь к папке с изображениями. Это папка проекта в томкате
//  плюс нужные подпапки в ней:
        String path = request.getServletContext().getRealPath("/") + "/resources/images/";

//  УДАЛЕНИЕ СУЩЕСТВУЮЩИХ ФАЙЛОВ
        if (request.getParameter("photo1Delete") != null) {
//  Если параметр photo1Delete не null, файл точно имеется - это следует из
//  c:if в adminCommercialChange.jsp. Создаем объект File, у которого путь
//  определили выше, а имя берем из базы данных:
            File filePhoto1 = new File(path, request.getParameter("photo1Name"));
//  Удаляем этот файл:
            filePhoto1.delete();
//  Обновляем информацию для базы данных - там теперь пустая строка:
            photo1Name = "";
        }
        if (request.getParameter("photo2Delete") != null) {
            File filePhoto2 = new File(path, request.getParameter("photo2Name"));
            filePhoto2.delete();
            photo2Name = "";
        }
        if (request.getParameter("photo3Delete") != null) {
            File filePhoto3 = new File(path, request.getParameter("photo3Name"));
            filePhoto3.delete();
            photo3Name = "";
        }
        if (request.getParameter("photo4Delete") != null) {
            File filePhoto4 = new File(path, request.getParameter("photo4Name"));
            filePhoto4.delete();
            photo4Name = "";
        }
        if (request.getParameter("photo5Delete") != null) {
            File filePhoto5 = new File(path, request.getParameter("photo5Name"));
            filePhoto5.delete();
            photo5Name = "";
        }
        if (request.getParameter("photo6Delete") != null) {
            File filePhoto6 = new File(path, request.getParameter("photo6Name"));
            filePhoto6.delete();
            photo6Name = "";
        }
        if (request.getParameter("photo7Delete") != null) {
            File filePhoto7 = new File(path, request.getParameter("photo7Name"));
            filePhoto7.delete();
            photo7Name = "";
        }
        if (request.getParameter("photo8Delete") != null) {
            File filePhoto8 = new File(path, request.getParameter("photo8Name"));
            filePhoto8.delete();
            photo8Name = "";
        }
        if (request.getParameter("photo9Delete") != null) {
            File filePhoto9 = new File(path, request.getParameter("photo9Name"));
            filePhoto9.delete();
            photo9Name = "";
        }
        if (request.getParameter("photo10Delete") != null) {
            File filePhoto10 = new File(path, request.getParameter("photo10Name"));
            filePhoto10.delete();
            photo10Name = "";
        }

//  ИЗМЕНЕНИЕ СУЩЕСТВУЮЩЕГО ФАЙЛА PHOTO1
        // Параметр changePhoto не равен нулю даже тогда, когда пользователь не собирается
        // менять фото, но в базе данных хранится ненулевое значение от прежних вводов.
        if (request.getParameter("changePhoto1") != null && photo1.getOriginalFilename().length() != 0) {
//  Сначала удаляем старое изображение с таким же идентификатором (photo1):
            File filePhoto1 = new File(path, request.getParameter("photo1Name"));
            filePhoto1.delete();
//  Теперь получаем имя нового файла, загруженного из формы (у нас он параметр photo1 метода change):
//  В выбранную папку /resources/images/ записываем новый файл:
            try {
                byte barr[] = photo1.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo1Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//  ДОБАВЛЕНИЕ НОВОГО ФАЙЛА PHOTO1
        if (request.getParameter("changePhoto1") == null && photo1.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo1.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo1Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//  ИЗМЕНЕНИЕ/ДОБАВЛЕНИЕ ФАЙЛА PHOTO2
        if (request.getParameter("changePhoto2") != null && photo2.getOriginalFilename().length() != 0) {
            File filePhoto2 = new File(path, request.getParameter("photo2Name"));
            filePhoto2.delete();
            try {
                byte barr[] = photo2.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo2Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("changePhoto2") == null && photo2.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo2.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo2Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //  ИЗМЕНЕНИЕ/ДОБАВЛЕНИЕ ФАЙЛА PHOTO3
        if (request.getParameter("changePhoto3") != null && photo3.getOriginalFilename().length() != 0) {
            File filePhoto3 = new File(path, request.getParameter("photo3Name"));
            filePhoto3.delete();
            try {
                byte barr[] = photo3.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo3Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("changePhoto3") == null && photo3.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo3.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo3Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //  ИЗМЕНЕНИЕ/ДОБАВЛЕНИЕ ФАЙЛА PHOTO4
        if (request.getParameter("changePhoto4") != null && photo4.getOriginalFilename().length() != 0) {
            File filePhoto4 = new File(path, request.getParameter("photo4Name"));
            filePhoto4.delete();
            try {
                byte barr[] = photo4.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo4Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("changePhoto4") == null && photo4.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo4.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo4Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //  ИЗМЕНЕНИЕ/ДОБАВЛЕНИЕ ФАЙЛА PHOTO5
        if (request.getParameter("changePhoto5") != null && photo5.getOriginalFilename().length() != 0) {
            File filePhoto5 = new File(path, request.getParameter("photo5Name"));
            filePhoto5.delete();
            try {
                byte barr[] = photo5.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo5Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("changePhoto5") == null && photo5.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo5.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo5Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //  ИЗМЕНЕНИЕ/ДОБАВЛЕНИЕ ФАЙЛА PHOTO6
        if (request.getParameter("changePhoto6") != null && photo6.getOriginalFilename().length() != 0) {
            File filePhoto6 = new File(path, request.getParameter("photo6Name"));
            filePhoto6.delete();
            try {
                byte barr[] = photo6.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo6Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("changePhoto6") == null && photo6.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo6.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo6Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //  ИЗМЕНЕНИЕ/ДОБАВЛЕНИЕ ФАЙЛА PHOTO7
        if (request.getParameter("changePhoto7") != null && photo7.getOriginalFilename().length() != 0) {
            File filePhoto7 = new File(path, request.getParameter("photo7Name"));
            filePhoto7.delete();
            try {
                byte barr[] = photo7.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo7Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("changePhoto7") == null && photo7.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo7.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo7Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //  ИЗМЕНЕНИЕ/ДОБАВЛЕНИЕ ФАЙЛА PHOTO8
        if (request.getParameter("changePhoto8") != null && photo8.getOriginalFilename().length() != 0) {
            File filePhoto8 = new File(path, request.getParameter("photo8Name"));
            filePhoto8.delete();
            try {
                byte barr[] = photo8.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo8Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("changePhoto8") == null && photo8.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo8.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo8Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //  ИЗМЕНЕНИЕ/ДОБАВЛЕНИЕ ФАЙЛА PHOTO9
        if (request.getParameter("changePhoto9") != null && photo9.getOriginalFilename().length() != 0) {
            File filePhoto9 = new File(path, request.getParameter("photo9Name"));
            filePhoto9.delete();
            try {
                byte barr[] = photo9.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo9Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("changePhoto9") == null && photo9.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo9.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo9Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //  ИЗМЕНЕНИЕ/ДОБАВЛЕНИЕ ФАЙЛА PHOTO10
        if (request.getParameter("changePhoto10") != null && photo10.getOriginalFilename().length() != 0) {
            File filePhoto10 = new File(path, request.getParameter("photo10Name"));
            filePhoto10.delete();
            try {
                byte barr[] = photo10.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo10Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("changePhoto10") == null && photo10.getOriginalFilename().length() != 0) {
            try {
                byte barr[] = photo10.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo10Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

/*    Кроме фоток, считываем также текстовую информацию, которая возможно была изменена.
      Записываем всю обновленную информацию в базу данных (и заново перезаписываем старую
      необновленную - ну просто так проще - нет смысла городить много кода для проверок).
 */
        boolean forSale;
        if (request.getParameter("forsale").equals("yes")) {
            forSale = true;
        } else {
            forSale = false;
        }
        String address = request.getParameter("address");
        String price = request.getParameter("price");
        String sarea = request.getParameter("area");
        double area;
        if (sarea.length() == 0) {
            area = 0;
        } else {
            area = Double.parseDouble(sarea);
        }
        String description = request.getParameter("description");
        double electricPower = 0;

        commercialDAO.update(forSale, address, price, area, electricPower, description,
                photo1Name, photo2Name, photo3Name, photo4Name, photo5Name, photo6Name,
                photo7Name, photo8Name, photo9Name, photo10Name, id);

//   Получаем из базы данных список объектов с обновленной информацией и добавляем в модель
        modelAndView.addObject("commercialList", commercialDAO.getAllAds());
        modelAndView.setViewName("adminCommercialList");
        return modelAndView;
    }

/*********************** ЕЩЕ ОДИН БОЛЬШОЙ МЕТОД (ЗАПОЛНЕНИЕ ОБЪЯВЛЕНИЙ) ******************/

// В панели администратора внутри страницы, которая открывается по клику
// "Открыть форму для заполнения", клик на кнопку "ДОБАВИТЬ ОБЪЕКТ В БАЗУ":
    @RequestMapping("/commercialInsert")
    public ModelAndView insert(HttpServletRequest request,
                               @RequestParam CommonsMultipartFile photo1,
                               @RequestParam CommonsMultipartFile photo2,
                               @RequestParam CommonsMultipartFile photo3,
                               @RequestParam CommonsMultipartFile photo4,
                               @RequestParam CommonsMultipartFile photo5,
                               @RequestParam CommonsMultipartFile photo6,
                               @RequestParam CommonsMultipartFile photo7,
                               @RequestParam CommonsMultipartFile photo8,
                               @RequestParam CommonsMultipartFile photo9,
                               @RequestParam CommonsMultipartFile photo10) {
        ModelAndView modelAndView = new ModelAndView();
        String path = request.getServletContext().getRealPath("/") + "/resources/images/";

        String photo1Name = "";
        if (photo1.getOriginalFilename().length() != 0) {
            photo1Name = photo1.getOriginalFilename();
            try {
                byte barr[] = photo1.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo1Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String photo2Name = "";
        if (photo2.getOriginalFilename().length() != 0) {
            photo2Name = photo2.getOriginalFilename();
            try {
                byte barr[] = photo2.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo2Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String photo3Name = "";
        if (photo3.getOriginalFilename().length() != 0) {
            photo3Name = photo3.getOriginalFilename();
            try {
                byte barr[] = photo3.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo3Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String photo4Name = "";
        if (photo4.getOriginalFilename().length() != 0) {
            photo4Name = photo4.getOriginalFilename();
            try {
                byte barr[] = photo4.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo4Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String photo5Name = "";
        if (photo5.getOriginalFilename().length() != 0) {
            photo5Name = photo5.getOriginalFilename();
            try {
                byte barr[] = photo5.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo5Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String photo6Name = "";
        if (photo6.getOriginalFilename().length() != 0) {
            photo6Name = photo6.getOriginalFilename();
            try {
                byte barr[] = photo6.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo6Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String photo7Name = "";
        if (photo7.getOriginalFilename().length() != 0) {
            photo7Name = photo7.getOriginalFilename();
            try {
                byte barr[] = photo7.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo7Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String photo8Name = "";
        if (photo8.getOriginalFilename().length() != 0) {
            photo8Name = photo8.getOriginalFilename();
            try {
                byte barr[] = photo8.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo8Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String photo9Name = "";
        if (photo9.getOriginalFilename().length() != 0) {
            photo9Name = photo9.getOriginalFilename();
            try {
                byte barr[] = photo9.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo9Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String photo10Name = "";
        if (photo10.getOriginalFilename().length() != 0) {
            photo10Name = photo10.getOriginalFilename();
            try {
                byte barr[] = photo10.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + photo10Name));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        boolean forSale;
        if (request.getParameter("forsale").equals("yes")) {
            forSale = true;
        } else {
            forSale = false;
        }
        String address = request.getParameter("address");
        String price = request.getParameter("price");
        String sarea = request.getParameter("area");
        double area;
        if (sarea.length() == 0) {
            area = 0;
        } else {
            area = Double.parseDouble(sarea);
        }
        String description = request.getParameter("description");
        double electricPower = 0;

        boolean hide = false;
        String type = "Коммерческая недвижимость";

        commercialDAO.add(forSale, address, price, area, electricPower, description,
                photo1Name, photo2Name, photo3Name, photo4Name, photo5Name, photo6Name,
                photo7Name, photo8Name, photo9Name, photo10Name, hide, type);
        modelAndView.addObject("commercialList", commercialDAO.getAllAds());
        modelAndView.setViewName("adminCommercialList");

        return modelAndView;
    }

}