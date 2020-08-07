package connection;

import GUI.LoginWindow;
import javafx.scene.image.Image;
import objects.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static DatabaseHelper instance = null;



    public DatabaseHelper() throws SQLException {
        OracleConnector.setUpConnection("fei-sql1.upceucebny.cz", 1521, "IDAS", "st55420", "oracle2019");
    }

    public static DatabaseHelper getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }


    public void insertNovyUzivatel(Uzivatel uzivatel, File foto) throws SQLException, FileNotFoundException {
        Connection connection = OracleConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT SP_ID_UZIVATEL_SEQ.NEXTVAL FROM DUAL");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        String uzivatelID = resultSet.getInt(1) + "";
        uzivatel.setId(uzivatelID);
        if (foto != null) {
            statement = connection.prepareStatement("INSERT INTO SP_UZIVATEL " +
                    "(ID_UZIVATELE, JMENO,PRIJMENI,HESLO,FOTO,ROK_STUDIA,PLATNOST) VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, uzivatel.getId());
            statement.setString(2, uzivatel.getJmeno());
            statement.setString(3, uzivatel.getPrijmeni());
            statement.setString(4, "heslo");
            FileInputStream fin = new FileInputStream(foto);
            statement.setBlob(5, fin);
            statement.setString(6, uzivatel.getRokStudia());
            statement.setInt(7, uzivatel.isJePlatny() ? 1 : 0);
            statement.execute();
        } else {
            statement = connection.prepareStatement("INSERT INTO SP_UZIVATEL " +
                    "(ID_UZIVATELE, JMENO,PRIJMENI,HESLO,FOTO,ROK_STUDIA,PLATNOST) VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, uzivatel.getId());
            statement.setString(2, uzivatel.getJmeno());
            statement.setString(3, uzivatel.getPrijmeni());
            statement.setString(4, "heslo");
            FileInputStream fin = new FileInputStream(new File("foto.jpg"));
            statement.setBlob(5, fin);
            statement.setString(6, uzivatel.getRokStudia());
            statement.setInt(7, uzivatel.isJePlatny() ? 1 : 0);
            statement.execute();
        }


        statement = connection.prepareStatement("SELECT SP_ID_KONTAKT_SEQ.NEXTVAL FROM DUAL");
        resultSet = statement.executeQuery();
        resultSet.next();
        String kontaktID = resultSet.getInt(1) + "";

        statement = connection.prepareStatement("INSERT INTO SP_KONTAKT" +
                " (ID_KONTAKT, TELEFON,EMAIL,MOBIL,ID_UZIVATELE) VALUES (?,?,?,?,?)");
        statement.setString(1, kontaktID);
        statement.setString(2, uzivatel.getKontakt().getTelefon());
        statement.setString(3, uzivatel.getKontakt().getEmail());
        statement.setString(4, uzivatel.getKontakt().getMobil());
        statement.setString(5, uzivatelID);
        statement.execute();
    }

    public void updateUzivatel(Uzivatel uzivatel, File foto) throws SQLException, FileNotFoundException {
        Connection connection = OracleConnector.getConnection();
        PreparedStatement statement;
        if (foto != null) {
            statement = connection.prepareStatement("UPDATE SP_UZIVATEL SET JMENO = ?, PRIJMENI = ?, HESLO = ?, FOTO = ?, ROK_STUDIA = ?, PLATNOST = ? WHERE ID_UZIVATELE = ?");
            statement.setString(1, uzivatel.getJmeno());
            statement.setString(2, uzivatel.getPrijmeni());
            statement.setString(3, uzivatel.getHeslo());
            FileInputStream fin = new FileInputStream(foto);
            statement.setBlob(4, fin);
            statement.setString(5, uzivatel.getRokStudia());
            statement.setInt(6, uzivatel.isJePlatny() ? 1 : 0);
            statement.setString(7, uzivatel.getId());
            statement.execute();
        } else {
            statement = connection.prepareStatement("UPDATE SP_UZIVATEL SET JMENO = ?, PRIJMENI = ?, HESLO = ?, ROK_STUDIA = ?, PLATNOST = ? WHERE ID_UZIVATELE = ?");
            statement.setString(1, uzivatel.getJmeno());
            statement.setString(2, uzivatel.getPrijmeni());
            statement.setString(3, uzivatel.getHeslo());
            statement.setString(4, uzivatel.getRokStudia());
            statement.setInt(5, uzivatel.isJePlatny() ? 1 : 0);
            statement.setString(6, uzivatel.getId());
            statement.execute();
        }


        statement = connection.prepareStatement("UPDATE SP_KONTAKT SET TELEFON = ?, EMAIL = ?, MOBIL = ? WHERE ID_UZIVATELE = ?");
        statement.setString(1, uzivatel.getKontakt().getTelefon());
        statement.setString(2, uzivatel.getKontakt().getEmail());
        statement.setString(3, uzivatel.getKontakt().getMobil());
        statement.setString(4, uzivatel.getId());
        statement.execute();
    }

    public void updateRoleUzivatele(Uzivatel uzivatel) throws SQLException {
        Connection connection = OracleConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM SP_REL_UZIV_ROLE WHERE ID_UZIVATELE = ?");
        statement.setString(1, uzivatel.getId());
        statement.execute();
        uzivatel.getRoleList().remove(ERole.None);
        for (ERole role : uzivatel.getRoleList()) {
            statement = connection.prepareStatement("INSERT INTO SP_REL_UZIV_ROLE(ID_UZIVATELE, ID_ROLE) VALUES(?,( SELECT ID_ROLE FROM SP_ROLE WHERE NAZEV = ? ))");
            statement.setString(1, uzivatel.getId());
            statement.setString(2, role.toString());
            statement.execute();
        }

    }

    public void deleteUzivatel(Uzivatel uzivatel) throws SQLException {
        Connection connection = OracleConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM SP_KONTAKT WHERE ID_UZIVATELE = ?");
        statement.setString(1, uzivatel.getId());
        statement.execute();
        statement = connection.prepareStatement("DELETE FROM SP_REL_UZIV_ROLE WHERE ID_UZIVATELE = ?");
        statement.setString(1, uzivatel.getId());
        statement.execute();
        statement = connection.prepareStatement("DELETE FROM SP_UZIVATEL WHERE ID_UZIVATELE = ?");
        statement.setString(1, uzivatel.getId());
        statement.execute();
    }


    public List<Uzivatel> selectAllUzivatel() throws SQLException {
        List<Uzivatel> result = new ArrayList<>();
        Connection connection = OracleConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM SP_V_UZIVATEL_INFO");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String jmeno = resultSet.getString(2);
            String prijmeni = resultSet.getString(3);
            String heslo = resultSet.getString(4);
            InputStream is = resultSet.getBinaryStream(5);
            Image foto = new Image(is);
            int rokStudia = resultSet.getInt(6);
            int blokovan = resultSet.getInt(7);
            String email = resultSet.getString(8);
            String telefon = resultSet.getString(9);
            String mobil = resultSet.getString(10);
            String role = resultSet.getString(11);
            ERole eRole = ERole.None;
            for (ERole role1 : ERole.values()) {
                if (role1.toString().equalsIgnoreCase(role)) {
                    eRole = role1;
                    break;
                }
            }
            Uzivatel uzivatel = new Uzivatel(id, jmeno, prijmeni, heslo, rokStudia + "", blokovan == 0, foto, new ArrayList<>(), new Kontakt(email, mobil, telefon));

            boolean uzivatelExistuje = false;

            for (Uzivatel uzivatel1 : result) {
                if (uzivatel.getId().equalsIgnoreCase(uzivatel1.getId())) {
                    uzivatel1.getRoleList().add(eRole);
                    uzivatelExistuje = true;
                    break;
                }
            }
            if (!uzivatelExistuje) {
                uzivatel.getRoleList().add(eRole);
                result.add(uzivatel);
            }

        }
        return result;
    }

    public Uzivatel selectUzivatel(Integer id) throws SQLException {
        Connection connection = OracleConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM SP_V_UZIVATEL_INFO WHERE ID_UZIVATELE=" + "'" + id + "'");
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        String jmeno = resultSet.getString(2);
        String prijmeni = resultSet.getString(3);
        String heslo = resultSet.getString(4);
        InputStream is = resultSet.getBinaryStream(5);
        Image foto = new Image(is);
        int rokStudia = resultSet.getInt(6);
        int blokovan = resultSet.getInt(7);
        String email = resultSet.getString(8);
        String telefon = resultSet.getString(9);
        String mobil = resultSet.getString(10);
        String role = resultSet.getString(11);
        ERole eRole = ERole.None;
        for (ERole role1 : ERole.values()) {
            if (role1.toString().equalsIgnoreCase(role)) {
                eRole = role1;
                break;
            }
        }
        Uzivatel uzivatel = new Uzivatel(id + "", jmeno, prijmeni, heslo, rokStudia + "", blokovan == 0, foto, new ArrayList<>(), new Kontakt(email, mobil, telefon));
        System.out.println("Current user is : " + uzivatel.getId()+" "+uzivatel.getJmeno()+" "+uzivatel.getPrijmeni());
        return uzivatel;
    }

    public boolean login(String id, String heslo, ERole role) throws SQLException {
        Connection connection = OracleConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM SP_V_UZIVATEL_INFO WHERE ID_UZIVATELE = ? AND HESLO = ? AND ROLE = ? AND PLATNOST = ?");
        statement.setString(1, id);
        statement.setString(2, heslo);
        statement.setString(3, role.toString());
        statement.setInt(4, 1);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        System.out.println(id + "|" + heslo + "|" + role.toString() + "|" + count);
        return count == 1;
    }

    public List<Message> getMessages() throws SQLException {
        Integer id = new LoginWindow().getUser();
        List<Message> listMessages = new ArrayList<>();
        Message message;
        Connection connection = OracleConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM sp_zprava " +
                "INNER JOIN sp_rel_uziv_zprava sp_zp on sp_zp.id_zpavy = sp_zprava.id_zpavy " +
                "INNER JOIN sp_uzivatel on  sp_uzivatel.id_uzivatele = sp_zp.id_adresat " +
                "where sp_zp.id_adresat = "+id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String idMessage = resultSet.getString(1);
            String text = resultSet.getString(2);
            message = new Message(idMessage, text,id.toString(),id.toString());
            listMessages.add(message);
        }
        return listMessages;
    }

    public List<Predmet> getPredmety() throws  SQLException{
    List<Predmet> predmets = new ArrayList<>();
    Predmet predmet;
    Connection connection = OracleConnector.getConnection();
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM sp_predmet");
    ResultSet resultSet = statement.executeQuery();
    while(resultSet.next()){
        String name = resultSet.getString(1);
        String abbrevation = resultSet.getString(2);
        predmet = new Predmet(name,abbrevation);
        predmets.add(predmet);
    }
        return predmets;
    }
}
