package dao.custom;

import dao.SuperDAO;

import java.util.List;

public interface QueryDAO  extends SuperDAO {

    List<Object[]> getKeyMoneyAndStudentDetails();

}
