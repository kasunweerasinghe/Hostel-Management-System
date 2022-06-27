package bo.custom;

import bo.SuperBO;
import dto.ReservationDTO;
import dto.RoomDTO;

import java.util.List;

public interface ReservationDetailsBO extends SuperBO {
    List<ReservationDTO> getAllReservations();

    boolean removeReservation(String id);
 
    boolean updateRoomQty(String roomTypeID, int qty);

    RoomDTO getRoom(String roomTypeID);
}
