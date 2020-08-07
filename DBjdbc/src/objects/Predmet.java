package objects;

public class Predmet {
    private String nazev;
    private String zkratka;

    public Predmet(String nazev, String zkratka){
    this.nazev = nazev;
    this.zkratka = zkratka;
    }
    public String getName(){
        return nazev;
    }
    public String getZkratka(){
        return zkratka;
    }
}
