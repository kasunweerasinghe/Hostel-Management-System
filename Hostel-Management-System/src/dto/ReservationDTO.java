package dto;

import entity.Room;
import entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    private String resId;
    private LocalDate date;
    private String status;
    private Student student;
    private Room room;
}
