package ru.kvdubna.service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndIP {
    /*      Метод для определения IP-адреса пользователя и времени его захода    */
    public static void userIPAndDate(HttpServletRequest request, String name) {
        Date d = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String ip = request.getRemoteAddr();
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                ip = inetAddress.getHostAddress();
            } catch (UnknownHostException e) {
                System.out.println("UnknownHostException");
            }
        }
        System.out.println(" IP: " + ip + "  " + name + "  " + format1.format(d));
    }

}