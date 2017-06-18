package one.mixme.mixone;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

/**
 * Created by laptop on 16.06.2017.
 */

public class  DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DRINK_TABLE_NAME = "drinks";
    private static final String DRINK_TABLE_CREATE = "CREATE TABLE " + DRINK_TABLE_NAME + " (" +
            "DID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "BILD_URL CHAR(50)," +
            "NAME TEXT);";
    private static final String ZUTAT_TABLE_NAME = "zutat";
    private static final String ZUTAT_TABLE_CREATE = "CREATE TABLE " + ZUTAT_TABLE_NAME + " (" +
            "ZID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NAME TEXT," +
            "FARBCODE CHAR(6));";
    private static final String HAT_TABLE_NAME = "drinkhatzutat";
    private static final String HAT_TABLE_CREATE = "CREATE TABLE " + HAT_TABLE_NAME + " (" +
            "HID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "DID INTEGER," +
            "ZID INTEGER," +
            "STAERKE CHAR(6)," +
            "VERHAELTNIS INTEGER);";

    DBHandler(Context context){
        super(context, "mixone", null, DATABASE_VERSION);
    }

    public int getCountZutaten(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM zutat", null);
        return c.getInt(0);
    }



    public void addDrink(Drink drink){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("BILD_URL", drink.getBildUrl());
        values.put("NAME", drink.getName());

        db.insert("drinks", null, values);
        db.close();
    }

    public void addZutat(Zutat zutat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME", zutat.getName());
        values.put("FARBCODE", "ff0000");

        db.insert("zutat", null, values);
        db.close();
    }

    public void addBeziehung(int DID, int ZID, Beziehung beziehung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("DID", DID);
        values.put("ZID", ZID);
        values.put("STAERKE", beziehung.getStaerke());
        values.put("VERHAELTNIS", beziehung.getVerhaeltnis());
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(DRINK_TABLE_CREATE);
        db.execSQL(ZUTAT_TABLE_CREATE);
        db.execSQL(HAT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ;
    }
}
