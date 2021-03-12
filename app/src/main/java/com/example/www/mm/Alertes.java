package com.example.www.mm;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Alertes extends Activity {

   ArrayList<String[]> alertes = new ArrayList<>();
   ImageView supprimer;
   Button searchA;
   EditText searchArea;
   ListView buckysListView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alertes);
        supprimer = findViewById(R.id.supprimer);
        searchA = findViewById(R.id.searchABtn);
        searchArea = findViewById(R.id.searchA);
        buckysListView = findViewById(R.id.buckysListView);

        searchA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchA();
            }
        });

        searchA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                myAdapter pf = new myAdapter(Alertes.this,alertes);
                initList(pf);
            }
        });

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
            Object[] objectArray = (Object[]) bundle.getSerializable("alertes");
            if(objectArray!=null){
                for(int i=0;i< size; i++){
                    alertes.add((String[]) objectArray[i]);
                }
            }
        } else {
            Toast.makeText(this,"Veuillez attendez SVP...",Toast.LENGTH_SHORT).show();
        }
        myAdapter pf = new myAdapter(Alertes.this,alertes);
        initList(pf);
        supprimer.setOnClickListener(new View.OnClickListener() {
            BackgroundWorker bW = null;
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirm = new AlertDialog.Builder(Alertes.this);
                confirm.setCancelable(true);
                confirm.setIcon(R.drawable.confirm);
                confirm.setTitle("Confirmer ?");
                confirm.setMessage("Etes-vous sûr de supprimer tous les alertes ?");
                confirm.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            bW = new BackgroundWorker(Alertes.this);
                        } catch (Exception E) {
                            E.printStackTrace();
                        }
                        bW.execute("","","supprimerTousAlertes");
                    }
                })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                });
                confirm.create().show();
            }
        });
    }

    public void searchA() {
        String al = searchArea.getText().toString();
        try {
            ArrayList<String[]> selectedAlerts = new ArrayList<>();
            for (String[] alert : alertes){
                if(alert[3].toLowerCase().contains(al.toLowerCase())){
                    selectedAlerts.add(alert);
                    System.out.println(alert[2]);
                }
            }

            myAdapter pf = new myAdapter(Alertes.this,selectedAlerts);
            initList(pf);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void initList(myAdapter pf) {
        buckysListView.setAdapter(pf);
        buckysListView.setTextFilterEnabled(true);
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