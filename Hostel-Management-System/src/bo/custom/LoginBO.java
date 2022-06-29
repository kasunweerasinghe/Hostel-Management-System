package bo.custom;

import bo.SuperBO;
import entity.User;

import java.util.List;

public interface LoginBO extends SuperBO {
    boolean checkCredentials(String userName, String password) throws Exception;

}
