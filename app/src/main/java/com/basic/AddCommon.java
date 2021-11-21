package com.basic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddCommon extends AppCompatActivity {
    Button btn;
    TextInputLayout nametxt,agetxt,emailtxt,addresstxt,phonetxt;
    TextView ind;
    Spinner spinner;
    DatePicker picker;
    TimePicker tPicker;
    CheckBox fastly;
    private String service,name,age,email,address,time,area,phone, emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";;
    private int price,index,checked=0;
    private ArrayList<String> list=new ArrayList<String>();


    Calendar now = Calendar.getInstance();

    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://android-f805a-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("prices").child("visits");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_common);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0358F2"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        btn = (Button) findViewById(R.id.btn_add_item);

        nametxt=(TextInputLayout) findViewById(R.id.name) ;
        agetxt=(TextInputLayout) findViewById(R.id.age) ;
        phonetxt=(TextInputLayout) findViewById(R.id.phone) ;
        emailtxt=(TextInputLayout) findViewById(R.id.email) ;
        addresstxt=(TextInputLayout) findViewById(R.id.address) ;

        spinner=(Spinner) findViewById(R.id.spinner);

        fastly=(CheckBox) findViewById(R.id.fastCheck);

        picker=(DatePicker) findViewById(R.id.simpleDatePicker);
        tPicker=(TimePicker)findViewById(R.id.TimePicker);

        picker.setMinDate(System.currentTimeMillis());

//        tPicker.setIs24HourView(true);
        tPicker.setCurrentMinute(now.MINUTE);
        tPicker.setCurrentHour(now.HOUR);

        ind=(TextView) findViewById(R.id.in);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot){
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    list.add(snapshot1.getValue().toString());
//                    System.out.println(list.get(0));
                }
                Intent in = getIntent();
                service= in.getStringExtra("index");
                index=Integer.parseInt(in.getStringExtra("num"));
                ind.setText("خدمة " + service);
                price = Integer.parseInt(list.get(index));

                list.clear();
            }
            @Override
            public void onCancelled (@NonNull DatabaseError error){
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
            }
        });

btn.setOnClickListener(new View.OnClickListener() {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        System.out.println(nametxt.getEditText().getText().toString());
        if(isValid()) {
            init();
            AlertDialog builder = new AlertDialog.Builder(AddCommon.this)
            .setTitle("التكلفة النهائية").setMessage("هذا السعر شامل جميع الاجهزة و الادوات و المستلزمات الطبية التي يحتاجها مقدم الخدمة اثناء تأديته للخدمة"+"\n"+Integer.toString(price))
                    .setPositiveButton("إتمام العملية", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                            addItemToSheet();
                        }
                    })
                    .setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    }).create();
            builder.show();

        }
    }
});
    }
    private boolean isValid(){
        boolean valid=true;
        try {
            if(nametxt.getEditText().getText().toString().matches("")){
                valid=false;
                nametxt.setError("أكتب اسم الحالة");
                Toast.makeText(this, "عفوا أكمل البيانات الهامة", Toast.LENGTH_SHORT).show();
            }if(agetxt.getEditText().getText().toString().trim().matches("")){
                valid=false;
                agetxt.setError("أكتب العمر");
                Toast.makeText(this, "عفوا أكمل البيانات الهامة", Toast.LENGTH_SHORT).show();
            }if(addresstxt.getEditText().getText().toString().trim().matches("")) {
                valid = false;
                addresstxt.setError("أكتب العنوان بالتفصيل");
                Toast.makeText(this, "عفوا أكمل البيانات الهامة", Toast.LENGTH_SHORT).show();
            }if(spinner.getSelectedItem().toString().trim().matches("المنطقة")) {
                TextView errorText = (TextView)spinner.getSelectedView();
                valid = false;
                errorText.setError("أختر المنطقة");
                Toast.makeText(this, "عفوا أكمل البيانات الهامة", Toast.LENGTH_SHORT).show();
            }if(!emailtxt.getEditText().getText().toString().trim().matches(emailPattern) &&!emailtxt.getEditText().getText().toString().matches(""))
            { valid = false;
                Toast.makeText(this,"عفوا أكمل البيانات الهامة",Toast.LENGTH_SHORT).show();
                // or
                emailtxt.setError("أدخل حسابا صحيحا");
            }if(phonetxt.getEditText().getText().toString().trim().matches(""))
            { valid = false;
                Toast.makeText(this,"عفوا أكمل البيانات الهامة",Toast.LENGTH_SHORT).show();
                // or
                phonetxt.setError("أكتب رقم الهاتف");
            }}catch (Exception e){
            System.out.println("Failed: "+e.getMessage());
        }

        return valid;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init(){
        try {
            name = nametxt.getEditText().getText().toString().trim();
            age = agetxt.getEditText().getText().toString().trim();
            phone=phonetxt.getEditText().getText().toString().trim();
            email = emailtxt.getEditText().getText().toString().trim();
            address = addresstxt.getEditText().getText().toString().trim();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(checked==0)
            time=picker.getDayOfMonth()+"/"+(picker.getMonth()+1)+"///"+tPicker.getHour()+":"+tPicker.getMinute();
        area=spinner.getSelectedItem().toString();
        System.out.println(time);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeVisits.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        if(v==fastly) {
            if (fastly.isChecked()) {
                checked = 1;
                picker.setEnabled(false);
                tPicker.setEnabled(false);
                time = "now";
            } else {
                checked = 0;
                time = "";
                picker.setEnabled(true);
                tPicker.setEnabled(true);
            }
        }
    }

    private void addItemToSheet() {
        final ProgressDialog loading = ProgressDialog.show(this,"إتمام العملية","جارِِ تنفيذ الطلب");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbyZ1DSuA834ZBWQW5LRskrlBNONP6t9fEcFgNnA--aTnYHXee3SsnPS3lIizW5j_1T88g/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(AddCommon.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();
                //here we pass params
                parmas.put("action","addVisit");
                parmas.put("service",service);
                parmas.put("name",name);
               parmas.put("age",age);
               parmas.put("phone",phone);
                parmas.put("email",email);
                parmas.put("area",area);
                parmas.put("address",address);
                parmas.put("time",time);
                parmas.put("price",Integer.toString(price));
                System.out.println(parmas);
                return parmas;
            }

        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

        System.out.println(stringRequest);
    }
}
