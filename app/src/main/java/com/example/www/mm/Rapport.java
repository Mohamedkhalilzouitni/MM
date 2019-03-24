package com.example.www.mm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Rapport extends AppCompatActivity {

     String[][] rapport = new String[4][3];
    TextView dater,npr,ndr,symptom1,grade1,symptom2,grade2,symptom3,grade3,symptom4,grade4;
     String nom,prenom,num_dossier,tel,date;
     int id_patient,id_rapport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport);

        TextView inscription =  findViewById(R.id.rapport_label);

        Typeface Acme = Typeface.createFromAsset(this.getAssets(),"Acme-Regular.ttf");
        inscription.setTypeface(Acme);

        dater = findViewById(R.id.dater);
        npr = findViewById(R.id.npr);
        ndr = findViewById(R.id.ndr);
        symptom1 = findViewById(R.id.symptom1);
        grade1 = findViewById(R.id.grades1);
        symptom2 = findViewById(R.id.symptom2);
        grade2 = findViewById(R.id.grades2);
        symptom3 = findViewById(R.id.symptom3);
        grade3 = findViewById(R.id.grades3);
        symptom4 = findViewById(R.id.symptom4);
        grade4 = findViewById(R.id.grades4);

        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            id_rapport = b.getInt("id_rapport");
            id_patient = b.getInt("id_patient");
            nom = b.getString("nom");
            prenom = b.getString("prenom");
            num_dossier = b.getString("num_dossier");
            tel = b.getString("tel");
            date = b.getString("date");
            int size = b.getInt("size");

            rapport=new String[size][3];
            Object[] objectArray = (Object[]) b.getSerializable("rapport");
            if(objectArray!=null){
                rapport = new String[objectArray.length][2];
                for(int i=0;i<objectArray.length;i++){
                    rapport[i]=(String[]) objectArray[i];
                }
            }
            dater.setText(date);
            npr.setText(nom+" "+prenom);
            ndr.setText(num_dossier);



            if (size==4){
                if (rapport[0][0] != null){
                    symptom1.setText(rapport[0][0]);
                    grade1.setText(rapport[0][1]);
                }
                if (rapport[1][0] != null){
                    symptom2.setText(rapport[1][0]);
                    grade2.setText(rapport[1][1]);
                }
                if (rapport[2][0] != null){
                    symptom3.setText(rapport[2][0]);
                    grade3.setText(rapport[2][1]);
                }
                if (rapport[3][0] != null){
                    symptom4.setText(rapport[3][0]);
                    grade4.setText(rapport[3][1]);
                }
            }else if (size==3){
            if (rapport[0][0] != null){
                symptom1.setText(rapport[0][0]);
                grade1.setText(rapport[0][1]);
            }
            if (rapport[1][0] != null){
                symptom2.setText(rapport[1][0]);
                grade2.setText(rapport[1][1]);
            }
            if (rapport[2][0] != null){
                symptom3.setText(rapport[2][0]);
                grade3.setText(rapport[2][1]);
            }
        }else if (size==2){
            if (rapport[0][0] != null){
                symptom1.setText(rapport[0][0]);
                grade1.setText(rapport[0][1]);
            }
            if (rapport[1][0] != null){
                symptom2.setText(rapport[1][0]);
                grade2.setText(rapport[1][1]);
            }
        }else if (size==1){
            if (rapport[0][0] != null){
                symptom1.setText(rapport[0][0]);
                grade1.setText(rapport[0][1]);
            }
        }

        } else {
            Toast.makeText(this,"Veuillez attendez SVP...",Toast.LENGTH_SHORT).show();
        }

        ImageView appelp =  findViewById(R.id.appelpic);
        appelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appel();
            }
        });

        ImageView supp =  findViewById(R.id.supprimerrapport);
        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimer();
            }
        });

        ImageView profilp =  findViewById(R.id.profilepic);
        profilp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker bW = null;
                try {
                    bW = new BackgroundWorker(Rapport.this);
                } catch (Exception E) {
                    E.printStackTrace();
                }
                bW.execute(String.valueOf(id_patient)," ","detail_profil");
            }
        });
    }

    public void supprimer(){

        BackgroundWorker bW = null;
        try {
            bW = new BackgroundWorker(Rapport.this);
        } catch (Exception E) {
            E.printStackTrace();
        }
        bW.execute(String.valueOf(id_rapport)," ","supprimer_rapport");
    }

    public void appel(){
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+tel));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Veuillez permettre l'application d'effectuer l'appel",Toast.LENGTH_SHORT).show();
            requestPermission();
        }else {
            startActivity(i);
        }
    }

    public void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
