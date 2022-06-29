package bo.custom.impl;

import bo.custom.LoginBO;
import dao.DAOFactory;
import dao.custom.LoginDAO;
import dao.custom.UserDAO;
import entity.User;

import java.util.HashMap;
import java.util.List;

public class LoginBOImpl implements LoginBO {
    private final UserDAO uDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public boolean checkCredentials(String userName, String password) throws Exception {
        HashMap<String, String> allUsers = uDAO.getAllUserNPasswordMap();
        if (allUsers.containsKey(userName)) {
            return allUsers.get(userName).equals(password);
        } else {
            return false;
        }
    }


}
