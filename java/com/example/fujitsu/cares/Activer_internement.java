package com.example.fujitsu.cares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activer_internement extends AppCompatActivity {
    // Write a message to the database
    FirebaseDatabaseHelper  firebaseDatabaseHelper;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("internement");
    String  usermail,patientID;

    TextView result,patient,medecin;
    EditText date_debut,date_fin;
    AutoCompleteTextView autoCompleteTraitement,autoCompleteLit;
    ImageView back_arrow,hamburger;
    Button add_button;
    internement new_intenement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activer_internement);
        usermail=getIntent().getStringExtra("usermail");
        patientID=getIntent().getStringExtra("patientID");
        medecin=findViewById(R.id.medecin);
        patient=findViewById(R.id.patient);
        com.example.fujitsu.cares.patient  Patient=firebaseDatabaseHelper.PatientParameters(patientID);
        personnel   Personnel=firebaseDatabaseHelper.UserParameters(usermail);
        medecin.setText(Personnel.getNom());
        patient.setText(Patient.getNom());

        date_debut=findViewById(R.id.date_debut);
        date_fin=findViewById(R.id.date_fin);
    }
}
