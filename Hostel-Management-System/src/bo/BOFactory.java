package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public enum BOTypes {
        LOGIN, MANAGESTUDENTS, MANAGEROOMS, MAKERESERVATION, RESERVATIONDETAILS, USERMANAGEMENT, KEYMONEYREMAINSTUDENTS

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case LOGIN:
                return new LoginBOImpl();
            case MANAGESTUDENTS:
                return new ManageStudentsBOImpl();
            case MANAGEROOMS:
                return new ManageRoomBOImpl();
            case MAKERESERVATION:
                return new MakeReservationBOImpl();
            case RESERVATIONDETAILS:
                return new ReservationDetailsBOImpl();
            case USERMANAGEMENT:
                //return new UserManagementBOImpl();
            case KEYMONEYREMAINSTUDENTS:
                return new KeyMoneyRemainStudentsBOImpl();
            default:
                return null;

        }
    }
}


