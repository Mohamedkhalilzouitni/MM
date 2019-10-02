package com.example.www.mm;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Rapports extends AppCompatActivity {
    ArrayList<String[]> rapports = new ArrayList<>();
    Button searchR;
    EditText searchAreaR;
    ListView khalilsList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapports);

        searchR = findViewById(R.id.searchRBtn);
        searchAreaR = findViewById(R.id.searchR);
        khalilsList = findViewById(R.id.khalilsListView);

        searchR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchR();
            }
        });

        searchR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mySecondAdapter khalilsAdapter = new mySecondAdapter(Rapports.this, rapports);
                initList(khalilsAdapter);
            }
        });

        TextView inscription =  findViewById(R.id.rapports_lab);
        SpannableString content11 = new SpannableString("Rapports");
        content11.setSpan(new UnderlineSpan(), 0, content11.length(), 0);
        inscription.setText(content11);

        Typeface Acme = Typeface.createFromAsset(this.getAssets(),"Acme-Regular.ttf");
        inscription.setTypeface(Acme);


        ImageView refRap = findViewById(R.id.refreshRapports);

        refRap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker bW = null;
                try {
                    bW = new BackgroundWorker(Rapports.this);
                } catch (Exception E) {
                    E.printStackTrace();
                }
                bW.execute("","","rapports");
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            int size = bundle.getInt("size");
            Object[] objectArray = (Object[]) bundle.getSerializable("rapports");
            if(objectArray!=null){
                for(int i=0;i<size;i++){
                    rapports.add((String[]) objectArray[i]);
                }
            }
        } else {
            Toast.makeText(this,"Veuillez attendez SVP...",Toast.LENGTH_SHORT).show();
        }

        mySecondAdapter khalilsAdapter = new mySecondAdapter(this, rapports);
        initList(khalilsAdapter);

    }

    public void searchR() {
        String al = searchAreaR.getText().toString();
        try {
            ArrayList<String[]> selectedAlerts = new ArrayList<>();
            for (String[] rapport : rapports){
                if(rapport[4].toLowerCase().contains(al.toLowerCase())){
                    selectedAlerts.add(rapport);
                    System.out.println(rapport[2]);
                }
            }

            mySecondAdapter pf = new mySecondAdapter(Rapports.this,selectedAlerts);
            initList(pf);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void initList(mySecondAdapter pf) {
        khalilsList = findViewById(R.id.khalilsListView);
        khalilsList .setAdapter(pf);
        khalilsList .setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String[] info =(String[]) parent.getItemAtPosition(position);
                        BackgroundWorker bW = null;
                        try {
                            bW = new BackgroundWorker(Rapports.this);
                        } catch (Exception E) {
                            E.printStackTrace();
                        }
                        bW.execute(info[0],info[3],"detail_rapport");
                    }

                }
        );
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Rapports.this,Home.class));
    }
}