package com.example.www.mm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Alerte extends AppCompatActivity {

    TextView datea,np,nd,symptom,grade;
     static int id_patient,id_rapport,gradea,id_alerte;
     static String nom,prenom,num_dossier,tel,date,nom_symptom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerte);

        TextView alertes_lab =  findViewById(R.id.alerte_label);
        SpannableString content1 = new SpannableString("Alerte");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        alertes_lab.setText(content1);

        Typeface roboto = Typeface.createFromAsset(this.getAssets(),"Acme-Regular.ttf");
        alertes_lab.setTypeface(roboto);

        datea = findViewById(R.id.dateal);
        np = findViewById(R.id.np);
        nd = findViewById(R.id.nd);
        symptom = findViewById(R.id.syma);
        grade = findViewById(R.id.gra);

        Bundle b = getIntent().getExtras();
        if (b != null)
        {
           id_rapport = b.getInt("id_rapport");
           id_patient = b.getInt("id_patient");
           id_alerte = b.getInt("id_alerte");
            nom = b.getString("nom");
            prenom = b.getString("prenom");
           num_dossier = b.getString("num_dossier");
           tel = b.getString("tel");
           gradea = b.getInt("grade");
           date = b.getString("date");
           nom_symptom = b.getString("nom_symptom");
            if (date != null){
                datea.setText(date);
               // datea.setText("tt");
            }
            if ((nom != null) && (prenom != null)){
                np.setText(nom+" "+prenom);
              //  np.setText("yy");
            }
            if (num_dossier != null){
                nd.setText(num_dossier);
               // nd.setText("mm");
            }
            if (nom_symptom != null){
               symptom.setText(nom_symptom);
               // symptom.setText("ll");
            }
            if (gradea != -1){
                grade.setText(String.valueOf(gradea));
               // grade.setText("uu");
            }
        } else {
            Toast.makeText(this,"Veuillez attendez SVP...",Toast.LENGTH_SHORT).show();
        }

        TextView profil_t =  findViewById(R.id.profil_text);
        SpannableString content2 = new SpannableString("Profil");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        profil_t.setText(content2);

        profil_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile(v);
            }
        });

        ImageView profilp =  findViewById(R.id.profil_pic);
        profilp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               profile(v);
            }
        });

        TextView rapport_t =  findViewById(R.id.rapport_text);
        SpannableString content3 = new SpannableString("Rapport");
        content3.setSpan(new UnderlineSpan(), 0, content3.length(), 0);
        rapport_t.setText(content3);

        rapport_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rapport(v);
            }
        });

        ImageView rapportp =  findViewById(R.id.rapport_pic);
        rapportp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rapport(v);
            }
        });

        TextView suppr_t =  findViewById(R.id.supprimer_text);
        SpannableString content4 = new SpannableString("Supprimer alerte");
        content4.setSpan(new UnderlineSpan(), 0, content4.length(), 0);
        suppr_t.setText(content4);

        suppr_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimer(v);
            }
        });

        ImageView suppp =  findViewById(R.id.suprimer_pic);
        suppp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimer(v);
            }
        });

        TextView appel_t =  findViewById(R.id.appel_text);
        SpannableString content5 = new SpannableString("Appeler patient");
        content5.setSpan(new UnderlineSpan(), 0, content5.length(), 0);
        appel_t.setText(content5);

        appel_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appel();
            }
        });

        ImageView appelp =  findViewById(R.id.appel_pic);
        appelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appel();
            }
        });
    }

    public void rapport(View v){
        BackgroundWorker bW = null;
        try {
            bW = new BackgroundWorker(Alerte.this);
        } catch (Exception E) {
            E.printStackTrace();
        }
        bW.execute(String.valueOf(id_patient),String.valueOf(id_rapport),"detail_rapport");
    }

    public void supprimer(View v){

        BackgroundWorker bW = null;
        try {
            bW = new BackgroundWorker(Alerte.this);
        } catch (Exception E) {
            E.printStackTrace();
        }
        bW.execute(String.valueOf(id_alerte)," ","supprimer_alerte");
    }

    public void profile(View v){

        BackgroundWorker bW = null;
        try {
            bW = new BackgroundWorker(Alerte.this);
        } catch (Exception E) {
            E.printStackTrace();
        }
        bW.execute(String.valueOf(id_patient)," ","detail_profil");
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
