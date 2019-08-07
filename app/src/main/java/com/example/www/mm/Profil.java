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

    static String[][] profils = new String[10][4];
    String nom,prenom,numDossier;
    int idPatient;
    EditText search;
    ListAdapter khalilsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        HashMap<String,Profil> profiles = new HashMap<>();
        Bundle bundle = getIntent().getExtras();
        search = findViewById(R.id.search);
        if (bundle != null) {
            int size = bundle.getInt("size");
            if (size > 0) {
                profils = new String[size][10];
                Object[] objectArray = (Object[]) bundle.getSerializable("patients");
                if (objectArray != null) {
                    profils = new String[objectArray.length][];
                    for (int i = 0; i < objectArray.length; i++) {
                        profils[i] = (String[]) objectArray[i];
                    }
                }
                List<Profil> peopleByAge = new ArrayList<>(profiles.values());
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

        khalilsAdapter = new profilsAdapter(this, profils);
        ListView khalilsListView = findViewById(R.id.khalilsecondListView);
        khalilsListView.setTextFilterEnabled(true);
        khalilsListView.setAdapter(khalilsAdapter);
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

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Profil.this.khalilsAdapter.get
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
