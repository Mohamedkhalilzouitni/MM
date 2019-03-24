package com.example.www.mm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;

public class profilsAdapter  extends ArrayAdapter<String[]> {

    String[][] profils;

    public profilsAdapter(@NonNull Context context, @NonNull String[][] pprofils) {
        super(context, R.layout.alerte,(String[][]) pprofils);
        profils = pprofils;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater khalilsInflater1 = LayoutInflater.from(getContext());
        View profil = khalilsInflater1.inflate(R.layout.profil,parent,false);

        String[] profile = getItem(position);
        TextView flag = (TextView) profil.findViewById(R.id.dossierl);
        flag.setText(profile[3]);
        TextView dossier = (TextView) profil.findViewById(R.id.patientl);
        dossier.setText(profile[1] + " " + profile[2]);

        return profil;
    }



}
