package com.teamx.farmapp;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void Animation(View view){
        if(count==0){
            ImageView image = (ImageView)findViewById(R.id.imageView);
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.clockwise_animation);
            image.startAnimation(animation);
        }
        if(count==1){
            ImageView image = (ImageView)findViewById(R.id.imageView);
            Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.fade_animation);
            image.startAnimation(animation1);
        }
        if(count==2){
            ImageView image = (ImageView)findViewById(R.id.imageView);
            Animation animation1 =
                    AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.blink_animation);
            image.startAnimation(animation1);
        }
        if(count==3){
            ImageView image = (ImageView)findViewById(R.id.imageView);
            Animation animation1 =
                    AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.slide_animation);
            image.startAnimation(animation1);
            count=0;
        }
        count++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}