package dao.custom.impl;

import dao.custom.UserDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean save(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public User search(String s) {
        return null;
    }

    @Override
    public User getFromUserNameAndPassword(String userName, String password) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="From User WHERE userName=: user_Name AND password=: Password";
        Query query = session.createQuery(hql);
        query.setParameter("user_Name",userName);
        query.setParameter("Password",password);
        List<User> users = query.list();

        transaction.commit();
        session.close();

        return users.get(0);
    }

}
