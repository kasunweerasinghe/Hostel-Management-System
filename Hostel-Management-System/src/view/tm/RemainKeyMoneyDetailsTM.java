package view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemainKeyMoneyDetailsTM {
    String studentId;
    String name;
    String address;
    String contactNo;
    String resId;
    LocalDate date;
    String roomId;
    String status;
}
