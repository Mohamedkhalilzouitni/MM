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

    String[][] profils;
    String [][] mDisplayedValues;
    static ArrayList<String[]> temporarylist = new ArrayList<>();
    static ArrayList<String[]> OriginalList = new ArrayList<>();

    public profilsAdapter(@NonNull Context context, @NonNull String[][] pprofils) {
        super(context, R.layout.alerte,(String[][]) pprofils);
        profils = pprofils;
        mDisplayedValues = profils;
        for (int i = 0; i < profils.length; i++){
            OriginalList.add(profils[i]);
        }
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater khalilsInflater1 = LayoutInflater.from(getContext());
        View profil = khalilsInflater1.inflate(R.layout.profil,parent,false);

        String[] profile = getItem(position);
        TextView flag = (TextView) profil.findViewById(R.id.dossierl);
        flag.setText(profile[3]);
        TextView dossier = (TextView) profil.findViewById(R.id.patientl);
        String nom = profile[1].toUpperCase();
        String prenom = profile[2].substring(0, 1).toUpperCase() + profile[2].substring(1);
        dossier.setText(nom + " " + prenom);

        return profil;
    }

    public void jjj(){

    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            ArrayList<String[]> FilteredList= new ArrayList<String[]>();
            if (constraint == null || constraint.length() == 0) {
                results.values = OriginalList;
                results.count = OriginalList.size();
            }
            else {
                for (int i = 0; i < OriginalList.size(); i++) {
                    String[] data = OriginalList.get(i);
                    if (data[3].toLowerCase().contains(constraint.toString()))  {
                        FilteredList.add(data);
                    }
                }
                results.values = FilteredList;
                results.count = FilteredList.size();
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence contraint, FilterResults results) {
            temporarylist=(ArrayList<String[]>)results.values;
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return myFilter;
    }


}

