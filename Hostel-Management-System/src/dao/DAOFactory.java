package dao;

import dao.custom.impl.LoginDAOImpl;
import dao.custom.impl.ReservationDAOImpl;
import dao.custom.impl.RoomDAOImpl;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        LOGIN,STUDENT,ROOM, RESERVATION
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case LOGIN:
                return new LoginDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }

}
