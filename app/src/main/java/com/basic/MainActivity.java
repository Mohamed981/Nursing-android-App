package com.basic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton homeImg, covidImg, careImg,labImg,heartImg,toolsImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeImg = (ImageButton) findViewById(R.id.homeBtn);
        covidImg = (ImageButton) findViewById(R.id.covidBtn);
        careImg = (ImageButton) findViewById(R.id.careBtn);
        labImg = (ImageButton) findViewById(R.id.labBtn);
        heartImg = (ImageButton) findViewById(R.id.heartBtn);
        toolsImg = (ImageButton) findViewById(R.id.toolsBtn);

        homeImg.setOnClickListener((View.OnClickListener) this);
        covidImg.setOnClickListener((View.OnClickListener) this);
        careImg.setOnClickListener((View.OnClickListener) this);
        labImg.setOnClickListener((View.OnClickListener) this);
        heartImg.setOnClickListener((View.OnClickListener) this);
        toolsImg.setOnClickListener((View.OnClickListener) this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0358F2"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("غَيْث");

        String Path2font = "Scheherazade-Bold.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), Path2font);

    }

    @Override
    public void onClick(View v) {

        if (v == homeImg) {
            Intent intent = new Intent(getApplicationContext(), HomeVisits.class);
            intent.putExtra("index", "Home Visits ");
            startActivity(intent);

            //Define what to do when button is clicked
        } else if (v == covidImg) {
            Intent intent = new Intent(getApplicationContext(), AddSpecial.class);
            intent.putExtra("index", "Covid");
            startActivity(intent);

        } else if (v == careImg) {
            Intent intent = new Intent(getApplicationContext(), AddSpecial.class);
            intent.putExtra("index", "Home Care ");
            startActivity(intent);

        }
        else if (v == labImg) {
            AlertDialog builder = new AlertDialog.Builder(MainActivity.this)
                    .setMessage("قريبا بإذن الله")
                    .setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    })
                    .create();
            builder.show();
        }
        else if (v == heartImg) {
            Alert();
        }
        else if (v == toolsImg) {
           Alert();
        }
    }

void Alert(){
    AlertDialog builder = new AlertDialog.Builder(MainActivity.this)

            .setMessage("قريبا بإذن الله")
            .setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // FIRE ZE MISSILES!

                }
            })
            .create();
    builder.show();
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        System.out.println("true");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                intent = new Intent(getApplicationContext(), AboutUs.class);
                intent.putExtra("index", "main ");
                startActivity(intent);
                return true;
            case R.id.contact:
                intent = new Intent(getApplicationContext(), ContactUs.class);
                intent.putExtra("index", "main ");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
