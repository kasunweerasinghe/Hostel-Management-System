package dao.custom.impl;

import dao.custom.LoginDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class LoginDAOImpl  implements LoginDAO {
    @Override
    public List<User> getAllUsers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List<User> users = query.list();

        transaction.commit();
        session.close();

        return users;
    }

}
