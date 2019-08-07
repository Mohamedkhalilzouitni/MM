package com.example.www.mm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class alertesPatient extends Fragment {
    private static View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_alertes_patient, container, false);

        ListAdapter buckysAdapter = new myAdapter(getContext(), singleProfile.alertes);
        ListView buckysListView = view.findViewById(R.id.pa);
        buckysListView.setAdapter(buckysAdapter);

        buckysListView.setOnItemClickListener(

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

        return view;
    }
}