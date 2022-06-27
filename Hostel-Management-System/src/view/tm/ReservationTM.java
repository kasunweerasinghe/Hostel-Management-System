package view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationTM {
    private String resID;
    private LocalDate date;
    private String roomId;
    private String studentId;
    private String status;
}
