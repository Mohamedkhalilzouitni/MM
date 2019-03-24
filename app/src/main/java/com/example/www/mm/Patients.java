package com.example.www.mm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class Patients extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        TextView textView1 =  findViewById(R.id.addpt);
        SpannableString content1 = new SpannableString("Ajouter un patient");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        textView1.setText(content1);

        TextView textView2 =  findViewById(R.id.listpt);
        SpannableString content2 = new SpannableString("Liste des patients");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        textView2.setText(content2);
    }

    public void add(View V){
       // startActivity(new Intent(Patients.this,AjouterPatient.class));
        BackgroundWorker bW = null;
        try {
            bW = new BackgroundWorker(Patients.this);
        } catch (Exception E) {
            E.printStackTrace();
        }
        bW.execute("","","medecins");
    }

    public void profils(View V){
        //  startActivity(new Intent(Patients.this,Profil.class));
        BackgroundWorker bW = null;
        try {
            bW = new BackgroundWorker(Patients.this);
        } catch (Exception E) {
            E.printStackTrace();
        }
        bW.execute("","","liste_patients");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Patients.this,Home.class));
    }
}
