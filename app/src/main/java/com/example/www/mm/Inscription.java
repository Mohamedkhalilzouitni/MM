package com.example.www.mm;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


        TextView conn = findViewById(R.id.conn);
        TextView inscription =  findViewById(R.id.inscription);

        Typeface Acme = Typeface.createFromAsset(this.getAssets(),"Acme-Regular.ttf");
        inscription.setTypeface(Acme);

        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Inscription.this, Connexion.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        //startActivity(new Intent(Inscription.this,MainActivity.class));
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Button ins = findViewById(R.id.ins);
//        ins.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int success = 1;
//                EditText nomm = findViewById(R.id.nom_prompt);
//                String nom = nomm.getText().toString();
//                if(nom.length()==0){
//                    nomm.setError("ce champs est obligatoire!");
//                    nomm.requestFocus();
//                    success = 0;
//                }
//                EditText pnomm =  findViewById(R.id.prenom_prompt);
//                String prenom = pnomm.getText().toString();
//                if(prenom.length()==0){
//                    pnomm.setError("ce champs est obligatoire!");
//                    pnomm.requestFocus();
//                    success = 0;
//                }
//                EditText telm =  findViewById(R.id.telephone_prompt);
//                String tel = telm.getText().toString();
//                if(tel.length()==0){
//                    telm.setError("ce champs est obligatoire!");
//                    telm.requestFocus();
//                    success = 0;
//                }
//                EditText hopm =  findViewById(R.id.hopital_prompt);
//                String hopital = hopm.getText().toString();
//                if(hopital.length()==0){
//                    hopm.setError("ce champs est obligatoire!");
//                    hopm.requestFocus();
//                    success = 0;
//                }
//                EditText emam =  findViewById(R.id.email_prompt);
//                String email = emam.getText().toString();
//                if(email.length()==0){
//                    emam.setError("ce champs est obligatoire!");
//                    emam.requestFocus();
//                    success = 0;
//                }
//                EditText inpm =  findViewById(R.id.inpe_prompt);
//                String inpe = inpm.getText().toString();
//                if(inpe.length()==0){
//                    inpm.setError("ce champs est obligatoire!");
//                    inpm.requestFocus();
//                    success = 0;
//                }
//                EditText mdpm = findViewById(R.id.mdp_prompt);
//                String mdp = mdpm.getText().toString();
//                if(mdp.length()==0){
//                    mdpm.setError("ce champs est obligatoire!");
//                    mdpm.requestFocus();
//                    success = 0;
//                }
//                EditText mpcm =  findViewById(R.id.mdp_confirm_prompt);
//                String mdpConfirm = mpcm.getText().toString();
//                if(mdpConfirm.length()==0){
//                    mpcm.setError("ce champs est obligatoire!");
//                    mpcm.requestFocus();
//                    success = 0;
//                }
//                EditText villem =  findViewById(R.id.ville_prompt);
//                String ville = villem.getText().toString();
//                if(ville.length()==0){
//                    villem.setError("ce champs est obligatoire!");
//                    villem.requestFocus();
//                    success = 0;
//                }
//
//               if ((mdp.equals(mdpConfirm)) && success == 1){
//                    BackgroundWorker bW = null;
//                    try {
//                        bW = new BackgroundWorker(Inscription.this);
//                    } catch (Exception E) {
//                        E.printStackTrace();
//                    }
//                    bW.execute(nom,prenom,"inscription",inpe,tel,hopital,email,mdp,ville);
//                }else {
//                    AlertDialog.Builder error = new AlertDialog.Builder(Inscription.this);
//                    error.setCancelable(true);
//                    error.setTitle("Erreur");
//                    error.setMessage("Veuillez entrer des informations valides");
//                    error.create().show();
//                }
//            }
//        });
//    }
}
