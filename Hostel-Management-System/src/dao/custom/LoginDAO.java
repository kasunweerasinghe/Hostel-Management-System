package dao.custom;

import dao.SuperDAO;
import entity.User;

import java.util.List;

public interface LoginDAO extends SuperDAO {
    List<User> getAllUsers();
}
