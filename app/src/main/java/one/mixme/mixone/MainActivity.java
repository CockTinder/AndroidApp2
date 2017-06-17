package one.mixme.mixone;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new ChildItemPagerAdapter(viewPager));
    }

    public void switchTinder (View view){
        Intent intent = new Intent(MainActivity.this, Tinder.class);
        startActivity(intent);
    }
}
