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

import java.util.ArrayList;

/**
 * The controller for the concern list. This allows access to the concern list without having to
 * modify it directly.
 */

public class ConcernListController {
    private static ConcernList concernList;

    static public ConcernList getConcernList(String userName){
        concernList = new ConcernList(userName);
        return concernList;
    }

    public void addConcern(Concern concern, String userName){
        getConcernList(userName).addConcern(concern, userName);
    }

    public boolean containsConcern(Concern concern, String userName){
        return getConcernList(userName).containsConcern(concern);
    }

    public void deleteConcern(Concern concern, String userName){
        getConcernList(userName).deleteConcern(concern, 999);
    }

    public int findCount(String userName){
        return getConcernList(userName).findCount();
    }
}
