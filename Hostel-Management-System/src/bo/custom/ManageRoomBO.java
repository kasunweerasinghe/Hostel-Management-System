package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;

import java.util.List;

public interface ManageRoomBO extends SuperBO {
    List<RoomDTO> getAllRooms();

    boolean updateQty(String roomTypeID, int qty);

    boolean saveRoom(RoomDTO roomDTO);

    boolean updateRoom(RoomDTO roomDTO);

    boolean deleteRoom(String id);

}
