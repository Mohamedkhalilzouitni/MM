package com.example.www.mm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static public SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        boolean logged = sp.getBoolean("logged",false);
        if(logged){
            String nom = sp.getString("nom","");
            String prenom = sp.getString("prenom","");
            Intent intent = new Intent(MainActivity.this, Home.class);
            Bundle b = new Bundle();
            b.putString("nom",nom);
            b.putString("prenom",prenom);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }

        TextView textView1 =  findViewById(R.id.inscription);
        TextView textView2 =  findViewById(R.id.connexion);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Inscription.class));
                finish();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Connexion.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
