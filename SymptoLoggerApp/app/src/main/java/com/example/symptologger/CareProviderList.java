package com.example.symptologger;

import java.util.ArrayList;

/**
 * Controller for list of care providers
 */
public class CareProviderList {
    private ArrayList<CareProvider> careProviders;

    public CareProviderList() {
        this.careProviders = new ArrayList<>();
    }

    public void addCareProviders(CareProvider careProvider) {
        this.careProviders.add(careProvider);
    }

    public void removeCareProviders(CareProvider careProvider) {
        this.careProviders.remove(careProvider);
    }

    public int getCareProvidersCount() {
        return this.careProviders.size();
    }

    public ArrayList<CareProvider> getCareProviders() {
        return this.careProviders;
    }
}
