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

        int a = c1.getInt(0);
        int b = c2.getInt(0);
        int c = c3.getInt(0);

        if (a == 0 && b == 0&& c == 0)
            importAllData();
        else
            importMissingData();
    }

    public DownloadRunnable importMissingData(){
        DownloadRunnable thread;
        new Thread(thread = new DownloadRunnable() {
            @Override
            public void run() {
                //TODO get all last ids of all tables
                //TODO compare to local last ids
                //TODO Download missing data
            }
        }).start();
        return thread;
    }

    public DownloadRunnable importAllData(){
        DownloadRunnable thread;
        new Thread(thread = new DownloadRunnable() {
            @Override
            public void run() {
                //TODO get all ids of all tables
                //TODO per id get the data
                //TODO download image / store image in specific folder+ update reference
                //TODO update status along
            }
        }).start();
        return thread;
    }
}
