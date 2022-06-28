package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Room;

public interface RoomDAO extends CrudDAO<Room,String > {
    boolean updateQty(String roomTypeID, int qty);


}
