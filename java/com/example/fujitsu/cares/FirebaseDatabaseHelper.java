package com.example.fujitsu.cares;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.format.DateFormat;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


public class FirebaseDatabaseHelper {
    // Write a message to the database

    private FirebaseDatabase myDB=FirebaseDatabase.getInstance();
    private FirebaseStorage myStorage=FirebaseStorage.getInstance();


    private String  uploadFile(String pathName, String Name){

        Uri file = Uri.fromFile(new File(pathName));
        final String[] url = new String[1];
        final StorageReference riversRef = myStorage.getReference("photos").child("Cares/"+Name+".jpg");

         Task<UploadTask.TaskSnapshot> uploadTask = riversRef.putFile(file);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return riversRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    url[0]=downloadUri.toString();
                }
            }
        });
        return url[0];
    }

    public String  setphoto(String  pathName,String  Name,String  previousUrl){
        String  newUrl = null;
        if (deletefile(previousUrl)==1){
          newUrl=uploadFile(pathName,Name);
        }
        return newUrl;
    }

    public int  deletefile(String url){
        final int[] result = {0};
        StorageReference ref=myStorage.getReferenceFromUrl(url);
        ref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public File getFile(String url){
        String  pathName = null;

        StorageReference riversRef = myStorage.getReference("photos").child(url);

        File localFile = null;
        try {
            localFile = File.createTempFile(url, "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        riversRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
            }
        });

        return localFile;
    }
    public int Addlit(lit  lit1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference refLit=myDB.getReference("lit");
        String Key=refLit.push().getKey();
        lit1.setId(Key);
        refLit.child(Key).setValue(lit1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Addpatient(patient  patient1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("patient");
        String Key=ref.push().getKey();
        patient1.setID_patient(Key);
        ref.child(Key).setValue(patient1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Addtraitement(traitement  traitement1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("traitement");
        String Key=ref.push().getKey();
        traitement1.setID_traitement(Key);
        ref.child(Key).setValue(traitement1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Addbracelet(bracelet  bracelet1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("bracelet");
        String Key=ref.push().getKey();
        bracelet1.setId_bracelet(Key);
        ref.child(Key).setValue(bracelet1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Addpersonnel(personnel  personnel1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("personnel");
        String Key=ref.push().getKey();
        personnel1.setID_personnel(Key);
        ref.child(Key).setValue(personnel1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Addhopital(hopital  hopital1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("hopital");
        String Key=ref.push().getKey();
        hopital1.setID_hopital(Key);
        ref.child(Key).setValue(hopital1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Addsuivi(suivi_montre  suivi){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("suivi");
        String Key=ref.push().getKey();
        suivi.setId_suivi(Key);
        ref.child(Key).setValue(suivi).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Addinternement(internement  internement1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("internement");
        String Key=ref.push().getKey();
        internement1.setID_internement(Key);
        ref.child(Key).setValue(internement1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }

    public int Setlit(lit  lit1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference refLit=myDB.getReference("lit");
        refLit.child(lit1.getId()).setValue(lit1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Setpatient(patient  patient1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("patient");
        ref.child(patient1.getID_patient()).setValue(patient1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Settraitement(traitement  traitement1){

        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("traitement");
        String Key=traitement1.getID_traitement();
        ref.child(Key).setValue(traitement1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Setbracelet(bracelet  bracelet1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("bracelet");
        String Key=bracelet1.getId_bracelet();
        ref.child(Key).setValue(bracelet1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Setpersonnel(personnel  personnel1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("personnel");
        String Key=personnel1.getID_personnel();
        ref.child(Key).setValue(personnel1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Sethopital(hopital  hopital1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("hopital");
        String Key=hopital1.getID_hopital();
        ref.child(Key).setValue(hopital1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Setsuivi(suivi_montre  suivi){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("suivi");
        String Key=suivi.getId_suivi();
        ref.child(Key).setValue(suivi).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }
    public int Setinternement(internement  internement1){
        final int[] result = new int[1];
        result[0] =0;
        DatabaseReference ref=myDB.getReference("internement");
        String Key=internement1.getID_internement();
        ref.child(Key).setValue(internement1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] =1;
            }
        });
        return result[0];
    }


    public personnel UserParameters(String UserEmail){
        final personnel[] Personnel = {null};

        Query UserQuery= myDB.getReference("personnel").child("email").equalTo(UserEmail);
        UserQuery.limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Personnel[0] =dataSnapshot.getValue(personnel.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return Personnel[0];
    }


    public patient PatientParameters(String patientID){
        final patient[] Patient = {null};

        Query UserQuery= myDB.getReference("patient").child("ID_patient").equalTo(patientID);
        UserQuery.limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Patient[0] =dataSnapshot.getValue(patient.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return Patient[0];
    }

    public hopital UserHospital(String ID_hopital){
        final hopital[] hopital = {null};

        Query UserQuery= myDB.getReference("hopital").child("ID_hopital").equalTo(ID_hopital);
        UserQuery.limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hopital[0] =dataSnapshot.getValue(hopital.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return hopital[0];
    }

    public ArrayList<traitement> HistoricTraitement(String ID_patient ){
        final ArrayList<traitement>  traitements=new ArrayList<>();
        final traitement[] traitement0 = new traitement[1];
        Query  ref=myDB.getReference("traitement").child("ID_patient").equalTo(ID_patient);
        ref.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    traitement0[0] = postSnapshot.getValue(traitement.class);
                    traitements.add(traitement0[0]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return traitements;
    }

    public ArrayList<annexe> HistoricAnnexe(String ID_patient ){
        final ArrayList<annexe>  annexes=new ArrayList<>();
        final annexe[] annexe0 = new annexe[1];
        Query  ref=myDB.getReference("annexe").child("ID_patient").equalTo(ID_patient);
        ref.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    annexe0[0] = postSnapshot.getValue(annexe.class);
                    annexes.add(annexe0[0]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return annexes;
    }

    public ArrayList<internement> HistoricInternement(String ID_patient ){
        final ArrayList<internement>  internements=new ArrayList<>();
        final internement[] internement0 = new internement[1];
        Query  ref=myDB.getReference("internement").child("ID_patient").equalTo(ID_patient);
        ref.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    internement0[0] = postSnapshot.getValue(internement.class);
                    internements.add(internement0[0]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return internements;
    }

    public ArrayList<patientsElement> FindActivePatient(){
        final ArrayList<patientsElement> PatientsElement = new ArrayList<>();

        DatabaseReference patientsQuery= myDB.getReference("patient");
        final Query internementQuery= myDB.getReference("internement").child("date_fin").startAt(System.currentTimeMillis()/1000);
        final Query suiviQuery= myDB.getReference("suivi").child("date_fin").startAt(System.currentTimeMillis()/1000);
        patientsQuery.limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    final patientsElement patientsElement = null;
                    final patient patient;
                    patient = postSnapshot.getValue(patient.class);
                    final int[] i = {0};
                    suiviQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                            suivi_montre suivi;

                            suivi=dataSnapshot2.getValue(suivi_montre.class);
                            if (suivi.getId_patient()==patient.getID_patient()) {
                                patientsElement.setPatient(patient);
                                i[0]++;
                                Query braceletQuery= myDB.getReference("bracelet").child("Code").equalTo(suivi.getId_bracelet());
                                braceletQuery.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot4) {
                                        bracelet bracelet1;
                                        bracelet1=dataSnapshot4.getValue(bracelet.class);
                                        patientsElement.setBracelet(bracelet1);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    internementQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot3) {
                            internement Internement;
                            Internement=dataSnapshot3.getValue(internement.class);
                            if (Internement.getID_patient()==patient.getID_patient()) {
                                i[0]++;
                                patientsElement.setPatient(patient);
                                Query litQuery = myDB.getReference("lit").child("Code").equalTo(Internement.getID_lit());
                                litQuery.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot5) {
                                        lit lit1;
                                        lit1 = dataSnapshot5.getValue(lit.class);
                                        patientsElement.setLit(lit1);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    if(i[0]>0){
                    PatientsElement.add(patientsElement);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return PatientsElement;
    }

    public int verifyIfExists(final String  code){
        final int[] result = {0};
        bracelet Bracelet;
        DatabaseReference  reference=myDB.getReference("bracelet");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    if (code == postSnapshot.getValue(bracelet.class).getCode()){
                        result[0] =1;
                        break;
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return result[0];
    }

    public String  CalculateCodeBr(){
        String  code=null;
        Random rndm= new Random();
        int i=rndm.nextInt(1000);
        code="BRA"+i;
        return code;
    }

    public String  CalculateCodeLit(){
        String  code=null;
        Random rndm= new Random();
        int i=rndm.nextInt(1000);
        code="LIT"+i;
        return code;
    }

    public String getDate(long time) {
        java.util.Calendar cal = java.util.Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy hh:mm", cal).toString();
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate setlongDate(long time) {
        LocalDate  date = null;
        date.atTime(LocalTime.ofSecondOfDay(time));
        return date;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getAge(long birthtime){
        LocalDate birth = null;
        LocalDate today = null;
            birth=setlongDate(birthtime);
            today=setlongDate(System.currentTimeMillis()/1000);
            return Period.between(birth,today).getYears();

    }


}
