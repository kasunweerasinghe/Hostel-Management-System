package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.util.List;

public interface ManageStudentsBO extends SuperBO {
    List<StudentDTO> getAllStudents();

    boolean deleteStudent(String studentId);

    boolean checkStudentIsExists(String id);

    boolean saveStudent(StudentDTO dto);

    boolean updateStudent(StudentDTO dto);
}
