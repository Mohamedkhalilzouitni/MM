package com.example.www.mm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.media.MediaCodec.MetricsConstants.MODE;


public class BackgroundWorker extends AsyncTask<String,String,String> {
    ProgressDialog loading;
    private static String typ,nomrr,prenomrr,num_dossierrr,telrr,dater,date_rapporti;
    String result;
    Context context;
    static int id_patientrr,id_rapportrr;


    public BackgroundWorker(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(context, "Veuillez patientez", "Chargement..", true, true);
        loading.setCancelable(false);
        loading.setIcon(R.drawable.refresh_button);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        typ = params[2];

        if (typ.equals("inscription")) {
            try {

                String url_php = "https://oncotool.watching-environment.com/Inscription.php";
                String nom = params[0];
                String prenom = params[1];
                String inpe = params[3];
                String telephone = params[4];
                String hopital = params[5];
                String email = params[6];
                String mdp = params[7];
                String ville = params[8];

                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("nom", "UTF-8") + "=" + URLEncoder.encode(nom, "UTF-8") + "&" + URLEncoder.encode("prenom", "UTF-8") + "=" + URLEncoder.encode(prenom, "UTF-8")+ "&" + URLEncoder.encode("inpe", "UTF-8") + "=" + URLEncoder.encode(inpe, "UTF-8") + "&" + URLEncoder.encode("telephone", "UTF-8") + "=" + URLEncoder.encode(telephone, "UTF-8")+ "&" + URLEncoder.encode("hopital", "UTF-8") + "=" + URLEncoder.encode(hopital, "UTF-8") + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("mdp", "UTF-8") + "=" + URLEncoder.encode(mdp, "UTF-8") + "&" + URLEncoder.encode("ville", "UTF-8") + "=" + URLEncoder.encode(ville, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                 result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (typ.equals("connexion")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/Connexion_medecin.php";
                String email = params[0];
                String mdp = params[1];
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("mdp", "UTF-8") + "=" + URLEncoder.encode(mdp, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (typ.equals("alertes")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/alertes.php";
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("recover")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/recover.php";
                String email = params[0];
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("rapports")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/rapports.php";
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("detail_rapport")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/detail_rapport.php";
                String id_patientr = params [0];
                String id_rapportr = params [1];
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("id_patient", "UTF-8") + "=" + URLEncoder.encode(id_patientr, "UTF-8") + "&" + URLEncoder.encode("id_rapport", "UTF-8") + "=" + URLEncoder.encode(id_rapportr, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("detail_alerte")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/detail_alerte.php";
                String id_alertea = params [0];
                String id_patienta = params [1];
                String id_rapporta = params [3];
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("id_patient", "UTF-8") + "=" + URLEncoder.encode(id_patienta, "UTF-8") + "&" + URLEncoder.encode("id_rapport", "UTF-8") + "=" + URLEncoder.encode(id_rapporta, "UTF-8")+ "&" + URLEncoder.encode("id_alerte", "UTF-8") + "=" + URLEncoder.encode(id_alertea, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("supprimer_alerte")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/supprimer_alerte.php";
                String id_alertea = params [0];
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("id_alerte", "UTF-8") + "=" + URLEncoder.encode(id_alertea, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("supprimer_rapport")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/supprimer_rapport.php";
                String id_rapporta = params [0];
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("id_rapport", "UTF-8") + "=" + URLEncoder.encode(id_rapporta, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("inscription_patient")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/inscription_patient.php";
                String nom_ = params[0];
                String prenom_ = params[1];
                String adr_ = params[3];
                String tel_ = params[4] ;
                String statut_ = params[5];
                String nd_ = params[6];
                String nbe_ = params[7];
                String ln_= params[8];
                String dn_ = params[9];
                String protocole_ = params[10];
                String centre_ = params[11];
                String service_ = params[12];
                String medecin_= params[13];
                String localisation_ = params[14];
                String histologie_ = params[15];
                String stade_ = params[16];
                String strategie_ = params[17];
                String couverture_ = params[18];
                String cin_ = params[19];
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("nom", "UTF-8") + "=" + URLEncoder.encode(nom_, "UTF-8")  + "&" + URLEncoder.encode("prenom", "UTF-8") + "=" + URLEncoder.encode(prenom_, "UTF-8") + "&" + URLEncoder.encode("numdos", "UTF-8") + "=" + URLEncoder.encode(nd_, "UTF-8")  + "&" + URLEncoder.encode("adr", "UTF-8") + "=" + URLEncoder.encode(adr_, "UTF-8") + "&" + URLEncoder.encode("tel", "UTF-8") + "=" + URLEncoder.encode(tel_, "UTF-8")  + "&" + URLEncoder.encode("statut", "UTF-8") + "=" + URLEncoder.encode(statut_, "UTF-8") + "&" + URLEncoder.encode("nbrenf", "UTF-8") + "=" + URLEncoder.encode(nbe_, "UTF-8")  + "&" + URLEncoder.encode("ln", "UTF-8") + "=" + URLEncoder.encode(ln_, "UTF-8") + "&" + URLEncoder.encode("dn", "UTF-8") + "=" + URLEncoder.encode(dn_, "UTF-8")  + "&" + URLEncoder.encode("protocole", "UTF-8") + "=" + URLEncoder.encode(protocole_, "UTF-8") + "&" + URLEncoder.encode("centre", "UTF-8") + "=" + URLEncoder.encode(centre_, "UTF-8")  + "&" + URLEncoder.encode("service", "UTF-8") + "=" + URLEncoder.encode(service_, "UTF-8") + "&" + URLEncoder.encode("medecin", "UTF-8") + "=" + URLEncoder.encode(medecin_, "UTF-8")  + "&" + URLEncoder.encode("localisation", "UTF-8") + "=" + URLEncoder.encode(localisation_, "UTF-8") + "&" + URLEncoder.encode("histologie", "UTF-8") + "=" + URLEncoder.encode(histologie_, "UTF-8")  + "&" + URLEncoder.encode("stade", "UTF-8") + "=" + URLEncoder.encode(stade_, "UTF-8") + "&" + URLEncoder.encode("strategie", "UTF-8") + "=" + URLEncoder.encode(strategie_, "UTF-8")  + "&" + URLEncoder.encode("couverture", "UTF-8") + "=" + URLEncoder.encode(couverture_, "UTF-8")+ "&" + URLEncoder.encode("cin", "UTF-8") + "=" + URLEncoder.encode(cin_, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("liste_patients")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/liste_patients.php";

                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("medecins")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/liste_medecins.php";
                URL url = new URL(url_php);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (typ.equals("detail_profil")) {
            try {
                String url_php = "https://oncotool.watching-environment.com/detail_profil.php";
                URL url = new URL(url_php);
                String id_patient = params[0];
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                String post_data = URLEncoder.encode("id_patient", "UTF-8") + "=" + URLEncoder.encode(id_patient, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                conn.getInputStream().close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        loading.dismiss();
        if(s!=null){
            if (typ.equals("inscription")){
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject JObject = reader.getJSONObject("data");
                    int result = JObject.getInt("success");
                    moveAI(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (typ.equals("connexion")){
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject JObject = reader.getJSONObject("data");
                    int result = JObject.getInt("success");
                    String nom = JObject.getString("nom");
                    String prenom = JObject.getString("prenom");
                    int an = JObject.getInt("an");
                    int rn = JObject.getInt("rn");
                        moveC(result,nom,prenom,an,rn);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (typ.equals("recover")){
                try {
                    JSONObject reader = new JSONObject(s);
                    int result = reader.getInt("data");
//                    moveC(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (typ.equals("alertes")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject sized = reader.getJSONObject("data");
                    int size = sized.getInt("size");
                    String [][] alertes = new String[size][9];
                    String[] jArray;
                      for (int i=0;i<size;i++) {
                            JSONObject JObject = reader.getJSONObject(String.valueOf(i));
                            int grade = JObject.getInt("grade");
                            int id_patient = JObject.getInt("id_patient");
                            int id_rapport = JObject.getInt("id_rapport");
                            int id_alerte = JObject.getInt("id_alerte");
                            String nom = JObject.getString("nom");
                            String prenom = JObject.getString("prenom");
                            String num_dossier = JObject.getString("Num_dossier");
                            String tel = JObject.getString("Num_telephone");
                            String date_alerte = JObject.getString("date_alerte");
                                    jArray = new String[]{String.valueOf(grade), num_dossier, nom + " " + prenom, date_alerte, tel,String.valueOf(id_alerte),String.valueOf(id_patient),String.valueOf(id_rapport)};
                            alertes[i] = (String[]) jArray;
                           // Toast.makeText(context,alertes[i][5],Toast.LENGTH_SHORT).show();
                      }
                   moveAa(alertes,size);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (typ.equals("rapports")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject sized = reader.getJSONObject("data");
                    int size = sized.getInt("size");
                    String [][] rapports = new String[size][9];
                    String[] jArray;
                    for (int i=0;i<size;i++) {
                        JSONObject JObject = reader.getJSONObject(String.valueOf(i));
                       // int grade = JObject.getInt("grade");
                        String nom = JObject.getString("nom");
                        String prenom = JObject.getString("prenom");
                        int id_patient = JObject.getInt("id_patient");
                        int id_rapport = JObject.getInt("id_rapport");
                        String num_dossier = JObject.getString("Num_dossier");
                        String date_rapport = JObject.getString("date_envoi");
                        jArray = new String[]{ num_dossier, nom + " " + prenom,String.valueOf(id_patient),String.valueOf(id_rapport),date_rapport};
                        rapports[i] = (String[]) jArray;
                    }
                    moveAb(rapports,size);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (typ.equals("detail_rapport")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject pp = reader.getJSONObject("0");
                    JSONObject sized = pp.getJSONObject("data");
                    int size = sized.getInt("size");
                    nomrr = sized.getString("nom");
                    prenomrr = sized.getString("prenom");
                    id_patientrr = sized.getInt("id_patient");
                    id_rapportrr = sized.getInt("id_rapport");
                    dater = sized.getString("date_rapport");
                    num_dossierrr = sized.getString("Num_dossier");
                    telrr = sized.getString("Num_telephone");
                    String [][] symptoms = new String[size-1][2];
                    String[] jArray;
                    for (int i=1;i<size;i++) {
                        JSONObject JObject = reader.getJSONObject(String.valueOf(i));
                        int grade = JObject.getInt("grade");
                        String nom_symptom = JObject.getString("nomSymptom");
                        jArray = new String[]{nom_symptom,String.valueOf(grade)};
                        symptoms[i-1] = (String[]) jArray;
                    }
                    toDr(symptoms,size-1,nomrr,prenomrr,id_patientrr,id_rapportrr,num_dossierrr,telrr, dater);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (typ.equals("detail_alerte")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject JObject = reader.getJSONObject("data");
                        int grade = JObject.getInt("grade");
                        String date_alerte = JObject.getString("date_symptom");
                        String nom_symptom = JObject.getString("nom_symptom");
                        String nom = JObject.getString("nom");
                        String prenom = JObject.getString("prenom");
                        int id_patient = JObject.getInt("id_patient");
                        int id_rapport = JObject.getInt("id_rapport");
                        int id_alerte = JObject.getInt("id_alerte");
                        String num_dossier = JObject.getString("Num_dossier");
                        String tel = JObject.getString("Num_telephone");
                    toDa(nom,prenom,id_patient,id_rapport,num_dossier,tel,grade,date_alerte,nom_symptom,id_alerte);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (typ.equals("supprimer_alerte")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject JObject = reader.getJSONObject("data");
                    int result = JObject.getInt("success");
                    if (result == 1){
                        moveAL();
                      //  Home.ann--;
                        Toast.makeText(context,"Alerte supprimée!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Erreur!",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (typ.equals("supprimer_rapport")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject JObject = reader.getJSONObject("data");
                    int result = JObject.getInt("success");
                    if (result == 1){
                        moveSR();
                      //  Home.rnn--;
                        Toast.makeText(context,"rapport supprimée!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Erreur!",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (typ.equals("inscription_patient")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject JObject = reader.getJSONObject("data");
                    int ri = JObject.getInt("success");
                    Toast.makeText(context, String.valueOf(ri), Toast.LENGTH_SHORT).show();
                    ReactToInsPatient(ri);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (typ.equals("medecins")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject sized = reader.getJSONObject("data");
                    int size = sized.getInt("size");
                    String [][] medecins = new String[size][3];
                    String[] jArray;
                    for (int i=0;i<size;i++) {
                        JSONObject JObject = reader.getJSONObject(String.valueOf(i));
                        int id_medecin = JObject.getInt("id_medecin");
                        String nom= JObject.getString("nom");
                        String prenom = JObject.getString("prenom");
                        jArray = new String[]{String.valueOf(id_medecin),nom,prenom};
                        medecins[i] = (String[]) jArray;
                    }
                    medecins(medecins,size);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (typ.equals("liste_patients")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject sized = reader.getJSONObject("data");
                    int size = sized.getInt("size");
                    String [][] patients = new String[size][4];
                    String[] jArray;
                    for (int i=0;i<size;i++) {
                        JSONObject JObject = reader.getJSONObject(String.valueOf(i));
                        int id_patient = JObject.getInt("id_patient");
                        String Num_dossier= JObject.getString("Num_dossier");
                        String nom= JObject.getString("nom");
                        String prenom = JObject.getString("prenom");
                        jArray = new String[]{String.valueOf(id_patient),nom,prenom,Num_dossier};
                        patients[i] = (String[]) jArray;
                    }
                    toPL(patients,size);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (typ.equals("detail_profil")) {
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject sized = reader.getJSONObject("data");
                        int id_patient = sized.getInt("id_patient");
                        String nom= sized.getString("nom");
                        String cin= sized.getString("cin");
                        String prenom = sized.getString("prenom");
                        String adresse = sized.getString("adresse");
                        String centre = sized.getString("centre");
                        String couverture_social = sized.getString("couverture_social");
                        String Statut_civil = sized.getString("Statut_civil");
                        String Nombre_enfants = sized.getString("Nombre_enfants");
                        String Num_dossier = sized.getString("Num_dossier");
                        String Num_telephone = sized.getString("Num_telephone");
                        String Lieu_naissance = sized.getString("Lieu_naissance");
                        String date_naissance = sized.getString("date_naissance");
                        String Protocole = sized.getString("Protocole");
                        String Service = sized.getString("Service");
                        String Localisation = sized.getString("Localisation");
                        String Histologie = sized.getString("Histologie");
                        String Stade = sized.getString("Stade");
                        String Strategie = sized.getString("Strategie");
                        String nom_med = sized.getString("nom_med");
                        String prenom_med = sized.getString("pre_med");
                    toPD(String.valueOf(id_patient), nom, prenom, adresse, couverture_social, Statut_civil, Nombre_enfants, Num_dossier, Num_telephone, Lieu_naissance, date_naissance, Protocole, Service, Localisation, Histologie, Stade, Strategie, nom_med, prenom_med,cin,centre);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Toast.makeText(context, "Erreur fatale", Toast.LENGTH_SHORT).show();
        }

    }

    public void toPD(String A, String B, String C, String D, String E, String F, String G, String H, String I, String J, String K, String L, String M,String N,String O,String P, String Q, String R,  String S,String T, String U){
                Intent intent = new Intent(context, singleProfile.class);
                Bundle b = new Bundle();
                b.putString("id_patient",A);
                b.putString("nom",B);
                b.putString("prenom",C);
                b.putString("adresse",D);
                b.putString("couverture_social",E);
                b.putString("Statut_civil",F);
                b.putString("Nombre_enfants",G);
                b.putString("Num_dossier",H);
                b.putString("Num_telephone",I);
                b.putString("Lieu_naissance",J);
                b.putString("date_naissance",K);
                b.putString("Protocole",L);
                b.putString("Service",M);
                b.putString("Localisation",N);
                b.putString("Histologie",O);
                b.putString("Stade",P);
                b.putString("Strategie",Q);
                b.putString("nom_med",R);
                b.putString("prenom_med",S);
                b.putString("cin",T);
                b.putString("centre",U);
                intent.putExtras(b);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

    public void toPL(String [][]lp,int s){
        Intent intent = new Intent(context, Profil.class);
        Bundle b = new Bundle();
        b.putSerializable("patients",lp);
        b.putInt("size",s);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void medecins(String[][] i,int o){
        Intent intent = new Intent(context, AjouterPatient.class);
        Bundle b = new Bundle();
        b.putSerializable("medecins",i);
        b.putInt("size",o);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void ReactToInsPatient(int r){
        if (r == 1) {
            final Intent intent = new Intent(context, Patients.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(false);
            error.setIcon(R.drawable.confirm);
            error.setTitle("Succés");
            error.setMessage("Inscription réussite");
            error.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    context.startActivity(intent);
                }
            });
            error.create().show();

        } else if (r == 0){
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setIcon(R.drawable.exclamation_mark);
            error.setTitle("Erreur");
            error.setMessage("Les données inscription échouée!");
            error.create().show();
        } else {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setIcon(R.drawable.exclamation_mark);
            error.setTitle("Erreur");
            error.setMessage("Erreur interne !");
            error.create().show();
        }
    }

    public void moveAI(int r){
        if (r == 1) {
            final Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(false);
            error.setIcon(R.drawable.confirm);
            error.setTitle("Succès");
            error.setMessage("Cependant, vous ne pouvez pas accéder aux services de l'application avant qu'elle ne soit approuvée par l'administrateur !");
            error.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    context.startActivity(intent);
                }
            });
            error.create().show();

        } else if (r == 2){
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setIcon(R.drawable.exclamation_mark);
            error.setTitle("Erreur");
            error.setMessage("Veuillez vérifier vos informations. \n INPE et/ou Email invalide(s)!");
            error.create().show();
        } else {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setIcon(R.drawable.exclamation_mark);
            error.setTitle("Erreur");
            error.setMessage("Veuillez vérifier vos informations !");
            error.create().show();
        }
    }

    private void moveC(int res,String n, String p,int a, int r){
        final int an = a;
        final int rn = r;
        final String nm = n;
        final String pm = p;
        n = n.substring(0, 1).toUpperCase() + n.substring(1);
        p = p.substring(0, 1).toUpperCase() + p.substring(1);
        if (res == 1) {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(false);
            error.setIcon(R.drawable.lock);
            error.setTitle("Connexion réussite");
            error.setMessage("Bienvenue Dr. "+n+" "+p);
            error.setPositiveButton("Entrer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context, Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle b = new Bundle();
                    b.putInt("an",an);
                    b.putInt("rn",rn);
                    b.putString("nom",nm);
                    b.putString("prenom",pm);
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
            error.create().show();

        } else if (res == -1){
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setIcon(R.drawable.lock1);
            error.setTitle("Erreur");
            error.setMessage("Votre inscription n'a pas été encore approuvé par l'admin!");
            error.create().show();
        }else {
            AlertDialog.Builder error = new AlertDialog.Builder(context);
            error.setCancelable(true);
            error.setIcon(R.drawable.lock1);
            error.setTitle("Erreur");
            error.setMessage("Erreur d'authentification. Vérifiez vos identifiants!");
            error.create().show();
        }
    }

    public void moveSR(){
        final Intent intent = new Intent(context, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void moveAL(){
        final Intent intent = new Intent(context, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void moveAa(String[][] a,int aa){
        Intent intent = new Intent(context, Alertes.class);
        Bundle b = new Bundle();
        b.putSerializable("alertes",a);
        b.putInt("size",aa);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void moveAb(String[][] a,int aa){
        Intent intent = new Intent(context, Rapports.class);
        Bundle b = new Bundle();
        b.putSerializable("rapports",a);
        b.putInt("size",aa);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void toDr(String[][] a,int aa,String A,String B,int C,int D,String E,String F,String G){
        Intent intent = new Intent(context, Rapport.class);
        Bundle b = new Bundle();
        b.putSerializable("rapport",a);
        b.putInt("size",aa);
        b.putInt("id_rapport",D);
        b.putInt("id_patient",C);
        b.putString("nom",A);
        b.putString("prenom",B);
        b.putString("num_dossier",E);
        b.putString("tel",F);
        b.putString("date",G);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void toDa(String A,String B,int C,int D,String E,String F,int G,String H,String I,int J){
        Intent intent = new Intent(context, Alerte.class);
        Bundle b = new Bundle();
        b.putInt("id_rapport",D);
        b.putInt("id_patient",C);
        b.putString("nom",A);
        b.putString("prenom",B);
        b.putString("num_dossier",E);
        b.putString("tel",F);
        b.putInt("grade",G);
        b.putString("date",H);
        b.putString("nom_symptom",I);
        b.putInt("id_alerte",J);
        intent.putExtras(b);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}