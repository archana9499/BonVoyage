package com.example.bonvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class SuggestionActivity extends AppCompatActivity {

    private Button goToAppbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestions);

        goToAppbtn = findViewById(R.id.goToAppbtn);
        goToAppbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuggestionActivity.this,SocialMediaActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}