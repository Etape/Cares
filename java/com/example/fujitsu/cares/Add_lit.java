package com.example.fujitsu.cares;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;

import java.time.LocalDate;

public class Add_lit extends AppCompatActivity {
     TextView result;
     EditText   chambre ;
    ImageView back_arrow,hamburger;
    Button add_button;
    FirebaseDatabaseHelper  firebaseDatabaseHelper;
    lit Lit;

    FirebaseDatabaseHelper  Firebase;
    String  usermail=getIntent().getStringExtra("email");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lit);

        final personnel Personnel=firebaseDatabaseHelper.UserParameters(usermail);
        add_button=findViewById(R.id.add_lit_but);
        result=findViewById(R.id.result);
        chambre=findViewById(R.id.chambre);
        add_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (chambre.getText().toString().isEmpty()){
                    chambre.setBackgroundColor(R.color.red);
                }
                else{
                    add_button.setClickable(false);
                    chambre.setBackgroundColor(R.color.white);
                    Lit.setID_hopital(Personnel.getEtablissement());
                    Lit.setChambre(chambre.getText().toString());
                    if (Firebase.Addlit(Lit)>0)
                        result.setText("Lit ajouté avec succès son code est : " + Lit.getId());
                    else result.setText("Probleme de connexion");
                }
            }

        });
    }
}
