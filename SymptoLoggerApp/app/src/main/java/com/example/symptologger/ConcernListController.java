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
    private static ConcernList concernList = null;

    static public ConcernList getConcernList(){
        if (concernList == null){
            concernList = new ConcernList();
        }
        return concernList;
    }

    public void addConcern(Concern concern){
        getConcernList().addConcern(concern);
    }

    public boolean containsConcern(Concern concern){
        return getConcernList().containsConcern(concern);
    }

    public void deleteConcern(Concern concern){
        getConcernList().deleteConcern(concern);
    }

    public int findCount(){
        return getConcernList().findCount();
    }
}
