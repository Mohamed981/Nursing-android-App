package com.basic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class AddSpecial  extends AppCompatActivity implements View.OnClickListener{
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://android-f805a-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("prices");

    private RadioButton maleBtn,femaleBtn;

    private int gender,price,totalPrice;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String shift,service,index,url,name,age,email,diagnosis,duration,address,phone,diseases,area;
    private final ArrayList<String> areasRich=new ArrayList<>(Arrays.asList("رشدى", "كفر عبدو", "سموحة"));
    private final ArrayList<String>  areasMedium=new ArrayList<>(Arrays.asList("سيدى بشر", "العجمى", "المنشية"));
    private final ArrayList<String>  areasPoor=new ArrayList<>(Arrays.asList("باكوس", "الظاهرية", "عوايد"));
    private ArrayList<String> list=new ArrayList<String>();

    Button btn,uploadbtn;
    TextInputLayout nametxt,agetxt,emailtxt,addresstxt,phonetxt,diagnosistxt,durationtxt;
    TextView ind,diseasestxt;
    Spinner areaspinner,shiftspinner;


    String[] disArray={"أمراض جلدية معدية","أمراض قلب\n (مثل ارتجاع الصمام المترالي...)","أمراض بالكلى و المسالك البولية\n(مثل الحصاوى أو تضخم البروستاتا...)","أمراض نفسية\n(مثل فصام أو اضطراب ثنائى القطب...)","أمراض تنفسية معدية\n(مثل الربو...)","أمراض كبدية","إلتهاب سحائى","إيدز","تصلب الشرايين","زهايمر","سكر","سكتة دماغية سابقة","ضغط","فيروس كبدى مثل A,B,C","مشاكل سابقة بالعظام أو الغضاريف أدت الى إجراء عمليات جراحية\n(مثل تركيب شرائح و مسامير...)"};
    ArrayList<Integer> dayList=new ArrayList<>(13);
    boolean[] selectedDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_special);
// calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0358F2"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        btn = (Button) findViewById(R.id.btn_add_item);
        uploadbtn=(Button)findViewById(R.id.upload);

        nametxt=(TextInputLayout) findViewById(R.id.name) ;
        agetxt=(TextInputLayout) findViewById(R.id.age) ;
        emailtxt=(TextInputLayout) findViewById(R.id.email) ;
        addresstxt=(TextInputLayout) findViewById(R.id.address) ;
        phonetxt=(TextInputLayout) findViewById(R.id.phone) ;
        durationtxt=(TextInputLayout) findViewById(R.id.duration) ;
        areaspinner=(Spinner) findViewById(R.id.spinner);
        shiftspinner=(Spinner) findViewById(R.id.spinner2);

        maleBtn=(RadioButton) findViewById(R.id.maleBtn);
        femaleBtn=(RadioButton) findViewById(R.id.femaleBtn);

        femaleBtn.setOnClickListener((View.OnClickListener) this);
        maleBtn.setOnClickListener((View.OnClickListener) this);



        ind=(TextView) findViewById(R.id.in);
        diseasestxt=(TextView) findViewById(R.id.diseases);
        selectedDay=new boolean[disArray.length];
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(intent.EXTRA_SUBJECT,nametxt.getEditText().getText().toString().trim());
                intent.putExtra(intent.EXTRA_EMAIL,new String[]{"mohamedakram153@gmail.com"});
                intent.setType("message/rfc822");
                startActivity(intent);
            }
        });
diseasestxt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(
                AddSpecial.this
        );
        builder.setTitle("أمراض مصاحبة");
        builder.setCancelable(false);
        builder.setMultiChoiceItems(disArray, selectedDay, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    System.out.println(which);
                    dayList.add(which);
                    Collections.sort(dayList);
                }else{
                    try{
//                    dayList.remove(which);
                        dayList.remove(Integer.valueOf(which));
                }catch (Exception e){
                        System.out.println("Failed:"+e.getMessage());
                    }
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder stringBuilder=new StringBuilder();
                for(int i=0;i<dayList.size();i++){
                    stringBuilder.append(disArray[dayList.get(i)]);
                    if(i!=dayList.size()-1){
                        stringBuilder.append(", ");
                    }
                }
                diseasestxt.setText(stringBuilder.toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.clear();
                for(int i=0;i<selectedDay.length;i++){
                    selectedDay[i]=false;
                    dayList.clear();
                    diseasestxt.setText("");
                }
            }
        });
        builder.show();
    }
});

        Intent in = getIntent();
        index = in.getStringExtra("index");
        ind.setText("Service "+index);
        System.out.println(index);
        if (index.equals("Covid")) {
//            System.out.println(list.get(3));
            service="covid";
//            url=list.get(3);
        index="addCovid";
        }else {
            service="care";
//            url="https://script.google.com/macros/s/AKfycbyxQ-rhwh2XUgtIA4yr78sHmwtdbOqDQn9yrqj6KpNjuk69Q20PNx3OlWnHTHtBeadf/exec";
            index="addCare";
        }
        myRef.child(service).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot){
//                System.out.println("on change");

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    list.add(snapshot1.getValue().toString());
                    System.out.println(list.get(0));
                }
                url=list.get(3);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError error){
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
            }
        });

