package one.mixme.mixone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new ChildItemPagerAdapter(viewPager));


        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_country);
        // Get the string array
        String[] countries = getResources().getStringArray(R.array.countries_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);

        LinearLayout avItems = (LinearLayout) findViewById(R.id.availableItems);

        dbHandler = new DBHandler(this);

        dbHandler.addZutat(new Zutat("wodka"));
        dbHandler.addZutat(new Zutat("banane"));
        dbHandler.addZutat(new Zutat("ananas"));
        dbHandler.addZutat(new Zutat("melone"));
        dbHandler.addZutat(new Zutat("cola"));

        SQLiteDatabase db = dbHandler.getReadableDatabase();

        for (int i = 0; i < 5; i++){ //dbHandler.getCountZutaten()
            TextView tv = new TextView(this);
            String s = db.rawQuery("SELECT name FROM zutat WHERE ZID =" + i, null).getString(0);
            tv.setText(s);
            avItems.addView(tv);
        }
    }

    public void goToRecipe(View view){
        Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
        startActivity(intent);
    }

    public void switchTinder (View view){
        Intent intent = new Intent(MainActivity.this, Tinder.class);
        startActivity(intent);
    }
}
