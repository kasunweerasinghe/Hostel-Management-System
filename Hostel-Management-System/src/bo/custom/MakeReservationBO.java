package bo.custom;

import bo.SuperBO;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;

import java.util.List;

public interface MakeReservationBO extends SuperBO {
    List<StudentDTO> getAllStudents();

    List<RoomDTO> getAllRooms();

    String generateNewReservationID();

    boolean saveStudent(StudentDTO studentDTO);

    boolean checkTheStudentIsExist(String studentId);

    boolean saveReservation(ReservationDTO reservationDTO);

    List<ReservationDTO> getAllReservations();

    boolean updateRoomDetails(RoomDTO roomDTO);
}
