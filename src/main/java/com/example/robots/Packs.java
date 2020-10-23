package com.example.robots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Packs extends AppCompatActivity {
    Button button_l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packs);
        button_l = (Button) findViewById(R.id.button_leave);
        button_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Packs.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        String shareBody = "Попробуй это блюдо в приложении!\nhttps://google.com/";
        String shareSub ="Поделитесь с друзьями!";
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sendIntent, "Поделись едой"));
    }

}