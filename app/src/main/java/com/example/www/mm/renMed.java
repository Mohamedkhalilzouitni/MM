package com.example.www.mm;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class renMed extends Fragment {

    private static final String TAG = "renMed";
    public static View view;
    public TextView telC_, centre_, service_,medecin_,localisation_,histologie_,stade_,strategie_,protocole_;
    public TextView telC, centre, service,medecin,localisation,histologie,stade,strategie,protocole;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ren_med,container,false);
        TextView textView1 =  view.findViewById(R.id.RenM);
        SpannableString content1 = new SpannableString("Données Médicales");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        textView1.setText(content1);

        Typeface Acme = Typeface.createFromAsset(getContext().getAssets(),"Acme-Regular.ttf");
        textView1.setTypeface(Acme);

        telC = view.findViewById(R.id.numt);
        SpannableString ct = new SpannableString("Téléphone du centre :");
        ct.setSpan(new UnderlineSpan(), 0, ct.length(), 0);
        telC.setText(ct);

        protocole = view.findViewById(R.id.protocole);
        SpannableString c = new SpannableString("Protocole :");
        c.setSpan(new UnderlineSpan(), 0, c.length(), 0);
        protocole.setText(c);

        centre =  view.findViewById(R.id.centre);
        SpannableString c1 = new SpannableString("Centre");
        c1.setSpan(new UnderlineSpan(), 0, c1.length(), 0);
        centre.setText(c1);

        service =  view.findViewById(R.id.service);
        SpannableString c2 = new SpannableString("Service");
        c2.setSpan(new UnderlineSpan(), 0, c2.length(), 0);
        service.setText(c2);

        medecin =  view.findViewById(R.id.medecin);
        SpannableString c3 = new SpannableString("Médecin traitant :");
        c3.setSpan(new UnderlineSpan(), 0, c3.length(), 0);
        medecin.setText(c3);

        localisation =  view.findViewById(R.id.localisation);
        SpannableString c4 = new SpannableString("Localisation :");
        c4.setSpan(new UnderlineSpan(), 0, c4.length(), 0);
        localisation.setText(c4);

        histologie =  view.findViewById(R.id.histologie);
        SpannableString c5 = new SpannableString("Histologie :");
        c5.setSpan(new UnderlineSpan(), 0, c5.length(), 0);
        histologie.setText(c5);

        stade =  view.findViewById(R.id.stade);
        SpannableString c6 = new SpannableString("Stade :");
        c6.setSpan(new UnderlineSpan(), 0, c6.length(), 0);
        stade.setText(c6);

        strategie =  view.findViewById(R.id.strategie);
        SpannableString c7 = new SpannableString("Stratégie :");
        c7.setSpan(new UnderlineSpan(), 0, c7.length(), 0);
        strategie.setText(c7);


        protocole_ = view.findViewById(R.id.protocole_);
        centre_ =  view.findViewById(R.id.centre_);
        service_ =  view.findViewById(R.id.service_);
        medecin_ =  view.findViewById(R.id.medecin_);
        localisation_ =  view.findViewById(R.id.localisation_);
        histologie_ =  view.findViewById(R.id.histologie_);
        stade_ =  view.findViewById(R.id.stade_);
        strategie_ =  view.findViewById(R.id.strategie_);
        telC_ =  view.findViewById(R.id.numt_);

        telC_.setText(singleProfile.telC);
        protocole_.setText(singleProfile.Protocole);
        centre_.setText(singleProfile.centre);
        service_.setText(singleProfile.Service);
        String nomMed = singleProfile.nom_med.substring(0,1).toUpperCase() + singleProfile.nom_med.substring(1);
        String prenomMed = singleProfile.prenom_med.substring(0,1).toUpperCase() + singleProfile.prenom_med.substring(1);
        medecin_.setText("Dr. "+nomMed + " " +prenomMed);
        localisation_.setText(singleProfile.Localisation);
        histologie_.setText(singleProfile.Histologie);
        stade_.setText(singleProfile.Stade);
        strategie_.setText(singleProfile.Strategie);

        return view;
    }
}
