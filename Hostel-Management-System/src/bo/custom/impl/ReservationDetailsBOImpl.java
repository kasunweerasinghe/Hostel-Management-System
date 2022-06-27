package bo.custom.impl;

import bo.custom.ReservationDetailsBO;
import dao.DAOFactory;
import dao.custom.ReservationDAO;
import dao.custom.RoomDAO;
import dto.ReservationDTO;
import dto.RoomDTO;
import entity.Reservation;
import entity.Room;

import java.util.ArrayList;
import java.util.List;

public class ReservationDetailsBOImpl implements ReservationDetailsBO {
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> all = reservationDAO.getAll();
        List<ReservationDTO> allReservations=new ArrayList<>();
        for (Reservation reservation : all) {
            allReservations.add(new ReservationDTO(reservation.getResId(),reservation.getDate(),reservation.getStatus(),reservation.getStudent(),reservation.getRoom()));
        }
        return allReservations;
    }

    @Override
    public boolean removeReservation(String id) {
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

}
