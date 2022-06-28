package bo.custom.impl;

import bo.custom.ReservationDetailsBO;
import dao.DAOFactory;
import dao.custom.ReservationDAO;
import dao.custom.RoomDAO;
import dao.custom.StudentDAO;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Reservation;
import entity.Room;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReservationDetailsBOImpl implements ReservationDetailsBO {
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<ReservationDTO> getAllReservations() throws Exception {
        List<Reservation> all = reservationDAO.getAll();
        List<ReservationDTO> allReservations=new ArrayList<>();
        for (Reservation reservation : all) {
            allReservations.add(new ReservationDTO(reservation.getResId(),reservation.getDate(),reservation.getStatus(),reservation.getStudent(),reservation.getRoom()));
        }
        return allReservations;
    }

    @Override
    public boolean removeReservation(String id) throws Exception {
        return reservationDAO.delete(id);
    }

    @Override
    public boolean updateRoomQty(String roomTypeID, int qty) {
        return roomDAO.updateQty(roomTypeID,qty);
    }

    @Override
    public RoomDTO getRoom(String roomTypeID) {
        Room room = roomDAO.search(roomTypeID);
        return new RoomDTO(room.getRoomId(),room.getType(),room.getKeyMoney(),room.getQty());
    }

    @Override
    public boolean updateReservationStatus(String res_id, String status) {
        return reservationDAO.updateStatus(res_id,status);
    }

    @Override
    public StudentDTO getStudent(String studentId) {
        Student student = studentDAO.search(studentId);
        return new StudentDTO(student.getStudentId(),student.getName(),student.getAddress(),student.getContactNo(),student.getDob(),student.getGender());
    }


}
