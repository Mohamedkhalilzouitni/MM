package com.example.www.mm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class rapportsPatient extends Fragment {

    private static View view;
    ListView reports;
    EditText searchAreaRP;
    ListView khalilsList;
    Button searchR;
    ArrayList<String[]> rapports = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.patient_rapports, container, false);

        searchR = view.findViewById(R.id.searchBtnRP);
        searchAreaRP = view.findViewById(R.id.searchRP);
        khalilsList = view.findViewById(R.id.pr);
        rapports = singleProfile.rapports;

        searchR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchRP();
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
                mySecondAdapter khalilsAdapter = new mySecondAdapter(getContext(), rapports);
                initList(khalilsAdapter);
            }
        });

        mySecondAdapter khalilsAdapter = new mySecondAdapter(getContext(), rapports);
        initList(khalilsAdapter);

        return view;
    }

    public void searchRP() {
        String al = searchAreaRP.getText().toString();
        try {
            ArrayList<String[]> selectedAlerts = new ArrayList<>();
            for (String[] rapport : rapports){
                if(rapport[4].toLowerCase().contains(al.toLowerCase())){
                    selectedAlerts.add(rapport);
                    System.out.println(rapport[2]);
                }
            }

            mySecondAdapter pf = new mySecondAdapter(getContext(),selectedAlerts);
            initList(pf);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void initList(mySecondAdapter pf) {
        khalilsList.setAdapter(pf);
        khalilsList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String[] info =(String[]) parent.getItemAtPosition(position);
                        BackgroundWorker bW = null;
                        try {
                            bW = new BackgroundWorker(getContext());
                        } catch (Exception E) {
                            E.printStackTrace();
                        }
                        bW.execute(info[5],info[0],"detail_rapport");
                    }

                }
        );
    }
}
