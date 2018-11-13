package com.example.symptologger;

import java.util.ArrayList;

public class CareProviderList {
    private ArrayList<CareProvider> careProviders;

    public CareProviderList() {
        this.careProviders = new ArrayList<>();
    }

    public void addCareProviders(CareProvider careProvider) {

    }

    public void removeCareProviders(CareProvider careProvider) {}

    public int getCareProvidersCount() {
        return this.careProviders.size();
    }

    public ArrayList<CareProvider> getCareProviders() {
        return this.careProviders;
    }
}