totalPrice=price;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    init();
//                    int additionalPrice=add(area);
                    totalPrice=price * Integer.parseInt(durationtxt.getEditText().getText().toString());
                    //pricetxt.setText(Integer.toString(totalPrice));
//                        addItemToSheet();
                    AlertDialog builder = new AlertDialog.Builder(AddSpecial.this)
                            .setTitle("التكلفة النهائية").setMessage("هذا السعر شامل جميع الاجهزة و الادوات و المستلزمات الطبية التي يحتاجها مقدم الخدمة اثناء تأديته الخدمة"+"\n"+Integer.toString(totalPrice))
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
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
    @Override
    public void onClick(View v) {
        if(v==femaleBtn){
          this.gender=1;
            //Define what to do when button is clicked
        }else if(v==maleBtn)
            this.gender=0;
    }

    private boolean isValid(){
        boolean valid=true;
        if(nametxt.getEditText().getText().toString().trim().matches("")){
            valid=false;
            nametxt.setError("أكتب اسم الحالة");
            Toast.makeText(this, "عفوا أكمل البيانات الهامة", Toast.LENGTH_SHORT).show();
        }if(agetxt.getEditText().getText().toString().trim().matches("")){
            valid=false;
            agetxt.setError("أكتب العمر");
            Toast.makeText(this, "عفوا أكمل البيانات الهامة", Toast.LENGTH_SHORT).show();
        }if(durationtxt.getEditText().getText().toString().trim().matches("")){
            valid=false;
            durationtxt.setError("أكتب عدد أيام التغطية");
            Toast.makeText(this, "عفوا أكمل البيانات الهامة", Toast.LENGTH_SHORT).show();
        }if(addresstxt.getEditText().getText().toString().trim().matches("")) {
            valid = false;
            addresstxt.setError("أكتب العنوان بالتفصيل");
            Toast.makeText(this, "عفوا أكمل البيانات الهامة", Toast.LENGTH_SHORT).show();
        }if(areaspinner.getSelectedItem().toString().trim().matches("المنطقة")) {
            TextView errorText = (TextView)areaspinner.getSelectedView();
            valid = false;
            errorText.setError("أختر المنطقة");
            Toast.makeText(this, "عفوا أكمل البيانات الهامة", Toast.LENGTH_SHORT).show();
        }if (!emailtxt.getEditText().getText().toString().trim().matches(emailPattern) &&!emailtxt.getEditText().getText().toString().matches(""))
        { valid = false;
            Toast.makeText(this,"عفوا أكمل البيانات الهامة",Toast.LENGTH_SHORT).show();
            // or
            emailtxt.setError("أدخل حسابا صحيحا");
        }

        return valid;
    }

void init(){
    this.name = nametxt.getEditText().getText().toString().trim();
    this.age = agetxt.getEditText().getText().toString().trim();
    this.email = emailtxt.getEditText().getText().toString().trim();
    this.duration = durationtxt.getEditText().getText().toString().trim();
    this.address=addresstxt.getEditText().getText().toString().trim();
    this.phone = phonetxt.getEditText().getText().toString().trim();
    this.diseases = diseasestxt.getText().toString().trim();
    this.area=areaspinner.getSelectedItem().toString();
    this.shift=shiftspinner.getSelectedItem().toString();
    if (shift.contains("۲٤")) {
        price = Integer.parseInt(list.get(2));
    }else if(shift.contains("۱۲")) {
        price = Integer.parseInt(list.get(1));
    }else if(shift.contains("٦")) {
        price = Integer.parseInt(list.get(0));
    }
//    list.clear();
}

    private void addItemToSheet(){

        final ProgressDialog loading = ProgressDialog.show(this,"جارٍ تنفيذ الطلب","إنتظر من فضلك");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, list.get(3).toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(AddSpecial.this,response,Toast.LENGTH_LONG).show();
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
                parmas.put("action",index);
                parmas.put("name",name);
                parmas.put("age",age);
                parmas.put("phone",phone);
                parmas.put("email",email);
                parmas.put("type",Integer.toString(gender));
                parmas.put("area",area);
                parmas.put("address",address);
                parmas.put("diseases",diseases);
                parmas.put("duration",duration);
                parmas.put("shift",shift);
                parmas.put("price",Integer.toString(totalPrice));

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
