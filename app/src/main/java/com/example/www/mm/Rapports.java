package com.example.www.mm;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Rapports extends AppCompatActivity {
    static String[][] rapports = new String[10][10];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapports);
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
            rapports=new String[size][5];
            Object[] objectArray = (Object[]) bundle.getSerializable("rapports");
            if(objectArray!=null){
                rapports = new String[size][];
                for(int i=0;i<size;i++){
                    rapports[i]=(String[]) objectArray[i];
                }
            }
        } else {
            Toast.makeText(this,"Veuillez attendez SVP...",Toast.LENGTH_SHORT).show();
        }
        ListAdapter khalilsAdapter = new mySecondAdapter(this, rapports);
        ListView khalilsListView = findViewById(R.id.khalilsListView);
        khalilsListView.setAdapter(khalilsAdapter);
        khalilsListView.setOnItemClickListener(
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
                        bW.execute(info[2],info[3],"detail_rapport", info[4]);
                    }

                }
        );
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Rapports.this,Home.class));
    }
}