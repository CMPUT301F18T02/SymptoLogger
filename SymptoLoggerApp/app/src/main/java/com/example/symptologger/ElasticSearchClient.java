package com.example.symptologger;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.DeleteByQuery;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.PutMapping;

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
 * ElasticSearchClient represents and handles all interactions with the ElasticSearch server. It creates
 * subclasses that represent specific objects for particular interactions (searching, adding, etc.).
 *
 * @author Remi Arshad
 */

public class ElasticSearchClient {

    private static JestClient client = null;
    private static final String server = "http://cmput301.softwareprocess.es:8080";
    private static final String index = "cmput301f18t02";


    /*
     * Initialize connection to server ...
      */
    static { // need static here since initClient is static
        initClient();
    }

    /**
     * initClient() initializes the connection to the ElasticSearch server, constructing a new Jest
     * client.
     */

    public static void initClient() {
        // Construct a new Jest client according to configuration via factory
        if (client == null) {
            JestClientFactory factory = new JestClientFactory();
            DroidClientConfig config = new DroidClientConfig.Builder(server).build();
            factory.setDroidClientConfig(config);
            client = factory.getObject();
        }
    }

    /**
     * Represents the object used to delete indices in the ElasticSearch server. Not used in prototype.
     * Takes as a parameter the string representation of the username to be deleted.
     */

