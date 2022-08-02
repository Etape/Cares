package com.example.fujitsu.cares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Activer_suivi extends AppCompatActivity {

    FirebaseDatabaseHelper  firebaseDatabaseHelper;
    String  usermail,patientID;

    TextView result,patient,medecin;
    AutoCompleteTextView autoCompleteCodeBracelet;
    ImageView back_arrow,hamburger;
    Button add_button;
    suivi_montre new_suivi;
    String[]    braceletCode=new String[]{};
    ArrayList<bracelet> braceletArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activer_suivi);

        autoCompleteCodeBracelet=findViewById(R.id.code_bracelet);
        result=findViewById(R.id.result);
        patient=findViewById(R.id.patient);
        medecin=findViewById(R.id.medecin);
        add_button=findViewById(R.id.add_but);

        usermail=getIntent().getStringExtra("usermail");
        patientID=getIntent().getStringExtra("patientID");
        new_suivi.setId_patient(patientID);
        personnel   Personnel=firebaseDatabaseHelper.UserParameters(usermail);
        new_suivi.setId_medecin_debut(Personnel.ID_personnel);

        braceletArrayList=firebaseDatabaseHelper.Braceletlibres(Personnel.getEtablissement());
        for (int i = 0; i <braceletArrayList.size() ; i++) {
            braceletCode[i]=braceletArrayList.get(i).getCode();
        }
        ArrayAdapter<String>   adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,braceletCode);
        if(adapter.isEmpty()){
            result.setText("aucun bracelet libre");
            add_button.setClickable(false);
        }
        autoCompleteCodeBracelet.setAdapter(adapter);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_button.setClickable(false);
                new_suivi.setDate_debut(System.currentTimeMillis()/1000);
                for (int i = 0; i <braceletArrayList.size() ; i++) {
                    if (braceletArrayList.get(i).getCode()==autoCompleteCodeBracelet.getText().toString()){
                        new_suivi.setId_bracelet(braceletArrayList.get(i).getId_bracelet());
                        break;
                    }
                }
                if(firebaseDatabaseHelper.Addsuivi(new_suivi)>0)
                result.setText("suivi active avec succes");
            }
        });
    }
}
