package com.example.www.mm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class mySecondAdapter extends ArrayAdapter<String[]> {

    ArrayList<String[]> rapportss;

    public mySecondAdapter(@NonNull Context context, @NonNull ArrayList<String[]> rapports) {
        super(context, R.layout.alerte,(ArrayList<String[]>) rapports);
        rapportss = rapports;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater khalilsInflater2 = LayoutInflater.from(getContext());
        View rapport = khalilsInflater2.inflate(R.layout.rapport_view,parent,false);

        String[] rapportt = getItem(position);

        TextView dossier = rapport.findViewById(R.id.dossierr);
        dossier.setText(rapportt[2]);
        TextView patient = rapport.findViewById(R.id.patientr);
        String nom = rapportt[1].substring(0, 1).toUpperCase() + rapportt[1].substring(1);
        patient.setText(nom);
        TextView date = rapport.findViewById(R.id.dater);
        date.setText(rapportt[4]);
        return rapport;
    }
}
