package com.example.symptologger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SharedPreference {

    private String App = "App";
    private String STATUS = "Status";
    private String USERNAME = "UserName";
    private ArrayList<Concern> concerns;


    SharedPreference() {
        super();
    }

    // 1 for patient
    // 2 for cp
    void storeUserName(Context context, String userName) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userName);
        editor.putString(USERNAME, json);
        editor.apply();
    }

    String loadUserName(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(USERNAME, null);
        Type type = new TypeToken<String>(){}.getType();
        String userName = gson.fromJson(json, type);
        if (userName == null) {
            return "";
        }

        return userName;
    }

    void updateLogInStatus(Context context, Integer role) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(role);
        editor.putString(STATUS, json);
        editor.apply();
    }

    int getLogInStatus(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(STATUS, null);
        Type type = new TypeToken<Integer>(){}.getType();
        Integer status = gson.fromJson(json, type);
        if (status == null) {
            return 0;
        }

        return status;
    }

    void saveConcerns(Context context, ArrayList<Concern> concerns) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(concerns);
        editor.putString(App, json);
        editor.apply();
    }

    void saveOneConcern(Context context, Concern concern) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        concerns = this.loadConcerns(context);
        concerns.add(concern);
        this.saveConcerns(context, concerns);
//        String json = gson.toJson(concerns);
//        editor.putString(App, json);
//        editor.apply();
    }

    void deleteOneConcern(Context context, Concern concern) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        concerns = this.loadConcerns(context);
        concerns.remove(concern);
        this.saveConcerns(context, concerns);
//        String json = gson.toJson(concerns);
//        editor.putString(App, json);
//        editor.apply();
    }


    ArrayList<Concern> loadConcerns(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(App, null);

        Type type = new TypeToken<ArrayList<Concern>>(){}.getType();
        ArrayList<Concern>concerns = gson.fromJson(json, type);
        if (concerns == null) {
            concerns = new ArrayList<Concern>();
        }

        return concerns;
    }

    ArrayList<Record> loadRecords(Context context, String concernTitle) {
        ArrayList<Record> records = new ArrayList<Record>();
        concerns = this.loadConcerns(context);
        for (Concern c: concerns) {
            if (c.getTitle().equals(concernTitle)) {
                records = (ArrayList<Record>) c.getRecords();
            }
        }

        return records;
    }

    void saveRecords(Context context, ArrayList<Record> records, String concernTitle) {
        concerns = this.loadConcerns(context);
        for (Concern c: concerns) {
            if (c.getTitle().equals(concernTitle)) {
                c.setRecords(records);
            }
        }

        this.saveConcerns(context, concerns);
    }


}