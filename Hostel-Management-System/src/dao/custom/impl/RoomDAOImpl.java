package dao.custom.impl;

import dao.custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public List<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Room";
        Query query = session.createQuery(hql);
        List<Room> roomList = query.list();

        transaction.commit();
        session.close();

        return roomList;
    }

    @Override
    public boolean save(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();

        return true;
    }
    @Override
    public boolean update(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.load(Room.class, id);

        session.delete(room);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean exist(String s) {
        return false;
    }
    @Override
    public Room search(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, s);

        transaction.commit();
        session.close();

        return room;
    }
    @Override
    public boolean updateQty(String roomTypeID, int qty) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="UPDATE Room SET qty=: qty_on_hand WHERE roomId=: id";
        Query query = session.createQuery(hql);
        query.setParameter("qty_on_hand",qty);
        query.setParameter("id",roomTypeID);
        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        if (i>0){
            return true;
        }
        return false;
    }


}
