package objects;

import java.sql.Timestamp;
import java.util.Date;

public class Message {
    private String idMessage;
    private String textOfMessage;
    private Timestamp dateOfMessage;
    private String idUser;
    private String adresat;

    public Message(String id, String text,String idUser,String adresat) {
        this.idUser = idUser;
        this.adresat = adresat;
        this.idMessage = id;
        this.textOfMessage = text;
        //dateOfMessage.getTime();
    }

    @Override
    public String toString() {
        return "From" + idUser + ":" + textOfMessage+" : to"+ adresat;// +":" + dateOfMessage.toString();
    }
}
