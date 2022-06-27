package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {
    private String roomId;
    private String type;
    private String keyMoney;
    private int qty;

    @Override
    public String toString() {
        return roomId;
    }
}
