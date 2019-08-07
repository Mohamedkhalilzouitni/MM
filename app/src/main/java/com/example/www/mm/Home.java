package com.example.www.mm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Map;
import java.util.Random;

public class    Home extends AppCompatActivity {
    static public String nom, prenom;
    TextView infoDr;
//    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    DatabaseReference databaseReference = firebaseDatabase.getReference("Alerts/newAlert");

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Rapports:
                    BackgroundWorker bW = null;
                    try {
                        bW = new BackgroundWorker(Home.this);
                    } catch (Exception E) {
                        E.printStackTrace();
                    }
                    bW.execute("","","rapports");
                    return true;
                case R.id.Alertes:
                    BackgroundWorker bW1 = null;
                    try {
                        bW1 = new BackgroundWorker(Home.this);
                    } catch (Exception E) {
                        E.printStackTrace();
                    }
                    bW1.execute("","","alertes");
                    return true;
                case R.id.Patients:
                    startActivity(new Intent(Home.this,Patients.class));
                    return true;
                case R.id.Deconnexion:
                    AlertDialog.Builder deconnect = new AlertDialog.Builder(Home.this);
                    deconnect.setIcon(R.drawable.logout);
                    deconnect.setCancelable(false);
                    deconnect.setTitle("Confirmer votre choix SVP !");
                    deconnect.setMessage("Voulez vous vraiment vous déconnecter de MoniTox ?");
                    deconnect.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.sp.edit().clear();
                            MainActivity.sp.edit().putBoolean("logged",false).commit();
                            MainActivity.sp.edit().commit();
                            Intent i = new Intent(Home.this,Connexion.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        }
                    })
                            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    deconnect.create().show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        infoDr = findViewById(R.id.Info_Dr);
//        FirebaseMessaging.getInstance().subscribeToTopic("all");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        databaseReference.addValueEventListener(new ValueEventListener(){
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot){
//                String lu = (String) dataSnapshot.getValue();
//                Log.d("TAG", lu);
//                if(lu.equals("0")){
////                    Notify(Home.this,"Nouvelle Alerte !","Cliquez pour en savoir plus...",getIntent());
//                   HashMap<String,String> data = new HashMap<>();
//                   data.put("title","Nouvelle alerte !");
//                   data.put("body","Cliquez pour en savoir plus...");
//                   showNotification("Nouvelle alerte !","Cliquez pour en savoir plus...");
//
//                    databaseReference.setValue("1");
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError error){
//
//            }
//        });

        Intent intent = getIntent();
            Bundle bd = getIntent().getExtras();
            if( bd != null){
                nom = bd.getString("nom");
                prenom = bd.getString("prenom");
            }

        nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        MainActivity.sp.edit().putString("nom",nom).apply();
        MainActivity.sp.edit().putString("prenom",prenom).apply();

        SpannableString infDr = new SpannableString("Dr. " + nom + " " + prenom);
        infDr.setSpan(new UnderlineSpan(),0,infDr.length(),0);
        infoDr.setText(infDr);

        TextView a = findViewById(R.id.alertes);
        TextView r = findViewById(R.id.rapports);
        TextView l = findViewById(R.id.patients);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker bW = null;
                try {
                    bW = new BackgroundWorker(Home.this);
                } catch (Exception E) {
                    E.printStackTrace();
                }
                bW.execute("","","alertes");

            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundWorker bW = null;
                try {
                    bW = new BackgroundWorker(Home.this);
                } catch (Exception E) {
                    E.printStackTrace();
                }
                bW.execute("","","rapports");
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Patients.class));
            }
        });
    }

    private void showNotification(String title, String body) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.example.www.mm.test";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("my Desc");
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.alarm).setContentTitle(title).setContentText(body).setContentInfo("Info");
        notificationManager.notify(new Random().nextInt(),notificationBuilder.build());
    }

    private void showNotification(Map<String,String> data) {

        String title = data.get("title").toString();
        String body = data.get("body").toString();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.example.www.mm.test";


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("my Desc");
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.alarm).setContentTitle(title).setContentText(body).setContentInfo("Info");
        notificationManager.notify(new Random().nextInt(),notificationBuilder.build());
    }

    public void Notify(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(this.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "Arduino - ESP8266 - PIR Sensor";
        String channelName = "Antivol Application";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.alarm)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
