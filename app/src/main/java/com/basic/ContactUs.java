package com.basic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ContactUs extends AppCompatActivity {
    ImageView orbtn,vodbtn,etbtn,emailbtn;
    TextView et,vod,or;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        etbtn=(ImageView) findViewById(R.id.etisalatcall);
        vodbtn=(ImageView) findViewById(R.id.vodafonecall);
        orbtn=(ImageView) findViewById(R.id.orangecall);
        emailbtn=(ImageView) findViewById(R.id.email);

        et=(TextView) findViewById(R.id.etisalat);
        vod=(TextView) findViewById(R.id.vodafone);
        or=(TextView) findViewById(R.id.orange);


        etbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+et.getText().toString()));
                startActivity(intent);
            }
        });
        vodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+vod.getText().toString()));
                startActivity(intent);
            }
        });
        orbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+or.getText().toString()));
                startActivity(intent);
            }
        });

        emailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(intent.EXTRA_EMAIL,new String[]{"mohamedakram153@gmail.com"});
                intent.setType("message/rfc822");
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        Intent in = getIntent();
        String service= in.getStringExtra("index");
        if(service.equals("Home"))
            intent = new Intent(getApplicationContext(), HomeVisits.class);
        else
            intent = new Intent(getApplicationContext(), MainActivity.class);
//        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
        return true;
    }
}