package one.mixme.mixone;

/**
 * Created by laptop on 16.06.2017.
 */

public class Drink {
    private String bild_url;
    private String name;

    public Drink(String bild_url, String name){
        this.bild_url = bild_url;
        this.name = name;
    }

    public String getBildUrl() {
        return bild_url;
    }

    public String getName() {
        return name;
    }
}
