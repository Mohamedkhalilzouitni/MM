package com.example.www.mm;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class myAdapter extends ArrayAdapter<String[]> {

    String[][] alertess;
    public myAdapter(@NonNull Context context, @NonNull String[][] alertes) {
        super(context, R.layout.alerte,(String[][]) alertes);
        alertess = alertes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater khalilsInflater1 = LayoutInflater.from(getContext());
        View alerte = khalilsInflater1.inflate(R.layout.alerte,parent,false);

        String[] alert = getItem(position);
        TextView flag = (TextView) alerte.findViewById(R.id.flaga);
        flag.setText(alert[0]);
        String grade = alert[0];
        switch (grade){
            case "2" :
                flag.setBackgroundColor(Color.parseColor("#ffff66"));
                break;
            case "3" :
                flag.setBackgroundColor(Color.parseColor("#ff9933"));
                break;
            case "4" :
                flag.setBackgroundColor(Color.parseColor("#ff0000"));
                break;
            default:
                flag.setBackgroundColor(Color.parseColor("#fff"));
                break;
        }
        TextView dossier = (TextView) alerte.findViewById(R.id.dossiera);
        dossier.setText(alert[1]);
        TextView patient = (TextView) alerte.findViewById(R.id.patienta);
        patient.setText(alert[2]);
        TextView date = (TextView) alerte.findViewById(R.id.datea);
        date.setText(alert[3]);

        return alerte;
    }
}
