package com.example.www.mm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.www.mm.dummy.DummyContent;
import com.example.www.mm.dummy.DummyContent.DummyItem;

import java.util.List;

import static com.example.www.mm.Rapports.rapports;


public class rapportsPatient extends Fragment {

    private static View view;
    ListView reports;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.patient_rapports, container, false);

        ListAdapter khalilsAdapter = new mySecondAdapter(getContext(), singleProfile.rapports);
        ListView khalilsListView = view.findViewById(R.id.pr);
        khalilsListView.setAdapter(khalilsAdapter);
        khalilsListView.setOnItemClickListener(
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


        return view;
    }
}
