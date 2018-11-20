package com.example.symptologger;

import java.util.ArrayList;
import java.util.Collection;

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
 * The RecordList class represents objects that are collections of records. Each concern has its own
 * list of records.
 *
 * @author Patrick Tamm
 * @see Concern, Record
 */

class RecordList {

    private ArrayList<Record> myRecords;

    /**
     * Constructor for RecordList class. Sets a new ArrayList of type Record.
     */

    RecordList(){
        this.myRecords = new ArrayList<Record>();
    }

    /**
     * gets the record list
     * @return myRecords
     */

    public Collection<Record> getRecords(){
        return this.myRecords;
    }

    /**
     * adding a new record to the list.
     * @param record the new record to be added.
     */

    public void addRecord(Record record) {
        this.myRecords.add(record);
    }


    /**
     * determines if a record is contained in the record list.
     * @param record the record to be found
     * @return true/false
     */

    public boolean containsRecord(Record record) {
        return this.myRecords.contains(record);
    }

    /**
     * removes a record from the list
     * @param record the record to be deleted.
     */

    public void deleteRecord(Record record) {
        this.myRecords.remove(record);
    }

    /**
     * determines the size of the record list.
     * @return myRecords.size()
     */

    public int findCount() {
        return this.myRecords.size();
    }
}
