package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    String generateNewID();

    boolean updateStatus(String res_id, String status);

}
