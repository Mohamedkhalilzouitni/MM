package com.example.www.mm;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    public Spinner couverture, statut;
    public static View view;
    TextView edittext;
    DatePickerDialog.OnDateSetListener date;
    final Calendar myCalendar = Calendar.getInstance();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab1_fragment,container,false);

        couverture = (Spinner) view.findViewById(R.id.couverture_);
        statut = (Spinner) view.findViewById(R.id.sc_);
        String [] couvs = {"--Couverture sociale--","Ramed","CNOPS","CNSS","FAR","Assurance privée","Sans"};
        String [] status = {"--statut civil--","Célebataire","Marié(e)","Divorcé(e)","Veuf"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,couvs);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,status);
        couverture.setAdapter(adapter);
        statut.setAdapter(adapter2);

         edittext= (TextView) view.findViewById(R.id.dn_);
         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TextView titre =  view.findViewById(R.id.R_S);
        SpannableString content1 = new SpannableString("Renseignements sociaux");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        titre.setText(content1);

        Typeface Acme = Typeface.createFromAsset(getContext().getAssets(),"Acme-Regular.ttf");
        titre.setTypeface(Acme);
        return view;
    }


    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }

    public void next(View v){

    }
}