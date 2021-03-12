package com.example.www.mm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    static public String nom, prenom;
   // String aN,rN;
    TextView infoDr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        infoDr = findViewById(R.id.Info_Dr);

        Intent intent = getIntent();
            Bundle bd = getIntent().getExtras();
            if( bd != null){
                nom = bd.getString("nom");
                prenom = bd.getString("prenom");
            }
//
//        nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
//        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
//        MainActivity.sp.edit().putString("nom",nom).apply();
//        MainActivity.sp.edit().putString("prenom",nom).apply();

        SpannableString infDr = new SpannableString("Dr. " + nom + " " + prenom);
        infDr.setSpan(new UnderlineSpan(),0,infDr.length(),0);
        infoDr.setText(infDr);

        TextView a = findViewById(R.id.alertes);
        TextView r = findViewById(R.id.rapports);
        TextView l = findViewById(R.id.patients);
        TextView dd = findViewById(R.id.dis);
        ImageView d = findViewById(R.id.disconnect);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker bW = null;
                try {
                    bW = new BackgroundWorker(Home.this);
                } catch (Exception E) {
                    E.printStackTrace();
                }
                bW.execute("","","alertes");

            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker bW = null;
                try {
                    bW = new BackgroundWorker(Home.this);
                } catch (Exception E) {
                    E.printStackTrace();
                }
                bW.execute("","","rapports");
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Patients.class));
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sp.edit().clear();
                MainActivity.sp.edit().putBoolean("logged",false).apply();
                MainActivity.sp.edit().commit();
                Intent i = new Intent(Home.this,Connexion.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });

        dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.sp.edit().clear();
                MainActivity.sp.edit().putBoolean("logged",false).apply();
                MainActivity.sp.edit().commit();
                Intent i = new Intent(Home.this,Connexion.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
