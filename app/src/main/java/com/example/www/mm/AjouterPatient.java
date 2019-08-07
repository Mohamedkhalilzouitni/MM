package com.example.www.mm;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class AjouterPatient extends AppCompatActivity {

    private static final String TAG = "Ajouter un patient";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    static String[][] medecins = new String[4][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_patient);
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        Bundle b = getIntent().getExtras();;
        if (b != null) {
            int size = b.getInt("size");
            medecins = new String[size][3];
            Object[] objectArray = (Object[]) b.getSerializable("medecins");
            if (objectArray != null) {
                medecins = new String[size][];
                for (int i = 0; i < objectArray.length; i++) {
                    medecins[i] = (String[]) objectArray[i];
                }
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Renseignements Sociaux");
        adapter.addFragment(new Tab2Fragment(), "Données Médicales");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}