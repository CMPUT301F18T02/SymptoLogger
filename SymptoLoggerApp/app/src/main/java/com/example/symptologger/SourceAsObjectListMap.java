package com.example.symptologger;

/**
 * Used for mapping values fro ElasticSearch
 *
 * @author Remi Arshad
 */

public class SourceAsObjectListMap {
    private String userID;
    private Integer memberID;

    /**
     * Gets the user id
     * @return userID
     */

    public String getUserID() { return userID; }

    /**
     * Gets the member id
     * @return memberID
     */

    public Integer getMemberID() {
        return memberID;
    }

}
