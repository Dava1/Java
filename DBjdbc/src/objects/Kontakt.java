package objects;

public class Kontakt {
    private String email;
    private String mobil;
    private String telefon;

    public Kontakt(String email, String mobil, String telefon) {
        this.email = email;
        this.mobil = mobil;
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public String getMobil() {
        return mobil;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
