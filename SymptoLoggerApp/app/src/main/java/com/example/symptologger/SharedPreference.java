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

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

public class SharedPreference {

//    private String PATIENTS = "Patients";
//    private String CAREPROVIDERS = "Care Providers";
    private String CONCERNS = "Concerns";


    //    private ArrayList<User> userList;
//    private ArrayList<Patient> patients;
//    private ArrayList<CareProvider> careProviders;
    private ConcernList concernList;


    SharedPreference() {
        super();
    }

    public ArrayList<Concern> readConcerns(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(CONCERNS, null);

        Type type = new TypeToken<ArrayList<Concern>>(){}.getType();
        ArrayList<Concern>concerns = gson.fromJson(json, type);
        if (concerns == null) {
            concerns = new ArrayList<Concern>();
        }

        return concerns;
    }

    public void updateConcerns(Context context, ArrayList<Concern> new_concerns) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(new_concerns);
        editor.putString(CONCERNS, json);
        editor.apply();
    }

//    public ArrayList<Patient> getPatients(Context context) {
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        Gson gson = new Gson();
//        String json = sharedPrefs.getString(PATIENTS, null);
//        Type type = new TypeToken<List<Patient>>() {}.getType();
//        patients = gson.fromJson(json, type);
//        if (patients == null) {
//            patients = new ArrayList<Patient>();
//        }
//
//        return patients;
//    }

//    public ArrayList<CareProvider> getCareProviders(Context context) {
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        Gson gson = new Gson();
//        String json = sharedPrefs.getString(CAREPROVIDERS, null);
//        Type type = new TypeToken<List<Patient>>() {}.getType();
//        careProviders = gson.fromJson(json, type);
//        if (careProviders == null) {
//            careProviders = new ArrayList<CareProvider>();
//        }
//
//        return careProviders;
//    }
//
//    public void refreshPatients(Context context, ArrayList<Patient> patients) {
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(patients);
//        editor.putString(PATIENTS, json);
//        editor.commit();
//    }


//    public void refreshCareProviders(Context context, ArrayList<CareProvider> careProviders) {
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(careProviders);
//        editor.putString(CAREPROVIDERS, json);
//        editor.commit();
//    }


}