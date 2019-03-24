package com.example.www.mm;


import android.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.ListAdapter;

import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;



public class Alertes extends Activity {

   static String[][] alertes = new String[10][10]; ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alertes);

        TextView alertes_lab =  findViewById(R.id.alertes_lab);
        SpannableString content1 = new SpannableString("Alertes");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        alertes_lab.setText(content1);

        Typeface roboto = Typeface.createFromAsset(this.getAssets(),"Acme-Regular.ttf");
        alertes_lab.setTypeface(roboto);

        ImageView refAle = findViewById(R.id.refreshAlertes);
        refAle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker bW = null;
                try {
                    bW = new BackgroundWorker(Alertes.this);
                } catch (Exception E) {
                    E.printStackTrace();
                }
                bW.execute("","","alertes");
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            int size = bundle.getInt("size");
            alertes=new String[size][10];
            Object[] objectArray = (Object[]) bundle.getSerializable("alertes");
            if(objectArray!=null){
                alertes = new String[objectArray.length][];
                for(int i=0;i<objectArray.length;i++){
                    alertes[i]=(String[]) objectArray[i];
                }
            }
        } else {
            Toast.makeText(this,"Veuillez attendez SVP...",Toast.LENGTH_SHORT).show();
        }

        ListAdapter buckysAdapter = new myAdapter(this, alertes);
        ListView buckysListView = findViewById(R.id.buckysListView);
        buckysListView.setAdapter(buckysAdapter);

        buckysListView.setOnItemClickListener(

                new AdapterView.OnItemClickListener(){

                    @Override

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String[] info =(String[]) parent.getItemAtPosition(position);

                        BackgroundWorker bW = null;
                        try {
                            bW = new BackgroundWorker(Alertes.this);
                        } catch (Exception E) {
                            E.printStackTrace();
                        }
                        bW.execute(info[5],info[6],"detail_alerte",info[7]);
                    }

                }
        );
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Alertes.this,Home.class));
    }
}