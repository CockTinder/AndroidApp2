package one.mixme.mixone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Tinder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewTinder);
        viewPager.setAdapter(new ChildItemPagerAdapter(viewPager));

        Intent intent = getIntent();
        String[] zutaten = intent.getStringArrayExtra("zutaten");

        DBHandler dbHandler = new DBHandler(this);

        SQLiteDatabase db = dbHandler.getReadableDatabase();
        
        String queryrezepte="WHERE ";
        for(int i = 0; i<zutaten.length-1; i++){
            queryrezepte+="NAME ="+zutaten[i]+" OR ";
        }
        queryrezepte+="NAME ="+zutaten[zutaten.length-1];
        
        Cursor cursor = db.rawQuery("SELECT * " +
                "FROM drinkhatzutat JOIN zutat USING ZID" +
                " JOIN drinks USING DID "+queryrezepte, null);
        
        Cursor cursor2 = db.rawQuery("", null); //// TODO: 18.06.17  
        
        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
            
        }
        
        
    }
    public void switchBack (View view){
        Intent intent = new Intent(Tinder.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToRecipe(View view){
        Intent intent = new Intent(Tinder.this, RecipeActivity.class);
        startActivity(intent);
    }


}
