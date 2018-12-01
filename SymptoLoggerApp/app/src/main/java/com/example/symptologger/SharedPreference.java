
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
    private ArrayList<Concern> concerns;


    SharedPreference() {
        super();
    }


    // update concerns
    public void saveConcerns(Context context, ArrayList<Concern> concerns) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(concerns);
        editor.putString(App, json);
        editor.apply();
    }


    public ArrayList<Concern> loadConcerns(Context context) {
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

    public void saveRecords(Context context, ArrayList<Record> records, int pos) {
        concerns = this.loadConcerns(context);
        Concern c = concerns.get(pos);
        for (Record r: records) {
            c.addRecord(r);
        }

        this.saveConcerns(context, concerns);
    }


    public ArrayList<Record> loadRecords(Context context, int pos) {
        concerns = this.loadConcerns(context);
        Concern c = concerns.get(pos);
        return (ArrayList<Record>) c.getRecords();
    }

}

//
//    public ArrayList<Concern> readConcerns(Context context) {
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        Gson gson = new Gson();
//        String json = sharedPrefs.getString(CONCERNS, null);
//
//        Type type = new TypeToken<ArrayList<Concern>>(){}.getType();
//        ArrayList<Concern>concerns = gson.fromJson(json, type);
//        if (concerns == null) {
//            concerns = new ArrayList<Concern>();
//        }
//
//        return concerns;
//    }
//
//    public void updateConcerns(Context context, ArrayList<Concern> new_concerns) {
//
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(new_concerns);
//        editor.putString(CONCERNS, json);
//        editor.apply();
//    }
