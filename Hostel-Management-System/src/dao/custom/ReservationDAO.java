package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    String generateNewID();

}
