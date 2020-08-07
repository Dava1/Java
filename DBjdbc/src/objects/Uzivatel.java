package objects;

import javafx.scene.image.Image;

import java.io.File;
import java.util.List;

public class Uzivatel {
    private String id;
    private String jmeno;
    private String prijmeni;
    private String heslo;
    private String rokStudia;
    private boolean jePlatny;
    private Image foto;
    private List<ERole> roleList;
    private Kontakt kontakt;

    public Uzivatel(String id, String jmeno, String prijmeni, String heslo, String rokStudia, boolean jePlatny, Image foto, List<ERole> roleList, Kontakt kontakt) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.heslo = heslo;
        this.rokStudia = rokStudia;
        this.jePlatny = jePlatny;
        this.foto = foto;
        this.roleList = roleList;
        this.kontakt = kontakt;
    }

    public String getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getRokStudia() {
        return rokStudia;
    }

    public boolean isJePlatny() {
        return jePlatny;
    }

    public Image getFoto() {
        return foto;
    }

    public List<ERole> getRoleList() {
        return roleList;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public void setRokStudia(String rokStudia) {
        this.rokStudia = rokStudia;
    }

    public void setJePlatny(boolean jePlatny) {
        this.jePlatny = jePlatny;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }


    @Override
    public String toString() {
        return id + ", " + jmeno + " " + prijmeni;
    }
}