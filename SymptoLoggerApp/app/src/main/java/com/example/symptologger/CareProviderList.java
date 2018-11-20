package com.example.symptologger;

import java.util.ArrayList;

/**
 * Controller for list of care providers
 */
public class CareProviderList {
    private ArrayList<CareProvider> careProviders;

    /**
     * The constructor. Sets a new ArrayList
     */

    public CareProviderList() {
        this.careProviders = new ArrayList<>();
    }

    /**
     * Adds a new care provider to the list.
     * @param careProvider the new care provider
     */

    public void addCareProviders(CareProvider careProvider) {
        this.careProviders.add(careProvider);
    }

    /**
     * Removes a care provider
     * @param careProvider the care provider to be removed
     */

    public void removeCareProviders(CareProvider careProvider) {
        this.careProviders.remove(careProvider);
    }

    /**
     * Returns the number of care providers in the list.
     * @return number of care providers
     */

    public int getCareProvidersCount() {
        return this.careProviders.size();
    }

    /**
     * Gets the care providers
     * @return careProviders
     */

    public ArrayList<CareProvider> getCareProviders() {
        return this.careProviders;
    }
}
