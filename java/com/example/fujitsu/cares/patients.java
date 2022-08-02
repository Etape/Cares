package com.example.fujitsu.cares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class patients extends AppCompatActivity {
    String    UserEmail=getIntent().getStringExtra("email");
    String  urlphotopersonnel,urlphotopatient;
    FirebaseDatabase myDB=FirebaseDatabase.getInstance();
    Query internementQuery= myDB.getReference("internement").child("date_fin").startAt(System.currentTimeMillis()/1000);
    Query suiviQuery= myDB.getReference("suivi").child("date_fin").startAt(System.currentTimeMillis()/1000);
    Query braceletQuery= myDB.getReference("bracelet").child("activité").equalTo("actif");
    Query UserQuery= myDB.getReference("personnel").child("email").equalTo(UserEmail);
    Query patientsQuery= myDB.getReference("patient");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);
    }
}
