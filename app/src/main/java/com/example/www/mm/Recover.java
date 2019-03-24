package com.example.www.mm;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Recover extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void check(View v){
        EditText emailid = findViewById(R.id.email_recover);
        String getEmailId = emailid.getText().toString();

        if(getEmailId.length()==0){
            emailid.setError("Veuillez saisir votre adresse email !");
            emailid.requestFocus();
        }

// Check if email id is valid or not
        if (!isEmailValid(getEmailId)){
            AlertDialog.Builder error = new AlertDialog.Builder(this);
            error.setCancelable(true);
            error.setIcon(R.drawable.exclamation_mark);
            error.setTitle("Vérifier votre adresse email SVP !");
            error.setMessage("adresse email non valide.");
            error.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            error.create().show();
        }else {
            BackgroundWorker bW = null;
            try {
                bW = new BackgroundWorker(Recover.this);
            } catch (Exception E) {
                E.printStackTrace();
            }
            bW.execute(getEmailId,"","recover");
        }
    }
}
