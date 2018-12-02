package com.example.symptologger;

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

/**
 * Used for mapping values fro ElasticSearch
 *
 * @author Remi Arshad
 */

public class SourceAsObjectListMap {
    private String userID;
    private Integer memberID;
    private String userRole;
    private String code;

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

    public String getRole(){
        return userRole;
    }

    public String getCode(){
        return this.code;
    }

}
