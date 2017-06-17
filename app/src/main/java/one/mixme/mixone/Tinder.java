package one.mixme.mixone;

import android.content.Intent;
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
