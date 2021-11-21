package com.basic;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Variables {
    private int cannula;
    private int pressure;
    private int covid;
    private ArrayList<String> list=new ArrayList<String>();
    public FirebaseDatabase database = FirebaseDatabase.getInstance("https://android-f805a-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("prices");
    public int getCovid() {
        return covid;
    }

    public void setCovid(int covid) {
        this.covid = covid;
    }
    public synchronized void setCannula(int cannula) {
        System.out.println("setter:"+cannula);
        this.cannula = cannula;
    }
    public int getCannula() {
        System.out.println("getter:"+this.cannula);
        return cannula;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
    public Variables() {

        myRef.addValueEventListener(new

            ValueEventListener() {
                @Override
                public void onDataChange (@NonNull DataSnapshot snapshot){
                    System.out.println("on change");
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue().toString());
                        System.out.println(snapshot1.getValue().toString());
                    }
                    setCannula(Integer.parseInt(list.get(0)));
                    setCovid(Integer.parseInt(list.get(2)));
                    setPressure(Integer.parseInt(list.get(3)));
                    list.clear();
//                getCannula();
                /*setCovid(Integer.parseInt(list.get(2)));
                setCannula(Integer.parseInt(list.get(0)));
                setPressure(Integer.parseInt(list.get(3)));*/
                }

                @Override
                public void onCancelled (@NonNull DatabaseError error){
                    // calling on cancelled method when we receive
                    // any error or we are not able to get the data.
                }
            });
        }

    }

// Read from the database



