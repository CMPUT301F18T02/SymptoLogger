package com.example.symptologger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreference {

    private String PATIENTS = "Patients";
    private String CAREPROVIDERS = "Care Providers";
    private String CONCERNS = "Concers";


    //    private ArrayList<User> userList;
    private ArrayList<Patient> patients;
    private ArrayList<CareProvider> careProviders;
    private ArrayList<Concern> concerns;


    SharedPreference() {
        super();
    }

    public ArrayList<Concern> getConcerns(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(CONCERNS, null);
        Type type = new TypeToken<ArrayList<Concern>>() {}.getType();
        concerns = gson.fromJson(json, type);
        if (concerns == null) {
            concerns = new ArrayList<Concern>();
        }

        return concerns;
    }

    public ArrayList<Patient> getPatients(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(PATIENTS, null);
        Type type = new TypeToken<List<Patient>>() {}.getType();
        patients = gson.fromJson(json, type);
        if (patients == null) {
            patients = new ArrayList<Patient>();
        }

        return patients;
    }

    public ArrayList<CareProvider> getCareProviders(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(CAREPROVIDERS, null);
        Type type = new TypeToken<List<Patient>>() {}.getType();
        careProviders = gson.fromJson(json, type);
        if (careProviders == null) {
            careProviders = new ArrayList<CareProvider>();
        }

        return careProviders;
    }

    public void refreshPatients(Context context, ArrayList<Patient> patients) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(patients);
        editor.putString(PATIENTS, json);
        editor.commit();
    }


    public void refreshCareProviders(Context context, ArrayList<CareProvider> careProviders) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(careProviders);
        editor.putString(CAREPROVIDERS, json);
        editor.commit();
    }

    public void refreshConcerns(Context context, ArrayList<Concern> concerns) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(concerns);
        editor.putString(CONCERNS, json);
        editor.commit();
    }

}