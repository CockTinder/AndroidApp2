package one.mixme.mixone;

/**
 * Created by laptop on 16.06.2017.
 */

public class Beziehung {
    private String staerke;
    private int verhaeltnis;

    public String getStaerke() {
        return staerke;
    }

    public int getVerhaeltnis() {
        return verhaeltnis;
    }

    public Beziehung(String staerke, int verhaeltnis) {
        this.staerke = staerke;
        this.verhaeltnis = verhaeltnis;
    }
}
