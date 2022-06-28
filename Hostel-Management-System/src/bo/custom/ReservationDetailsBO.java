package bo.custom;

import bo.SuperBO;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;

import java.util.List;

public interface ReservationDetailsBO extends SuperBO {
    List<ReservationDTO> getAllReservations() throws Exception;

    boolean removeReservation(String id) throws Exception;

    boolean updateRoomQty(String roomTypeID, int qty);

    RoomDTO getRoom(String roomTypeID);

    boolean updateReservationStatus(String res_Id ,String status);

    StudentDTO getStudent(String studentId);
}
