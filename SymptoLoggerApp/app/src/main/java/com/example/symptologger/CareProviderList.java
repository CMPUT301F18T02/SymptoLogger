package com.example.symptologger;

import java.util.ArrayList;

public class CareProviderList {
    private ArrayList<CareProvider> careProviders;

    public void addCareProviders(CareProvider careProvider) {

    }

    public void removeCareProviders(CareProvider careProvider) {}

    public int getCareProvidersCount() {
        return careProviders.size();
    }

    public ArrayList<CareProvider> getCareProviders() {
        return careProviders;
    }
}
