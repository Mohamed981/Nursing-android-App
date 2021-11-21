package com.basic;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeVisits extends AppCompatActivity implements View.OnClickListener{
    private ImageButton pressureImg,diabeticImg,breathImg,breath2Img,pulseImg,bedImg,oneImg,happyImg,transImg,teezImg,armImg,handImg,polaImg,pencImg,sharagImg,legImg,qurhaImg,tadlekImg,anboobImg,canuImg,darnaqaImg,pamperzImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_visits);

        pressureImg=(ImageButton)findViewById(R.id.pressureBtn);
        diabeticImg=(ImageButton)findViewById(R.id.diabeticBtn);
        breathImg=(ImageButton)findViewById(R.id.breathBtn);
        breath2Img=(ImageButton)findViewById(R.id.breath2Btn);
        pulseImg=(ImageButton)findViewById(R.id.pulseBtn);
        bedImg=(ImageButton)findViewById(R.id.bedBtn);
        oneImg=(ImageButton)findViewById(R.id.oneBtn);
        happyImg=(ImageButton)findViewById(R.id.happyBtn);
        transImg=(ImageButton)findViewById(R.id.transBtn);
        teezImg=(ImageButton)findViewById(R.id.teezBtn);
        armImg=(ImageButton)findViewById(R.id.armBtn);
        handImg=(ImageButton)findViewById(R.id.handBtn);
        polaImg=(ImageButton)findViewById(R.id.polaBtn);
        pencImg=(ImageButton)findViewById(R.id.pencBtn);
        sharagImg=(ImageButton)findViewById(R.id.sharagBtn);
        legImg=(ImageButton)findViewById(R.id.legBtn);
        qurhaImg=(ImageButton)findViewById(R.id.qurhaBtn);
        tadlekImg=(ImageButton)findViewById(R.id.tadlekBtn);
        anboobImg=(ImageButton)findViewById(R.id.anboobBtn);
        canuImg=(ImageButton)findViewById(R.id.canuBtn);
        darnaqaImg=(ImageButton)findViewById(R.id.darnaqaBtn);
        pamperzImg=(ImageButton)findViewById(R.id.pamprzBtn);

        pressureImg.setOnClickListener((View.OnClickListener) this);
        diabeticImg.setOnClickListener((View.OnClickListener) this);
        breathImg.setOnClickListener((View.OnClickListener) this);
        breath2Img.setOnClickListener((View.OnClickListener) this);
        pulseImg.setOnClickListener((View.OnClickListener) this);
        bedImg.setOnClickListener((View.OnClickListener) this);
        oneImg.setOnClickListener((View.OnClickListener) this);
        happyImg.setOnClickListener((View.OnClickListener) this);
        transImg.setOnClickListener((View.OnClickListener) this);
        teezImg.setOnClickListener((View.OnClickListener) this);
        armImg.setOnClickListener((View.OnClickListener) this);
        handImg.setOnClickListener((View.OnClickListener) this);
        polaImg.setOnClickListener((View.OnClickListener) this);
        pencImg.setOnClickListener((View.OnClickListener) this);
        sharagImg.setOnClickListener((View.OnClickListener) this);
        legImg.setOnClickListener((View.OnClickListener) this);
        qurhaImg.setOnClickListener((View.OnClickListener) this);
        tadlekImg.setOnClickListener((View.OnClickListener) this);
        anboobImg.setOnClickListener((View.OnClickListener) this);
        canuImg.setOnClickListener((View.OnClickListener) this);
        darnaqaImg.setOnClickListener((View.OnClickListener) this);
        pamperzImg.setOnClickListener((View.OnClickListener) this);


        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("زيارات منزلية");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0358F2"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                intent = new Intent(getApplicationContext(), AboutUs.class);
                intent.putExtra("index", "Home");
                startActivity(intent);
                return true;
            case R.id.contact:
                intent = new Intent(getApplicationContext(), ContactUs.class);
                intent.putExtra("index", "Home");
                startActivity(intent);
                return true;
            default:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==pressureImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "قياس ضغط" );
            intent.putExtra("num", "0");
            startActivity(intent);
        }else if(v==diabeticImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "تحليل سكر عشوائى" );
            intent.putExtra("num", "1");
            startActivity(intent);
        }else if(v==breathImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "جلسة تنفس أكسجين" );
            intent.putExtra("num", "2");
            startActivity(intent);
        }else if(v==breath2Img){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "جلسة تنفس نبوليزر" );
            intent.putExtra("num", "3");
            startActivity(intent);
        }else if(v==pulseImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "قياس نسبة الأكسجين بالدم" );
            intent.putExtra("num", "4");
            startActivity(intent);
        }else if(v==bedImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "تعليق محلول وريدى" );
            intent.putExtra("num", "5");
            startActivity(intent);
        }else if(v==oneImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "تعليق محلول وريدى أكثر من ساعة" );
            intent.putExtra("num", "6");
            startActivity(intent);
        }else if(v==happyImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "تعليق محلول حديد" );
            intent.putExtra("num", "7");
            startActivity(intent);
        }else if(v==transImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "نقل دم و بلازما" );
            intent.putExtra("num", "8");
            startActivity(intent);
        }else if(v==teezImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "حقنة عضلية" );
            intent.putExtra("num", "9");
            startActivity(intent);
        }else if(v==armImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "حقنة تحت الجلد" );
            intent.putExtra("num", "10");
            startActivity(intent);
        }else if(v==handImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "حقنة وريدية" );
            intent.putExtra("num", "11");
            startActivity(intent);
        }else if(v==polaImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "حقنة أدمية أو إختبار حساسية" );
            intent.putExtra("num", "12");
            startActivity(intent);
        }else if(v==pencImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "حقنة مضاد حيوى" );
            intent.putExtra("num", "13");
            startActivity(intent);
        }else if(v==sharagImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "حقنة شرجية" );
            intent.putExtra("num", "14");
            startActivity(intent);
        }else if(v==legImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "غيار على الجروح" );
            intent.putExtra("num", "15");
            startActivity(intent);
        }else if(v==qurhaImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "غيار على قرحة الفراش" );
            intent.putExtra("num", "16");
            startActivity(intent);
        }else if(v==tadlekImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "غيار على قدم سكرى" );
            intent.putExtra("num", "17");
            startActivity(intent);
        }else if(v==anboobImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "تركيب أنبوبة معدة" );
            intent.putExtra("num", "18");
            startActivity(intent);
        }else if(v==canuImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "تركيب قسطرة بولية" );
            intent.putExtra("num", "19");
            startActivity(intent);
        }else if(v==darnaqaImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "فتح إنسداد القسطرة البولية" );
            intent.putExtra("num", "20");
            startActivity(intent);
        }else if(v==pamperzImg){
            Intent intent = new Intent(getApplicationContext(), AddCommon.class);
            intent.putExtra("index", "نظافة الجزء السفلى للمريض" );
            intent.putExtra("num", "21");
            startActivity(intent);
        }
    }
}
