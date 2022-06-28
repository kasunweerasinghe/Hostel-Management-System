package bo.custom;

import bo.SuperBO;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;

import java.util.List;

public interface MakeReservationBO extends SuperBO {
    List<StudentDTO> getAllStudents() throws Exception;

    List<RoomDTO> getAllRooms() throws Exception;

    String generateNewReservationID();

    boolean saveStudent(StudentDTO studentDTO) throws Exception;

    boolean checkTheStudentIsExist(String studentId);

    boolean saveReservation(ReservationDTO reservationDTO) throws Exception;

    List<ReservationDTO> getAllReservations() throws Exception;

    boolean updateRoomDetails(RoomDTO roomDTO) throws Exception;

}
