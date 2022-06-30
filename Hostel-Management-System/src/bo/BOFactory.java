package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public enum BOTypes {
        LOGIN, STUDENTS, ROOM, RESERVATION, RESERVATIONDETAILS, USER, KEYMONEYREMAINSTUDENTS

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case LOGIN:
                return new LoginBOImpl();
            case STUDENTS:
                return new ManageStudentsBOImpl();
            case ROOM:
                return new ManageRoomBOImpl();
            case RESERVATION:
                return new MakeReservationBOImpl();
            case RESERVATIONDETAILS:
                return new ReservationDetailsBOImpl();
            case USER:
                return new UserBOImpl();
            case KEYMONEYREMAINSTUDENTS:
                return new KeyMoneyRemainStudentsBOImpl();
            default:
                return null;

        }
    }
}


