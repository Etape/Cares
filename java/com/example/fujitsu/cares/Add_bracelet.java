package com.example.fujitsu.cares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;

import java.io.Serializable;

public class Add_bracelet extends AppCompatActivity {
    bracelet    Bracelet=null;
    TextView    result;
    ImageView   back_arrow,hamburger;
    Button      add_button;

    FirebaseDatabaseHelper  Firebase;
    String  usermail=getIntent().getStringExtra("email");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bracelet);

        add_button=findViewById(R.id.add_but);
        result=findViewById(R.id.result);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_button.setClickable(false);
                int i=1;
                String  code = "";
                while (i==1){
                    code=Firebase.CalculateCodeBr();
                    i=Firebase.verifyIfExists(code);
                }
                Bracelet.setCode(code);
                try {
                    Bracelet.setEtablissement(Firebase.UserHospital(Firebase.UserParameters(usermail).getEtablissement()).getID_hopital());
                    if (Firebase.Addbracelet(Bracelet)>0)
                        result.setText("Bracelet ajouté avec succès son code est : " + code);
                    else result.setText("Probleme de connexion");
                } catch (Exception e) {
                    e.printStackTrace();
                    result.setText("Probleme de connexion");
                }

            }
        });
    }
}
