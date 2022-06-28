package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;

import java.util.List;

public interface ManageRoomBO extends SuperBO {
    List<RoomDTO> getAllRooms() throws Exception;

    boolean updateQty(String roomTypeID, int qty);

    boolean saveRoom(RoomDTO roomDTO) throws Exception;

    boolean updateRoom(RoomDTO roomDTO) throws Exception;

    boolean deleteRoom(String id) throws Exception;

}
