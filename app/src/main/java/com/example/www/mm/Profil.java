package com.example.www.mm;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Profil extends AppCompatActivity {

    ArrayList<String[]> profils = new ArrayList<>();
    String nom,prenom;
    EditText search;
    int size;
    static ListAdapter khalilsAdapter;
    ListView khalilsListView;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        final HashMap<String,Profil> profiles = new HashMap<>();
        Bundle bundle = getIntent().getExtras();
        search = findViewById(R.id.search);
        searchBtn = findViewById(R.id.searchBtn);

        if (bundle != null) {
            size = bundle.getInt("size");
            System.out.println(size);
            if (size > 0) {
                Object[] objectArray = (Object[]) bundle.getSerializable("patients");
                if (objectArray != null) {
                    for (int i = 0; i < size; i++) {
                        profils.add((String[]) objectArray[i]);
                    }
                }

            } else {
                AlertDialog.Builder error = new AlertDialog.Builder(Profil.this);
                error.setCancelable(false);
                error.setIcon(R.drawable.exclamation_mark);
                error.setTitle("Alerte");
                error.setMessage("Aucun patient trouvé !");
                error.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Profil.this, Home.class));
                    }
                });
            }

        } else {
            Toast.makeText(this, "Veuillez attendez SVP...", Toast.LENGTH_SHORT).show();
        }
        profilsAdapter pf = new profilsAdapter(this,profils);
        initList(pf);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                profilsAdapter pf = new profilsAdapter(Profil.this,profils);
                initList(pf);
            }
        });

    }

    public void search(){
        String pat = search.getText().toString();
        System.out.println(pat);
        try {
            ArrayList<String[]> selectedProfils = new ArrayList<>();
            for (String[] patient : profils){
                if(patient[2].toLowerCase().contains(pat.toLowerCase()) || patient[1].toLowerCase().contains(pat.toLowerCase()) || patient[3].contains(pat)){
                    selectedProfils.add(patient);
                    System.out.println(patient[1]);
                }
            }

            profilsAdapter pf = new profilsAdapter(this,selectedProfils);
            initList(pf);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void initList(profilsAdapter pf) {
        khalilsListView = findViewById(R.id.khalilsecondListView);
        khalilsListView.setAdapter(pf);
        khalilsListView.setTextFilterEnabled(true);
        khalilsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String[] info = (String[]) parent.getItemAtPosition(position);
                        BackgroundWorker bW = null;
                        try {
                            bW = new BackgroundWorker(Profil.this);
                        } catch (Exception E) {
                            E.printStackTrace();
                        }
                        bW.execute(info[0], " ", "detail_profil");
                    }

                }
        );
    }
}
