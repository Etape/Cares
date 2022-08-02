package com.example.fujitsu.cares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Add_hopital extends AppCompatActivity {

    FirebaseDatabaseHelper  firebaseDatabaseHelper;
    String  usermail,patientID;

    TextView result;
    EditText nom,pays,ville,quartier,email,telephone1,telephone2;
    ImageView back_arrow,hamburger;
    Button add_button;
    hopital new_hopital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hopital);
        result=findViewById(R.id.result);
        nom=findViewById(R.id.nom);
        pays=findViewById(R.id.pays);
        ville=findViewById(R.id.ville);
        quartier=findViewById(R.id.quartier);
        email=findViewById(R.id.email);
        telephone1=findViewById(R.id.telephone1);
        telephone2=findViewById(R.id.telephone2);
        add_button=findViewById(R.id.add_but);

        if (nom.getText().toString().isEmpty()||pays.getText().toString().isEmpty()||quartier.getText().toString().isEmpty()||ville.getText().toString().isEmpty()){
            result.setText("remplissez correctement les champs  obligatoires");
        }
        else if(telephone1.getText().toString().length()<9||(telephone2.getText().toString().length()>0 && telephone2.getText().toString().length()<9)){
            result.setText("veuillez remplir correctement les numeros de telephones");
        }
        else {
            add_button.setClickable(false);
            new_hopital=new hopital(nom.getText().toString(),pays.getText().toString(),ville.getText().toString(),quartier.getText().toString(),email.getText().toString()
            ,telephone1.getText().toString(),telephone2.getText().toString());
            if(firebaseDatabaseHelper.Addhopital(new_hopital)>0)
            result.setText("hopital ajouté avec succes");
        }
    }
}
