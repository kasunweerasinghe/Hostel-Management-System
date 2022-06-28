package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.User;

public interface UserDAO extends CrudDAO<User,String> {
        User getFromUserNameAndPassword(String userName,String password);

        }
