package one.mixme.mixone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        int drinkID = intent.getIntExtra("ID", -1);
        String drinkName = intent.getStringExtra("name");
        String bildname = intent.getStringExtra("bild_name");

        DBHandler dbHandler = new DBHandler(this);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT name FROM zutat JOIN drinkhatzutat USING DID WHERE DID = "+drinkID, null);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.mainLayout);

        ImageView image = new ImageView(this);
        linearLayout.addView(image);
        int res = getResources().getIdentifier(bildname, "drawable", this.getPackageName());
        image.setImageResource(res);


        TextView textview = new TextView(this);
        linearLayout.addView(textview);
        String text ="";
        text+= "drink: "+drinkName;
        
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            String zutatname = cursor.getString(0);
            text+=zutatname+", ";
        }
        
        textview.setText(text);
    }


}