    //AVOID USING DeleteIndices FOR NOW!
    public static class DeleteIndices extends AsyncTask<String, Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String... indices) {
            for (String index : indices) {
                try {
                    JestResult result = client.execute(new DeleteIndex.Builder(index).build());
                    if (!result.isSucceeded()) {
                        Log.e("Error", "ElasticSearch was not able to delete some index.");
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed - reason: DeleteIndices.");
                }
            }
            return null; //Void requires return, (it's not void)
        }
    }

    /**
     * Represents the object used to add a new table to the server.
     */

    public static class AddConcernsTable extends AsyncTask<String, Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String... indices) {

            String type = "Concerns";
            String source = "{\"Concerns\" : {\"properties\" : " +
                    "{\"title\": {\"type\" : \"string\", \"index\": \"not_analyzed\"}," +
                    "\"date\": {\"type\" : \"string\"}, " +
                    "\"description\": {\"type\" : \"string\"}, " +
                    "\"userName\" : {\"type\" : \"string\", \"index\": \"not_analyzed\"}" +
                    "}}}";

                try {

                    JestResult result = client.execute(new PutMapping.Builder(index, type, source).build());
                    if (!result.isSucceeded()) {
                        Log.e("Error", "ElasticSearch was not able to add table.");
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed - reason: AddConcernsTable.");
                }
            return null; //Void requires return, (it's not void)
        }
    }

    /**
     * Represents the object used to add a new user to the server. The nested doInBackground method
     * returns Boolean.TRUE or Boolean.FALSE depending on whether or not the user was successfully added.
     * Takes as parameter the username to be added.
     *
     * @author Remi Arshad
     */

    public static class AddUser extends AsyncTask<String, Void, Boolean> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Boolean doInBackground(String... record) {

            String type = "usersLogin";
            String source = String.format("{\"userID\": \"%s\"," +
                    " \"creationDate\": \"%s\", " +
                    "\"userRole\": \"%s\", " +
                    "\"memberID\": %d, " +
                    "\"email\": \"%s\", " +
                    "\"phone\": \"%s\"}",
                    record[0],
                    record[1],
                    record[2],
                    Integer.parseInt(record[3]),
                    record[4],
                    record[5]);

            try {
                JestResult result = client.execute( new Index.Builder(source).index(index).type(type).build() );

                if (result.isSucceeded()) {
                    return Boolean.TRUE;
                }
                else{
                    return Boolean.FALSE;
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed - reason: AddUser.");
            }
            return Boolean.FALSE;
        }
    }

    /**
     * Represents the object used to search for a user in the server. It takes as parameter the
     * username to be found.
     *
     * @author Remi Arshad
     */

    public static class SearchUser extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... search_parameters){

            String val = null;

            String type = "usersLogin";
            String query =  String.format("{\"query\": {\"match\": {\"userID\": \"%s\"}}}", search_parameters[0]);
            try {
                JestResult result = client.execute(  new Search.Builder(query).addIndex(index).addType(type).build() );

                if (result.isSucceeded()){
                    List<SourceAsObjectListMap> res = result.getSourceAsObjectList(SourceAsObjectListMap.class);
                    if (res.size() != 0){
                        val = "";
                    }
                    else{
                        //Log.e("Error","nothing found.");
                        val = "The user name was not found.";
                    }


                } else {
                    Log.e("Error","Some issues with query.");
                    val = "The was an issue with the ElasticSearch query; please try again.";
                }
            } catch (Exception e){
                Log.i("Error","Something went wrong when we tried to communicate with the elasticsearch server.");
                val = "There was an issue with communicating with the server; it may be offline. Try again.";
            }
            return val;
        }
    }

    public static class FetchChatLogs extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... search_parameters){

            String type = "chatLogs";
            String query =  String.format("{\"query\":{\"bool\":{\"must\":[{\"match\":{\"recordID\":\"%s\"}}, {\"match\":{\"participantsID\":\"%s\"}},{\"match\":{\"participantsID\":\"%s\"}}]}},\"sort\":{\"timestamp\":\"asc\"},\"size\":1000}", search_parameters[0], search_parameters[1], search_parameters[2]);
            try {
                List<SearchResult.Hit<ChatLogs,Void>> hits = client.execute(  new Search.Builder(query).addIndex(index).addType(type).build() ).getHits(ChatLogs.class);

                if (hits.size() != 0){
                    RecordCommentFragment.chatLogs.add(hits.stream()
                            .map(result -> new ChatLogs(result.source.getRecordID(), result.source.getParticipantsID(), result.source.getMessage(), result.source.getTimestamp()))
                            .collect(Collectors.toList()));
                    System.out.println("FETCHING DONE!");
                    RecordCommentFragment.updateLogsReady = true;
                    System.out.println("UPDATING VIEW DONE!");

                    try {
                        ChatManager.callViewUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return null;
                }
                else{
                    return null;
                }
            } catch (Exception e){
                Log.i("Error","Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return null;
        }
    }

    public static class SaveChatLog extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... save_parameters){


            String type = "chatLogs";
            String source = String.format("{\"recordID\":\"%s\",\"participantsID\":[\"%s\",\"%s\"],\"message\":\"%s\",\"timestamp\":\"%s\"}", save_parameters[0], save_parameters[1], save_parameters[2], save_parameters[3], save_parameters[4]);
            System.out.println(source);

            try {
                JestResult result = client.execute( new Index.Builder(source).index(index).type(type).build() );

                if (result.isSucceeded()) {
                    System.out.println("SAVING DONE!");
                    return null;
                }
                else{
                    return null;
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed - reason: AddChatLog.");
            }
            return null;
        }
    }

    public static class GetUserRole extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... search_parameters) {

            String type = "usersLogin";
            String query = String.format("{\"query\": {\"match\": {\"userID\": \"%s\"}}}", search_parameters[0]);
            try {
                JestResult result = client.execute(new Search.Builder(query).addIndex(index).addType(type).build());
                if (result.isSucceeded()) {
                    List<SourceAsObjectListMap> res = result.getSourceAsObjectList(SourceAsObjectListMap.class);
                    if (res.size() != 0) {
                        return res.get(0).getRole();
                    } else {
                        //Log.e("Error","nothing found.");
                        return "";
                    }
                } else {
                    Log.e("Error", "Some issues with query.");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return "";
        }
    }

        public static class AddConcern extends AsyncTask<String, Void, Boolean> { //use Void instead of void for AsyncTask as return type
            @Override
            protected Boolean doInBackground(String... record) {

                String type = "Concerns";
                String source = String.format("{\"title\": \"%s\", \"date\": \"%s\", \"description\": \"%s\", \"userName\": \"%s\"}", record[0], record[1], record[2], record[3]);

                try {
                    JestResult result = client.execute(new Index.Builder(source).index(index).type(type).build());

                    if (result.isSucceeded()) {
                        return Boolean.TRUE;
                    } else {
                        return Boolean.FALSE;
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed - reason: AddConcern.");
                }
                return Boolean.FALSE;
            }
        }

        public static class GetConcerns extends AsyncTask<String, Void, ArrayList<Concern>> {

            @Override
            protected ArrayList<Concern> doInBackground(String... search_parameters) {

                ArrayList<Concern> foundConcerns = new ArrayList<Concern>();
                String type = "Concerns";
                String query = String.format("{\"query\": {\"match\": {\"userName\": \"%s\"}}}", search_parameters[0]);
                try {
                    JestResult result = client.execute(new Search.Builder(query).addIndex(index).addType(type).build());
                    if (result.isSucceeded()) {
                        List<Concern> res = result.getSourceAsObjectList(Concern.class);
                        if (res.size() != 0) {
                            foundConcerns.addAll(res);
                        } else {
                            Log.e("Error", "nothing found.");
                        }
                    } else {
                        Log.e("Error", "Some issues with the GetConcerns query");
                    }
                } catch (Exception e) {
                    Log.e("Error", "Something went wrong when trying to communicate with the elasticsearch server");
                }
                return foundConcerns;
            }
        }

        public static class AddRecord extends AsyncTask<String, Void, Boolean> { //use Void instead of void for AsyncTask as return type
            @Override
            protected Boolean doInBackground(String... record) {

                String type = "Records";
                String source = String.format("{\"title\": \"%s\", \"date\": \"%s\", \"concernTitle\": \"%s\", \"userName\": \"%s\", \"created\": \"%s\"}", record[0], record[1], record[2], record[3], record[4]);

                try {
                    JestResult result = client.execute(new Index.Builder(source).index(index).type(type).build());
                    if (result.isSucceeded()) {
                        return Boolean.TRUE;
                    } else {
                        return Boolean.FALSE;
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed - reason: AddRecord.");
                }
                return Boolean.FALSE;
            }
        }


        public static class GetRecords extends AsyncTask<String, Void, ArrayList<Record>> {

            @Override
            protected ArrayList<Record> doInBackground(String... search_parameters) {

                ArrayList<Record> foundRecords = new ArrayList<Record>();
                String type = "Records";
                String query = String.format(
                        "{\"query\": {\"bool\": " +
                                "{\"must\": [" +
                                "{\"match\": {\"concernTitle\": \"%s\"}}, " +
                                "{\"match\": {\"userName\": \"%s\"}}]}}}", search_parameters[0], search_parameters[1]);
                try {
                    JestResult result = client.execute(new Search.Builder(query).addIndex(index).addType(type).build());
                    if (result.isSucceeded()) {
                        List<Record> res = result.getSourceAsObjectList(Record.class);
                        if (res.size() != 0) {
                            foundRecords.addAll(res);
                        } else {
                            Log.e("Error", "nothing found.");
                        }
                    } else {
                        Log.e("Error", "Some issues with query.");
                    }
                } catch (Exception e) {
                    Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server.");
                }
                return foundRecords;
            }
        }

        public static class DeleteRecord extends AsyncTask<String, Void, Boolean> {
            @Override
            protected Boolean doInBackground(String... search_parameters) {

                String type = "Records";
                String query = String.format(
                        "{\"query\": {\"bool\": " +
                                "{\"must\": [" +
                                "{\"match\": {\"title\": \"%s\"}}, " +
                                "{\"match\": {\"concernTitle\": \"%s\"}}]}}}", search_parameters[0], search_parameters[1]);
                try {
                    JestResult result = client.execute(new DeleteByQuery.Builder(query).addIndex(index).addType(type).build());
                    if (result.isSucceeded()) {
                        return Boolean.TRUE;
                    } else {
                        Log.e("Error", "Some issues with DeleteRecord query.");
                    }
                } catch (Exception e) {
                    Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server.");
                }
                return Boolean.FALSE;
            }
        }

        public static class DeleteConcern extends AsyncTask<String, Void, Boolean> {
            @Override
            protected Boolean doInBackground(String... search_parameters) {
                String type = "Concerns";
                String query = String.format(
                        "{\"query\": {\"bool\": " +
                                "{\"must\": [" +
                                "{\"match\": {\"title\": \"%s\"}}, " +
                                "{\"match\": {\"userName\": \"%s\"}}]}}}", search_parameters[0], search_parameters[1]);
                try {
                    JestResult result = client.execute(new DeleteByQuery.Builder(query).addIndex(index).addType(type).build());
                    if (result.isSucceeded()) {
                        return Boolean.TRUE;
                    } else {
                        Log.e("Error", "Some issues with DeleteRecord query.");
                    }
                } catch (Exception e) {
                    Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server.");
                }
                return Boolean.FALSE;
            }
        }

    public static class AddPatientsTable extends AsyncTask<String, Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String... indices) {

            String type = "Patients";
            String source = "{\"Patients\" : {\"properties\" : " +
                    "{\"userID\": {\"type\" : \"string\", \"index\": \"not_analyzed\"}," +
                    "\"email\": {\"type\" : \"string\"}, " +
                    "\"cell\": {\"type\" : \"string\"}, " +
                    "\"cpUserName\" : {\"type\" : \"string\", \"index\": \"not_analyzed\"}," +
                    "\"created\": {\"type\" : \"date\"}," +
                    "}}}";

            try {
                JestResult result = client.execute(new PutMapping.Builder(index, type, source).build());
                if (!result.isSucceeded()) {
                    Log.e("Error", "ElasticSearch was not able to add table.");
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed - reason: AddPatientsTable.");
            }
            return null; //Void requires return, (it's not void)
        }
    }

    public static class AddPatient extends AsyncTask<String, Void, Boolean> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Boolean doInBackground(String... record) {

            String type = "Patients";
            String source = String.format("{\"userID\": \"%s\", \"email\": \"%s\", \"cell\": \"%s\", \"cpUserName\": \"%s\", \"created\": \"%s\"}", record[0], record[1], record[2], record[3], record[4]);

            try {
                JestResult result = client.execute(new Index.Builder(source).index(index).type(type).build());

                if (result.isSucceeded()) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed - reason: AddPatient.");
            }
            return Boolean.FALSE;
        }
    }

    public static class GetSinglePatient extends AsyncTask<String, Void, Patient> {

        @Override
        protected Patient doInBackground(String... search_parameters) {

            Patient p = null;

            String type = "Patients";
            String query = String.format("{\"query\": {\"match\": {\"userID\": \"%s\"}}}", search_parameters[0]);
            try {
                JestResult result = client.execute(new Search.Builder(query).addIndex(index).addType(type).build());
                if (result.isSucceeded()) {
                    List<Patient> res = result.getSourceAsObjectList(Patient.class);
                    if (res.size() != 0) {
                        p = res.get(0);
                    } else {
                        Log.e("Error","nothing found.");
                    }
                } else {
                    Log.e("Error", "Some issues with GetShareCode query.");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return p;
        }
    }


    public static class GetPatients extends AsyncTask<String, Void, ArrayList<Patient>> {

        @Override
        protected ArrayList<Patient> doInBackground(String... search_parameters) {

            ArrayList<Patient> foundPatients = new ArrayList<Patient>();
            String type = "Patients";
            String query = String.format("{\"query\": {\"match\": {\"cpUserName\": \"%s\"}}}", search_parameters[0]);
            try {
                JestResult result = client.execute(new Search.Builder(query).addIndex(index).addType(type).build());
                if (result.isSucceeded()) {
                    List<Patient> res = result.getSourceAsObjectList(Patient.class);
                    if (res.size() != 0) {
                        foundPatients.addAll(res);
                    } else {
                        Log.e("Error", "nothing found.");
                    }
                } else {
                    Log.e("Error", "Some issues with GetPatients query.");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return foundPatients;
        }
    }

    public static class DeletePatient extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... search_parameters) {
            String type = "Patients";
            String query = String.format(
                    "{\"query\": {\"match\": {\"userID\": \"%s\"}}}", search_parameters[0]);
            try {
                JestResult result = client.execute(new DeleteByQuery.Builder(query).addIndex(index).addType(type).build());
                if (result.isSucceeded()) {
                    return Boolean.TRUE;
                } else {
                    Log.e("Error", "Some issues with DeletePatient query.");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return Boolean.FALSE;
        }
    }


    public static class AddShareCodeTable extends AsyncTask<String, Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String... indices) {

            String type = "shareCode";
            String source = "{\"shareCode\" : {\"properties\" : " +
                    "{\"userID\": {\"type\" : \"string\", \"index\": \"not_analyzed\"}," +
                    "\"code\": {\"type\" : \"string\"}, " +
                    "\"created\": {\"type\" : \"date\"}," +
                    "}}}";

            try {
                JestResult result = client.execute(new PutMapping.Builder(index, type, source).build());
                if (!result.isSucceeded()) {
                    Log.e("Error", "ElasticSearch was not able to add table.");
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed - reason: AddShareCodeTable.");
            }
            return null; //Void requires return, (it's not void)
        }
    }

    public static class AddShareCode extends AsyncTask<String, Void, Boolean> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Boolean doInBackground(String... record) {

            String type = "shareCode";
            String source = String.format("{\"userID\": \"%s\", \"code\": \"%s\", \"created\": \"%s\"}", record[0], record[1], record[2]);

            try {
                JestResult result = client.execute(new Index.Builder(source).index(index).type(type).build());

                if (result.isSucceeded()) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed - reason: AddShareCode.");
            }
            return Boolean.FALSE;
        }
    }

    public static class DeleteShareCode extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... search_parameters) {
            String type = "shareCode";
            String query = String.format(
                    "{\"query\": {\"bool\": " +
                            "{\"must\": [" +
                            "{\"match\": {\"userID\": \"%s\"}}, " +
                            "{\"match\": {\"code\": \"%s\"}}]}}}", search_parameters[0], search_parameters[1]);
            try {
                JestResult result = client.execute(new DeleteByQuery.Builder(query).addIndex(index).addType(type).build());
                if (result.isSucceeded()) {
                    return Boolean.TRUE;
                } else {
                    Log.e("Error", "Some issues with DeleteShareCode query.");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return Boolean.FALSE;
        }
    }

    public static class GetShareCode extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... search_parameters) {

            String type = "shareCode";
            String query = String.format("{\"query\": {\"match\": {\"userID\": \"%s\"}}}", search_parameters[0]);
            try {
                JestResult result = client.execute(new Search.Builder(query).addIndex(index).addType(type).build());
                if (result.isSucceeded()) {
                    List<SourceAsObjectListMap> res = result.getSourceAsObjectList(SourceAsObjectListMap.class);
                    if (res.size() != 0) {
                        return res.get(0).getCode();
                    } else {
                        //Log.e("Error","nothing found.");
                        return "";
                    }
                } else {
                    Log.e("Error", "Some issues with GetShareCode query.");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return "";
        }
    }

    public static class AddRecordTable extends AsyncTask<String, Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String... indices) {

            String type = "Records";
            String source = "{\"Records\" : {\"properties\" : " +
                    "{\"title\": {\"type\" : \"string\", \"index\": \"not_analyzed\"}," +
                    "\"date\": {\"type\" : \"date\"}, " +
                    "\"concernTitle\": {\"type\" : \"string\", \"index\": \"not_analyzed\"}," +
                    "\"userName\": {\"type\" : \"string\", \"index\": \"not_analyzed\"}," +
                    "\"created\": {\"type\" : \"date\"}," +
                    "}}}";

            try {
                JestResult result = client.execute(new PutMapping.Builder(index, type, source).build());
                if (!result.isSucceeded()) {
                    Log.e("Error", "ElasticSearch was not able to add table.");
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed - reason: AddRecordTable.");
            }
            return null; //Void requires return, (it's not void)
        }
    }
}