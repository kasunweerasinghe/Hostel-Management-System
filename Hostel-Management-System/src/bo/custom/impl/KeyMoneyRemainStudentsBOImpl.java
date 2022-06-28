package bo.custom.impl;

import bo.custom.KeyMoneyRemainStudentsBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;

import java.util.List;

public class KeyMoneyRemainStudentsBOImpl implements KeyMoneyRemainStudentsBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<Object[]> getKeyMoneyAndStudentDetails() {
        return queryDAO.getKeyMoneyAndStudentDetails();
    }
}
