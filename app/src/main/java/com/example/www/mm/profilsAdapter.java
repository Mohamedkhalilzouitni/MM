package com.example.www.mm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;

public class profilsAdapter  extends ArrayAdapter<String[]> implements Filterable {

    ArrayList<String[]> profils;

    public profilsAdapter(@NonNull Context context, @NonNull ArrayList<String[]> pprofils) {
        super(context, R.layout.alerte, (ArrayList<String[]>) pprofils);
        profils = pprofils;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater khalilsInflater1 = LayoutInflater.from(getContext());
        View profil = khalilsInflater1.inflate(R.layout.profil, parent, false);

        String[] profile = getItem(position);
        TextView flag = (TextView) profil.findViewById(R.id.dossierl);
        flag.setText(profile[3]);
        TextView dossier = (TextView) profil.findViewById(R.id.patientl);
        String nom = profile[1].toUpperCase();
        String prenom = profile[2].substring(0, 1).toUpperCase() + profile[2].substring(1);
        dossier.setText(nom + " " + prenom);
        return profil;
    }


}