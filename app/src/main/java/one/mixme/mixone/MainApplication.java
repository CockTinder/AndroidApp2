package one.mixme.mixone;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by laptop on 17.06.2017.
 */

public class MainApplication extends Application {
    private DBHandler dbHandler = new DBHandler(this);

    @Override
    public void onCreate() {
        super.onCreate();
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor c1 = db.rawQuery("SELECT COUNT(*) FROM zutat", null);
        Cursor c2 = db.rawQuery("SELECT COUNT(*) FROM drinks", null);
        Cursor c3 = db.rawQuery("SELECT COUNT(*) FROM drinkhatzutat", null);

        final int a = c1.getInt(0);
        final int b = c2.getInt(0);
        final int c = c3.getInt(0);

        DownloadRunnable thread;
        new Thread(thread = new DownloadRunnable() {
            @Override
            public void run() {
                if (a == 0 && b == 0 && c == 0)
                    importAllData();
                else
                    importMissingData();
            }
        }).start();

        dbHandler.addZutat(new Zutat("wodka"));
        dbHandler.addZutat(new Zutat("banane"));
        dbHandler.addZutat(new Zutat("ananas"));
        dbHandler.addZutat(new Zutat("melone"));
        dbHandler.addZutat(new Zutat("cola"));
    }

    public DownloadRunnable importMissingData(){
        //TODO get all last ids of all tables
        //TODO compare to local last ids
        //TODO Download missing data
        return null;
    }

    public DownloadRunnable importAllData(){
        //TODO get all ids of all tables
        //TODO per id get the data
        //TODO download image / store image in specific folder+ update reference
        //TODO update status along
        return null;
    }
}
