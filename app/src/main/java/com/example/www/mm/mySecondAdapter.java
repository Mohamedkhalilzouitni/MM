package com.example.www.mm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class mySecondAdapter extends ArrayAdapter<String[]> {

    String[][] rapportss;
    public mySecondAdapter(@NonNull Context context, @NonNull String[][] rapports) {
        super(context, R.layout.alerte,(String[][]) rapports);
        rapportss = rapports;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater khalilsInflater2 = LayoutInflater.from(getContext());
        View rapport = khalilsInflater2.inflate(R.layout.rapport_view,parent,false);

        String[] rapportt = getItem(position);

        TextView dossier = rapport.findViewById(R.id.dossierr);
        dossier.setText(rapportt[0]);
        TextView patient = rapport.findViewById(R.id.patientr);
        patient.setText(rapportt[1]);
        TextView date = rapport.findViewById(R.id.dater);
        date.setText(rapportt[4]);
        return rapport;
    }


}
