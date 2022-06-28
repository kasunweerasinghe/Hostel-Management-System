package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.util.List;

public interface ManageStudentsBO extends SuperBO {
    List<StudentDTO> getAllStudents() throws Exception;

    boolean deleteStudent(String studentId) throws Exception;

    boolean checkStudentIsExists(String id);

    boolean saveStudent(StudentDTO dto) throws Exception;

    boolean updateStudent(StudentDTO dto) throws Exception;
}
