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

        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM zutat", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final TextView tv = new TextView(this);
            tv.setText(cursor.getString(0));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addTextView(view);
                }
            });
            avItems.addView(tv);
            cursor.moveToNext();
        }
    }

    public void addTextView(View tv){
        LinearLayout adItems = (LinearLayout) findViewById(R.id.addedItems);
        LinearLayout avItems = (LinearLayout) findViewById(R.id.availableItems);
        avItems.removeView(tv);
        adItems.addView(tv);
    }

    public void goToRecipe(View view){
        Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
        startActivity(intent);
    }

    public void switchTinder (View view){
        Intent intent = new Intent(MainActivity.this, Tinder.class);
        LinearLayout adItems = (LinearLayout) findViewById(R.id.addedItems);

        String[] zutaten;

        //intent.putExtra("zutaten", zutaten);

        startActivity(intent);
    }
}
