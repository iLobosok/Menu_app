package com.example.robots;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.robots.ui.dashboard.DashboardFragment;
import com.example.robots.ui.home.HomeFragment;
import com.example.robots.ui.notifications.NotificationsFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.sql.Struct;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class SecondActivity extends AppCompatActivity {
    Button button_p, button_l, button_d, button_di, button_pack,button_din;
    FirebaseStorage firebaseStorage;

    TextView textm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_p = (Button) findViewById(R.id.button_pizza);
        button_l = (Button) findViewById(R.id.button_leave);
        button_d = (Button) findViewById(R.id.button_drinks);
        button_di = (Button) findViewById(R.id.button_diets);
        button_pack = (Button) findViewById(R.id.button_packs);
        button_din = (Button) findViewById(R.id.button_dinner);



        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }


    }

    protected void onRestart() {
        super.onRestart();
        button_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, Pizza.class);
                startActivity(intent);

            }
        });
        button_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, Drinks.class);
                startActivity(intent);

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navigation_dashboard:
                            selectedFragment = new DashboardFragment();
                            break;
                        case R.id.navigation_notifications:
                            selectedFragment = new NotificationsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    public void pizza(View view) {
        Intent intent = new Intent(SecondActivity.this, Pizza.class);
        startActivity(intent);
    }

    public void drinks(View view) {
        Intent intent = new Intent(SecondActivity.this, Drinks.class);
        startActivity(intent);
    }

    public void diets(View view) {
        Intent intent = new Intent(SecondActivity.this, Diets.class);
        startActivity(intent);
    }

    public void dinner(View view) {
        Intent intent = new Intent(SecondActivity.this, Dinner.class);
        startActivity(intent);
    }

    public void packs(View view) {
        Intent intent = new Intent(SecondActivity.this, Packs.class);
        startActivity(intent);
    }

    public void dronezonemap(View view) {
        Intent intent = new Intent(SecondActivity.this, DroneMap.class);
        startActivity(intent);
    }

    public void howitworks(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://t.me/niowoapp/6"));
        startActivity(intent);
    }
}