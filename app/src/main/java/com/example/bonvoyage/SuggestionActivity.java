package com.example.bonvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;


public class SuggestionActivity extends AppCompatActivity {

    private Button goToAppbtn;
    private CardView seoul;
    private CardView delhi;
    private CardView amritsar;



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

        seoul = findViewById(R.id.seoul);
        seoul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PrettyDialog prettyDialog = new PrettyDialog(SuggestionActivity.this);
                prettyDialog.setTitle("Seoul")
                        .setMessage("Seoul, the capital of South Korea" + "\n" +
                        "Places to visit :" + "\n" + "Gyeongbokgung Palace" + "\n" +
                                "Namsan Tower" + "\n" + "Lotte World");
                prettyDialog.setIcon(R.drawable.place);
                prettyDialog.addButton(
                        "OK",					// button text
                        R.color.pdlg_color_white,		// button text color
                        R.color.pdlg_color_black,		// button background color
                        new PrettyDialogCallback() {		// button OnClick listener
                            @Override
                            public void onClick() {
                                // Do what you gotta do
                                prettyDialog.dismiss();
                            }
                        }
                )
                        .show();
            }
        });

        delhi = findViewById(R.id.delhi);
        delhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PrettyDialog prettyDialog = new PrettyDialog(SuggestionActivity.this);
                prettyDialog.setTitle("Delhi")
                        .setMessage("New Delhi, the capital of India" + "\n" +
                                "Places to visit :" + "\n" + "Red Fort" + "\n" +
                                "Qutub Minar" + "\n" + "Lotus Temple");
                prettyDialog.setIcon(R.drawable.place);
                prettyDialog.addButton(
                        "OK",					// button text
                        R.color.pdlg_color_white,		// button text color
                        R.color.pdlg_color_black,		// button background color
                        new PrettyDialogCallback() {		// button OnClick listener
                            @Override
                            public void onClick() {
                                // Do what you gotta do
                                prettyDialog.dismiss();
                            }
                        }
                )
                        .show();
            }
        });

        amritsar = findViewById(R.id.amritsar);
        amritsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PrettyDialog prettyDialog = new PrettyDialog(SuggestionActivity.this);
                prettyDialog.setTitle("Amritsar")
                        .setMessage("Amritsar, second-most populous city of India" + "\n" +
                                "Places to visit :" + "\n" + "The Golden Temple" + "\n" +
                                "Jallianwala Bagh" + "\n" + "Partition Museum");
                prettyDialog.setIcon(R.drawable.place);
                prettyDialog.addButton(
                        "OK",					// button text
                        R.color.pdlg_color_white,		// button text color
                        R.color.pdlg_color_black,		// button background color
                        new PrettyDialogCallback() {		// button OnClick listener
                            @Override
                            public void onClick() {
                                // Do what you gotta do
                                prettyDialog.dismiss();
                            }
                        }
                )
                        .show();
            }
        });




    }
}