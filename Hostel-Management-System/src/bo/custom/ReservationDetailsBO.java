package bo.custom;

import bo.SuperBO;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;

import java.util.List;

public interface ReservationDetailsBO extends SuperBO {
    List<ReservationDTO> getAllReservations();

    boolean removeReservation(String id);

    boolean updateRoomQty(String roomTypeID, int qty);

    RoomDTO getRoom(String roomTypeID);

    boolean updateReservationStatus(String res_Id ,String status);

    StudentDTO getStudent(String studentId);
}
