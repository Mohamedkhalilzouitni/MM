package com.example.www.mm;


import android.os.Bundle;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class alertesPatient extends Fragment {
    private static View view;
    EditText searchAreaAP;
    ListView khalilsList;
    Button searchA;
    ArrayList<String[]> alertes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_alertes_patient, container, false);

        searchA = view.findViewById(R.id.searchBtnAP);
        searchAreaAP = view.findViewById(R.id.searchAP);
        khalilsList = view.findViewById(R.id.pa);
        alertes = singleProfile.alertes;

        searchA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAP();
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
                myAdapter khalilsAdapter = new myAdapter(getContext(), alertes);
                initList(khalilsAdapter);
            }
        });

        myAdapter khalilsAdapter = new myAdapter(getContext(), alertes);
        initList(khalilsAdapter);
        return view;
    }

    public void searchAP() {
        String al = searchAreaAP.getText().toString();
        try {
            ArrayList<String[]> selectedAlerts = new ArrayList<>();
            for (String[] rapport : alertes){
                if(rapport[3].toLowerCase().contains(al.toLowerCase())){
                    selectedAlerts.add(rapport);
                }
            }

            myAdapter pf = new myAdapter(getContext(),selectedAlerts);
            initList(pf);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void initList(myAdapter pf) {
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
                        bW.execute(info[5],info[6],"detail_alerte",info[7]);
                    }

                }
        );
    }
}