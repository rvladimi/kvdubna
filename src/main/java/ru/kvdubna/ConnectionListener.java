package ru.kvdubna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kvdubna.dao.*;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
public class ConnectionListener implements HttpSessionListener {
    @Autowired
    private FlatDAO flatDAO; // инжекция ссылки на компонент FlatDAO
    @Autowired
    private Flat1DAO flat1DAO; // инжекция ссылки на компонент Flat1DAO
    @Autowired
    private Flat2DAO flat2DAO; // инжекция ссылки на компонент Flat2DAO
    @Autowired
    private Flat3DAO flat3DAO; // инжекция ссылки на компонент Flat3DAO
    @Autowired
    private Flat4DAO flat4DAO; // инжекция ссылки на компонент Flat4DAO
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private LandDAO landDAO;
    @Autowired
    private CommercialDAO commercialDAO;
    @Autowired
    private GarageDAO garageDAO;
    @Autowired
    private CottageDAO cottageDAO;
    @Autowired
    private TownhouseDAO townhouseDAO;

    public void sessionCreated(HttpSessionEvent sessionEvent) {
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        try {
            flatDAO.closeConnection();
            flat1DAO.closeConnection();
            flat2DAO.closeConnection();
            flat3DAO.closeConnection();
            flat4DAO.closeConnection();
            roomDAO.closeConnection();
            landDAO.closeConnection();
            commercialDAO.closeConnection();
            garageDAO.closeConnection();
            cottageDAO.closeConnection();
            townhouseDAO.closeConnection();
        } catch (Exception e) {
            System.out.println("Exception in sessionDestroyed method of ConnectionListener");
        }
    }
}