package com.example.www.mm;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

public class Tab2Fragment extends Fragment {
    public Spinner centre, service,medecin,localisation,histologie,stade,strategie;
    String telC_, details_, nom_,prenom_,adr_,tel_,nd_,adrp_,nbe_,ln_,dn_,protocole_,centre_, service_,medecin_,localisation_,histologie_,stade_,strategie_,couverture_, statut_,cin_;
    public Spinner couverture, statut;
    int std, size, id_medecin;
    EditText nom;
    EditText prenom;
    EditText adr;
    EditText tel;
    EditText nd;
    EditText nbe;
    EditText ln, telC, details;
    TextView dn;
    EditText protocole;
    EditText cin;
    static View view;
    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab2_fragment,container,false);
        TextView titre =  view.findViewById(R.id.R_M);
        SpannableString content1 = new SpannableString("Données Médicales");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        titre.setText(content1);
        Typeface Acme = Typeface.createFromAsset(getContext().getAssets(),"Acme-Regular.ttf");
        titre.setTypeface(Acme);
        details = view.findViewById(R.id.details_);
        telC = view.findViewById(R.id.numtel_);
        couverture = (Spinner) view.findViewById(R.id.couverture_);
        statut = (Spinner) view.findViewById(R.id.sc_);
        centre = (Spinner) view.findViewById(R.id.centre_);
        service = (Spinner) view.findViewById(R.id.service_);
        medecin = (Spinner) view.findViewById(R.id.medecin_);
        localisation = (Spinner) view.findViewById(R.id.localisation_);
        histologie = (Spinner) view.findViewById(R.id.histologie_);
        stade = (Spinner) view.findViewById(R.id.stade_);
        strategie = (Spinner) view.findViewById(R.id.strategie_);
        String [] centres = {"Centre Ahmad Bin Zayed Al Nahyan de traitement des cancers"};
        String [] services = {"Oncologie Médicale"};
        final String [][] medecins = AjouterPatient.medecins;
        size = medecins.length;
        final String [] medecinss = new String[size];
        for (int i = 0; i<size;i++){
            medecinss[i] ="Dr. "+ medecins[i][2].substring(0, 1).toUpperCase()+ medecins[i][2].substring(1)+" "+medecins[i][1].substring(0, 1).toUpperCase()+ medecins[i][1].substring(1);
        }
        String [] localisations = {"Cerveau","Nasopharynx","Hypopharynx","Larynx","Thyroïde","Oesophage","Estomac","Grêle","Colon","Rectum",
                                    "Canal anal", "Bronches" ,"Poumon","Thymus","Sein","Ovaire","Endomètre","Col de l'utérus","Vulve","Foie",
                                    "Pancréas","Vésicule biliaire","Rein","Voies excrétrices","Prostate","Pénis","Testicles","Parties molles",
                                    "Rétropéritoine", "Ganglions","Os","Peau","Tête et cou : autre","Carcinome à primtif inconnu"};

        String [] histologies = {"Carcinome NOS","CHC","Carcinome à cellules claires",
                                "Carcinome vésiculaire","Carcinome thymique","Thymome",
                                  "Tumeur germinale non séminomateuse","GIST","Ostéosarcome",
                "Glioblastome","Astrocytome","UCNT","Carcinome épidermoïde","Carcinome adénoïde kystique","Carcinome papillaire",
        "Carcinome anaplasique","Adénocarcinome","Carcinome à cellules en bague à chatons",
        "Carcinome neuroendocrine bien différencié","Carcinome neuroendocrine peu différencié","Carcinome à petites cellules","Carcinome TNS",
         "Sarcome des tissus mous","Sarcome","Sarcome d’Ewing", "Carcinome hépatocellulaire", "Carcinome urothélial",
        "Séminome", "Tumeur à cellules géantes", "Autre"};
        String [] stades = {"I","II","III","IV"};
        String [] strategies = {"Néoadjuvante","Adjuvante","Palliative","Chimiothérapie Concomitante"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,centres);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,services);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,medecinss);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,localisations);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,histologies);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,stades);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,strategies);
        centre.setAdapter(adapter);
        service.setAdapter(adapter2);
        medecin.setAdapter(adapter3);
        localisation.setAdapter(adapter4);
        histologie.setAdapter(adapter5);
        stade.setAdapter(adapter6);
        strategie.setAdapter(adapter7);

        Button ins = (Button) view.findViewById(R.id.terminer);
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int success = 1;
                nom = (EditText) Tab1Fragment.view.findViewById(R.id.nom_);
                cin = (EditText) Tab1Fragment.view.findViewById(R.id.cin_);
                prenom = (EditText) Tab1Fragment.view.findViewById(R.id.prenom_);
                adr = (EditText) Tab1Fragment.view.findViewById(R.id.adr_);
                tel = (EditText) Tab1Fragment.view.findViewById(R.id.tele_);
                nd = (EditText) Tab1Fragment.view.findViewById(R.id.num_dos_);
                nbe = (EditText) Tab1Fragment.view.findViewById(R.id.nbe_);
                ln = (EditText) Tab1Fragment.view.findViewById(R.id.ln_);
                dn = (TextView) Tab1Fragment.view.findViewById(R.id.dn_);
                protocole = (EditText) view.findViewById(R.id.protocole_);
                couverture = (Spinner) Tab1Fragment.view.findViewById(R.id.couverture_);
                statut = (Spinner) Tab1Fragment.view.findViewById(R.id.sc_);
                centre = (Spinner) view.findViewById(R.id.centre_);
                service = (Spinner) view.findViewById(R.id.service_);
                medecin = (Spinner) view.findViewById(R.id.medecin_);
                localisation = (Spinner) view.findViewById(R.id.localisation_);
                histologie = (Spinner) view.findViewById(R.id.histologie_);
                stade = (Spinner) view.findViewById(R.id.stade_);
                strategie = (Spinner) view.findViewById(R.id.strategie_);

                cin_ = cin.getText().toString();
                if(cin_.length()==0){
                    cin.setError("ce champs est obligatoire!");
                    cin.requestFocus();
                    success = 0;
                }
                telC_ = telC.getText().toString();
                if(telC_.length()==0){
                    telC.setError("ce champs est obligatoire!");
                    telC.requestFocus();
                    success = 0;
                }
                nom_ = nom.getText().toString();
                if(nom_.length()==0){
                    nom.setError("ce champs est obligatoire!");
                    nom.requestFocus();
                    success = 0;
                }
                prenom_ = prenom.getText().toString();
                if(prenom_.length()==0){
                    prenom.setError("ce champs est obligatoire!");
                    prenom.requestFocus();
                    success = 0;
                }
                adr_ = adr.getText().toString();
                if(adr_.length()==0){
                    adr.setError("ce champs est obligatoire!");
                    adr.requestFocus();
                    success = 0;
                }
                tel_ = tel.getText().toString();
                if(tel_.length()==0){
                    tel.setError("ce champs est obligatoire!");
                    tel.requestFocus();
                    success = 0;
                }
                nd_ = nd.getText().toString();
                if(nd_.length()==0){
                    nd.setError("ce champs est obligatoire!");
                    nd.requestFocus();
                    success = 0;
                }
                nbe_ = nbe.getText().toString();
                if(nbe_.length()==0){
                    nbe.setError("ce champs est obligatoire!");
                    nbe.requestFocus();
                    success = 0;
                }
                ln_ = ln.getText().toString();
                if(ln_.length()==0){
                    ln.setError("ce champs est obligatoire!");
                    ln.requestFocus();
                    success = 0;
                }
                dn_ = dn.getText().toString();
                if(dn_.length()==0){
                    dn.setError("ce champs est obligatoire!");
                    dn.requestFocus();
                    success = 0;
                }
                protocole_ = protocole.getText().toString();
                if(protocole_.length()==0){
                    protocole.setError("ce champs est obligatoire!");
                    protocole.requestFocus();
                    success = 0;
                }


                centre_ = centre.getSelectedItem().toString();
                /*if(centre_.equals("--Centre--")){
                    Toast.makeText(getContext(),"Veuillez remplir le champs 'Centre'",Toast.LENGTH_LONG).show();
                    success = 0;
                }*/
                service_ = service.getSelectedItem().toString();
               /* if(service_.equals("--Service--")){
                    Toast.makeText(getContext(),"Veuillez remplir le champs 'Service' !",Toast.LENGTH_LONG).show();
                    success = 0;
                }*/
                medecin_ = medecin.getSelectedItem().toString();
                /*if(medecin_.equals("--Médecin traitant--")){
                    Toast.makeText(getContext(),"Veuillez remplir le champs 'Medecin traitant'",Toast.LENGTH_LONG).show();
                    success = 0;
                }*/
                for (int i = 0; i< size; i++){
                    if (medecin_.equals(medecinss[i])){
                        id_medecin = Integer.valueOf(medecins[i][0]);
                    }
                }
                localisation_ = localisation.getSelectedItem().toString();
               /* if(localisation_.equals("--Localisation_--")){
                    Toast.makeText(getContext(),"Veuillez remplir le champs 'Localisation'",Toast.LENGTH_LONG).show();
                    success = 0;
                }*/
                histologie_ = histologie.getSelectedItem().toString();
              /*  if(histologie_.equals("--Histologie--")){
                    Toast.makeText(getContext(),"Veuillez remplir le champs 'Histologie'",Toast.LENGTH_LONG).show();
                    success = 0;
                }*/
                stade_ = stade.getSelectedItem().toString();
               /* if(stade_.equals("--Stade--")){
                    Toast.makeText(getContext(),"Veuillez remplir le champs 'Stade'",Toast.LENGTH_LONG).show();
                    success = 0;
                }*/
                strategie_ = strategie.getSelectedItem().toString();
                /*if(stade_.equals("--Strategie--")){
                    Toast.makeText(getContext(),"Veuillez remplir le champs 'Strategie'",Toast.LENGTH_LONG).show();
                    success = 0;
                }*/
                couverture_ = couverture.getSelectedItem().toString();
                if(couverture_.equals("--Couverture médicale--")){
                    Toast.makeText(getContext(),"Veuillez remplir le champs 'Couverture médicale'",Toast.LENGTH_LONG).show();
                    success = 0;
                }
                statut_ = statut.getSelectedItem().toString();
                if(statut_.equals("--statut civil--")){
                    Toast.makeText(getContext(),"Veuillez remplir le champs 'Statut civil'",Toast.LENGTH_LONG).show();
                    success = 0;
                }

                switch (stade_){
                    case "I":
                        std = 1;
                        break;
                    case "II":
                        std = 2;
                        break;
                    case "III":
                        std = 3;
                        break;
                    case "IV":
                        std = 4;
                        break;
                }

                if (success == 1){
                    AlertDialog.Builder error = new AlertDialog.Builder(getContext());
                    error.setCancelable(true);
                    error.setIcon(R.drawable.confirm);
                    error.setTitle("Confirmation");
                    error.setMessage("Confirmer l'inscription ? ");
                    error.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                        BackgroundWorker bW = null;
                            try {
                                bW = new BackgroundWorker(getContext());
                            } catch (Exception E) {
                                E.printStackTrace();
                            }
                          bW.execute(nom_,prenom_,"inscription_patient",adr_,tel_,statut_,nd_,nbe_,ln_,dn_,protocole_,centre_,service_,String.valueOf(id_medecin),localisation_,histologie_,String.valueOf(std),strategie_,couverture_,cin_,telC_, details_);
                        }
                    }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getContext(),"Vérifier vos choix",Toast.LENGTH_SHORT).show();
                        }
                    });
                    error.create().show();
                }else {
                    AlertDialog.Builder error = new AlertDialog.Builder(getContext());
                    error.setCancelable(true);
                    error.setIcon(R.drawable.exclamation_mark);
                    error.setTitle("Erreur");
                    error.setMessage("Veuillez vérifier tous les champs !");
                    error.create().show();
                }
            }
        });
        return view;
    }
}