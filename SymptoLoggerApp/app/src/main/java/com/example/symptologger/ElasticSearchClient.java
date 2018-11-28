package com.example.symptologger;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
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

    public static class AddTable extends AsyncTask<String, Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String... indices) {

            String type = "Concern";
            String source = "{\"Concern\" : {\"properties\" : " +
                    "{\"concernTitle\": {\"type\" : \"string\"}," +
                    "\"concernDate\": {\"type\" : \"date\"}, " +
                    "\"concernDescription\": {\"type\" : \"string\"}, " +
                    "\"userName\" : {\"type\" : \"string\", \"index\": \"not_analyzed\"}," +
                    "\"created\": {\"type\" : \"date\"}," +
                    "}}}";

                try {
                    JestResult result = client.execute(new PutMapping.Builder(index, type, source).build());
                    if (!result.isSucceeded()) {
                        Log.e("Error", "ElasticSearch was not able to add table.");
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed - reason: AddTable.");
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

    public static class SearchUser extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... search_parameters){

            String type = "usersLogin";
            String query =  String.format("{\"query\": {\"match\": {\"userID\": \"%s\"}}}", search_parameters[0]);
            try {
                JestResult result = client.execute(  new Search.Builder(query).addIndex(index).addType(type).build() );

                if (result.isSucceeded()){
                    List<SourceAsObjectListMap> res = result.getSourceAsObjectList(SourceAsObjectListMap.class);
                    if (res.size() != 0){
                        return Boolean.TRUE;
                    }
                    else{
                        //Log.e("Error","nothing found.");
                        return Boolean.FALSE;
                    }


                } else {
                    Log.e("Error","Some issues with query.");
                }
            } catch (Exception e){
                Log.i("Error","Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return Boolean.FALSE;
        }
    }

    /**
     * Represents the object used to find the largest member id.
     *
     * @author Remi Arshad
     */

    public static class SearchLargestMemberID extends AsyncTask<String, Void, Integer>{

        @Override
        protected Integer doInBackground(String... search_parameters){

            String type = "usersLogin";
            String query =  "{\"query\": {\"match_all\": {}},\"sort\": {\"memberID\": \"desc\"},\"size\": 1}";
            try {
                JestResult result = client.execute(  new Search.Builder(query).addIndex(index).addType(type).build() );

                if (result.isSucceeded()){
                    List<SourceAsObjectListMap> res = result.getSourceAsObjectList(SourceAsObjectListMap.class);
                    if (res.size() != 0){
                        return res.get(0).getMemberID();
                    }
                    else{
                        return -1;
                    }


                } else {
                    Log.e("Error","Some issues with query.");
                }
            } catch (Exception e){
                Log.i("Error","Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return -1;
        }
    }

    public static class AddConcern extends AsyncTask<String, Void, Boolean> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Boolean doInBackground(String... record) {

            String type = "Concerns";
            String source = String.format("{\"title\": \"%s\", \"date\": \"%s\", \"description\": \"%s\", \"userName\": \"%s\", \"created\": \"%s\"}",record[0], record[1], record[2], record[3], record[4]);

            try {
                JestResult result = client.execute( new Index.Builder(source).index(index).type(type).build() );

                if (result.isSucceeded()) {
                    return Boolean.TRUE;
                }
                else{
                    return Boolean.FALSE;
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed - reason: AddConcern.");
            }
            return Boolean.FALSE;
        }
    }

    public static class GetConcerns extends AsyncTask<String, Void, ArrayList<Concern>>{

        @Override
        protected ArrayList<Concern> doInBackground(String... search_parameters){

            ArrayList<Concern> foundConcerns = new ArrayList<Concern>();
            String type = "Concerns";
            String query =  String.format("{\"query\": {\"match\": {\"userName\": \"%s\"}}}", search_parameters[0]);
            try {
                JestResult result = client.execute(  new Search.Builder(query).addIndex(index).addType(type).build() );

                if (result.isSucceeded()){
                    List<Concern> res = result.getSourceAsObjectList(Concern.class);
                    if (res.size() != 0){
                        foundConcerns.addAll(res);
                    }
                    else{
                        Log.e("Error","nothing found.");
                    }


                } else {
                    Log.e("Error","Some issues with query.");
                }
            } catch (Exception e){
                Log.i("Error","Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return foundConcerns;
        }
    }

}