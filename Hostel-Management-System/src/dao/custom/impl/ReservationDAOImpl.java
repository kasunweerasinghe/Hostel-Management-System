package dao.custom.impl;

import dao.custom.ReservationDAO;
import entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public List<Reservation> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Reservation";
        Query query = session.createQuery(hql);
        List<Reservation> reservationList = query.list();

        transaction.commit();
        session.close();

        return reservationList;
    }

    @Override
    public boolean save(Reservation entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Reservation entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation = session.load(Reservation.class, id);
        session.delete(reservation);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public Reservation search(String s) {
        return null;
    }
    @Override
    public String generateNewID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT resId FROM Reservation ORDER BY resId DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        List<String> list = query.list();

        transaction.commit();
        session.close();

        return list.isEmpty() ? "R-001" : String.format("R-%03d", (Integer.parseInt(list.get(0).replace("R-", "")) + 1));
    }

    @Override
    public boolean updateStatus(String res_id, String status) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="UPDATE Reservation SET status=: new_Status WHERE resId=: reservationId";
        Query query = session.createQuery(hql);
        query.setParameter("new_Status",status);
        query.setParameter("reservationId",res_id);

        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        if(i>0){
            return true;
        }
        return false;
    }




}
