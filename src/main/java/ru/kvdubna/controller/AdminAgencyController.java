package ru.kvdubna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.kvdubna.model.Agency;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static ru.kvdubna.service.DateAndIP.userIPAndDate;

@Controller
public class AdminAgencyController {
    @Autowired
    Agency agency;

//  На любой странице клик на кнопку "О нашем агентстве":
    @RequestMapping("/agency")
    public ModelAndView showAgency(HttpServletRequest request, ModelAndView modelAndView) {
        userIPAndDate(request, "Об агентстве");
        modelAndView.addObject("agency", agency);
        modelAndView.setViewName("aboutAgency");
        return modelAndView;
    }

// В панели администратора клик на выпадающий список "Изменить информацию об агентстве":
    @RequestMapping("/adminAgency")
    public ModelAndView changeAgency(HttpServletRequest request,
                                     @RequestParam CommonsMultipartFile directorPhoto,
                                     ModelAndView modelAndView) {

        String address = request.getParameter("address");
        if (address != null) {
            agency.setAddress(address);
        }
        String phone1 = request.getParameter("phone1");
        if (phone1 != null) {
            agency.setPhone1(phone1);
        }
        String phone2 = request.getParameter("phone2");
        if (phone2 != null) {
            agency.setPhone2(phone2);
        }
        String email = request.getParameter("email");
        if (email != null) {
            agency.setEmail(email);
        }
        String description = request.getParameter("description");
        if (description != null) {
            agency.setDescription(description);
        }
        String directorAbout = request.getParameter("director");
        if (directorAbout != null) {
            agency.setDirectorAbout(directorAbout);
        }
        String logo = request.getParameter("logo");
        if (logo != null) {
            agency.setLogo(logo);
        }
        String logoNNK = request.getParameter("logoNNK");
        if (logoNNK != null) {
            agency.setLogoNNK(logoNNK);
        }

        if (directorPhoto.getOriginalFilename().length() != 0) {
            String path = request.getServletContext().getRealPath("/") + "/resources/images/";
            if(request.getParameter("photoName") != null) {
                File file = new File(path, request.getParameter("photoName"));
                file.delete();
            }
            String newPhotoName = directorPhoto.getOriginalFilename();
            agency.setDirectorPhoto(newPhotoName);
            try {
                byte barr[] = directorPhoto.getBytes();
                BufferedOutputStream bout = new BufferedOutputStream(
                        new FileOutputStream(path + newPhotoName));
                bout.write(barr);
                bout.flush();
                bout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        modelAndView.addObject("agency", agency);
        modelAndView.setViewName("aboutAgency");
        return modelAndView;
    }
}