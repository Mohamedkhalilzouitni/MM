package com.example.www.mm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static android.media.MediaCodec.MetricsConstants.MODE;

public class Connexion extends AppCompatActivity {

    static public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        Button ins =  findViewById(R.id.ins);

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Connexion.this,Inscription.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void Recover(View v){
        startActivity(new Intent(Connexion.this,Recover.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button conn = findViewById(R.id.connect);
        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int success = 1;
                EditText emam = findViewById(R.id.email_connect);
                String email = emam.getText().toString();
                if(email.length()==0){
                    emam.setError("Veuillez remplir ce champs!");
                    emam.requestFocus();
                    success = 0;
                }

                EditText mdpm = findViewById(R.id.mdp_connect);
                String mdp = mdpm.getText().toString();
                if(mdp.length()==0){
                    mdpm.setError("Veuillez remplir ce champs!");
                    mdpm.requestFocus();
                    success = 0;
                }

                if (success == 1){
                    BackgroundWorker bW = null;
                    try {
                        bW = new BackgroundWorker(Connexion.this);
                    } catch (Exception E) {
                        E.printStackTrace();
                    }
                    bW.execute(email,mdp,"connexion");
                    CheckBox checkBox = findViewById(R.id.checkbox);
                    if(checkBox.isChecked())
                    MainActivity.sp.edit().putBoolean("logged",true).apply();
                }else {
                    AlertDialog.Builder error = new AlertDialog.Builder(Connexion.this);
                    error.setCancelable(true);
                    error.setIcon(R.drawable.lock1);
                    error.setTitle("Erreur");
                    error.setMessage("Email ou/ et mot de passe erroné(s). Reéssayer !");
                    error.create().show();
                }
            }
        });
    }
}