package com.example.www.mm;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class renSoc extends Fragment{

    private static final String TAG = "renSoc";
    public static View view;
    TextView nom_,prenom_,adr_,tel_,nd_,nbe_,ln_,dn_,cin_,couverture_, statut_;
    TextView RenS,nom,prenom,adr,tel,nd,nbe,ln,dn,cin,couverture,statut;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ren_soc,container,false);
        TextView RenS =  view.findViewById(R.id.RenS);
        SpannableString content1 = new SpannableString("Renseignements sociaux");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        RenS.setText(content1);

        Typeface Acme = Typeface.createFromAsset(getContext().getAssets(),"Acme-Regular.ttf");
        RenS.setTypeface(Acme);

        nom =  view.findViewById(R.id.nom);
        SpannableString c = new SpannableString("Nom :");
        c.setSpan(new UnderlineSpan(), 0, c.length(), 0);
        nom.setText(c);

        cin =  view.findViewById(R.id.cin);
        SpannableString c1 = new SpannableString("CIN :");
        c1.setSpan(new UnderlineSpan(), 0, c1.length(), 0);
        cin.setText(c1);

        prenom =  view.findViewById(R.id.prenom);
        SpannableString c2 = new SpannableString("Prénom :");
        c2.setSpan(new UnderlineSpan(), 0, c2.length(), 0);
        prenom.setText(c2);

        adr =  view.findViewById(R.id.adr);
        SpannableString c3 = new SpannableString("Adresse :");
        c3.setSpan(new UnderlineSpan(), 0, c3.length(), 0);
        adr.setText(c3);

        tel =  view.findViewById(R.id.tele);
        SpannableString c4 = new SpannableString("Téléphone :");
        c4.setSpan(new UnderlineSpan(), 0, c4.length(), 0);
        tel.setText(c4);

        nd =  view.findViewById(R.id.num_dos);
        SpannableString c5 = new SpannableString("Numéro de dossier :");
        c5.setSpan(new UnderlineSpan(), 0, c5.length(), 0);
        nd.setText(c5);

        nbe =  view.findViewById(R.id.nbe);
        SpannableString c6 = new SpannableString("Nombre d'enfants :");
        c6.setSpan(new UnderlineSpan(), 0, c6.length(), 0);
        nbe.setText(c6);

        ln =  view.findViewById(R.id.ln);
        SpannableString c7 = new SpannableString("Lieu de naissance :");
        c7.setSpan(new UnderlineSpan(), 0, c7.length(), 0);
        ln.setText(c7);

        dn =  view.findViewById(R.id.dn);
        SpannableString c8 = new SpannableString("Date de naissance:");
        c8.setSpan(new UnderlineSpan(), 0, c8.length(), 0);
        dn.setText(c8);

        couverture =  view.findViewById(R.id.couverture);
        SpannableString c9 = new SpannableString("Couverture sociale :");
        c9.setSpan(new UnderlineSpan(), 0, c9.length(), 0);
        couverture.setText(c9);

        statut =  view.findViewById(R.id.sc);
        SpannableString c10 = new SpannableString("Statut civil :");
        c10.setSpan(new UnderlineSpan(), 0, c10.length(), 0);
        statut.setText(c10);

        nom_ =  view.findViewById(R.id.nom_);
        cin_ =  view.findViewById(R.id.cin_);
        prenom_ =  view.findViewById(R.id.prenom_);
        adr_ =  view.findViewById(R.id.adr_);
        tel_ =  view.findViewById(R.id.tele_);
        nd_ =  view.findViewById(R.id.num_dos_);
        nbe_ =  view.findViewById(R.id.nbe_);
        ln_ =  view.findViewById(R.id.ln_);
        dn_ =  view.findViewById(R.id.dn_);
        couverture_ =  view.findViewById(R.id.couverture_);
        statut_ =  view.findViewById(R.id.sc_);

        nom_.setText(singleProfile.nom);
        prenom_.setText(singleProfile.prenom);
        cin_.setText(singleProfile.cin);
        adr_.setText(singleProfile.adresse);
        tel_.setText(singleProfile.Num_telephone);
        nd_.setText(singleProfile.Num_dossier);
        nbe_.setText(singleProfile.Nombre_enfants);
        ln_.setText(singleProfile.Lieu_naissance);
        dn_.setText(singleProfile.date_naissance);
        couverture_.setText(singleProfile.couverture_social);
        statut_.setText(singleProfile.Statut_social);

        return view;
    }
}
