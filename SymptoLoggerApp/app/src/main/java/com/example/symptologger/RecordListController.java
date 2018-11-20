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

public class RecordListController {
    private static RecordList recordList = null;

    static public RecordList getRecordList(){
        if (recordList == null){
            recordList = new RecordList();
        }
        return recordList;
    }

    public void addRecord(Record record){
        getRecordList().addRecord(record);
    }

    public boolean containsRecord(Record record){
        return getRecordList().containsRecord(record);
    }

    public void deleteRecord(Record record){
        getRecordList().deleteRecord(record);
    }

    public int findCount(){
        return getRecordList().findCount();
    }
}
