package com.example.www.mm;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.sql.DriverManager.println;

public class singleProfile extends AppCompatActivity {
    private static final String TAG = "profil d'un patient";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    static int id_patient;
    static ArrayList<String[]> rapports = new ArrayList<>();
    static ArrayList<String[]> alertes = new ArrayList<>();
    static String telC, id_patientr, cin, centre, nom, prenom, adresse, couverture_social, Statut_social, Nombre_enfants, Num_dossier, Num_telephone, date_naissance, Protocole, Service, Localisation, Histologie, Stade, Strategie, nom_med, prenom_med, Lieu_naissance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_profile);
        Log.d(TAG, "onCreate: Starting.");
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            id_patientr = b.getString("id_patient");
            id_patient = Integer.valueOf(id_patientr);
            cin = b.getString("cin");
            nom = b.getString("nom");
            prenom = b.getString("prenom");
            adresse = b.getString("adresse");
            couverture_social = b.getString("couverture_social");
            Statut_social = b.getString("Statut_civil");
            Nombre_enfants = b.getString("Nombre_enfants");
            Num_dossier = b.getString("Num_dossier");
            Num_telephone = b.getString("Num_telephone");
            Lieu_naissance = b.getString("Lieu_naissance");
            date_naissance = b.getString("date_naissance");
            Protocole = b.getString("Protocole");
            Service = b.getString("Service");
            Localisation = b.getString("Localisation");
            Histologie = b.getString("Histologie");
            Stade = b.getString("Stade");
            Strategie = b.getString("Strategie");
            nom_med = b.getString("nom_med");
            prenom_med = b.getString("prenom_med");
            centre = b.getString("centre");
            telC = b.getString("telC");
            System.out.println("Bismillah");
            Log.d(TAG, "Bismillah");

            int size = b.getInt("size");
            Object[] objectArray = (Object[]) b.getSerializable("rapportsPatient");
            if (objectArray != null) {
                for (int i = 0; i < size; i++) {
                    rapports.add((String[]) objectArray[i]);
                    System.out.println("This is the id_patient : " + rapports.get(i)[5] + " and this is the id_rapport : " + rapports.get(i)[0]);
                }
            }

            int sizeA = b.getInt("sizeA");
            Object[] objectArray2 = (Object[]) b.getSerializable("alertesPatient");
            if (objectArray != null) {
                for (int i = 0; i < objectArray2.length; i++) {
                    alertes.add((String[]) objectArray2[i]);
                }
            } else {
                Toast.makeText(this, "Veuillez attendez SVP...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new renSoc(), "RENSEIGNEMENTS SOCIAUX");
        adapter.addFragment(new renMed(), "DONNÉES MÉDICALES");
        adapter.addFragment(new rapportsPatient(), "RAPPORTS");
        adapter.addFragment(new alertesPatient(), "ALERTES");
        viewPager.setAdapter(adapter);
    }
}
