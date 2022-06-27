package bo.custom.impl;

import bo.custom.ManageStudentsBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ManageStudentsBOImpl  implements ManageStudentsBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (Student student : all) {
            studentDTOList.add(new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getContactNo(), student.getDob(), student.getGender()));
        }
        return studentDTOList;
    }

    @Override
    public boolean deleteStudent(String studentId) {
        return studentDAO.delete(studentId);
    }

    @Override
    public boolean checkStudentIsExists(String id) {
        return studentDAO.exist(id);
    }

    @Override
    public boolean saveStudent(StudentDTO dto) {
        return studentDAO.save(new Student(dto.getStudentId(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDob(), dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) {
        return studentDAO.update(new Student(dto.getStudentId(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDob(), dto.getGender()));
    }
}
