package one.mixme.mixone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tinder extends AppCompatActivity {
    private String[] drink_name, bild_name;

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

        String zutatenWhere = "WHERE ";
        for(String zutat : zutaten){
            zutatenWhere += zutat + "OR ";
        }
        
        Cursor cursor = db.rawQuery("SELECT DISTINCT DID FROM drinkhatzutat JOIN zutat USING ZID " + zutatenWhere.substring(0, zutatenWhere.length()-3) + ";", null);
        ViewPager vp = (ViewPager) findViewById(R.id.viewTinder);
        Cursor cursor2;
        drink_name = new String[cursor.getCount()];
        bild_name = new String[cursor.getCount()];
        int i = 0;

        for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){
            cursor2 = db.rawQuery("SELECT name, bild_name FROM drinks WHERE DID=" + cursor.getInt(0) + ";", null);
            drink_name[i] = cursor2.getString(0);
            bild_name[i] = cursor2.getString(1);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setId(i);
            linearLayout.setPadding(50, 0, 50, 0);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setBackgroundResource(R.drawable.roundcorners);

            ImageView img = new ImageView(this);
            img.setImageResource(getResources().getIdentifier(bild_name[i], "drawable", this.getPackageName()));
            linearLayout.addView(img);

            TextView tv = new TextView(this);
            tv.setText(cursor2.getString(0));
            linearLayout.addView(tv);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToRecipe(v);
                }
            });

            vp.addView(linearLayout);

            i++;
        }
    }
    public void switchBack (View view){
        Intent intent = new Intent(Tinder.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToRecipe(View view){
        Intent intent = new Intent(Tinder.this, RecipeActivity.class);

        int id = view.getId();
        intent.putExtra("ID", id);
        intent.putExtra("name", drink_name[id]);
        intent.putExtra("bild_name", bild_name[id]);

        startActivity(intent);
    }


}
