package com.example.symptologger;

import java.util.ArrayList;

public class CareProviderList {
    private ArrayList<CareProvider> careProviders;

    public void addCareProviders(Patient p) {

    }

    public void removeCareProviders(Patient p) {}

    public int getCareProvidersCount() {
        return careProviders.size();
    }

    public ArrayList<CareProvider> getCareProviders() {
        return careProviders;
    }
}
