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
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.mapping.PutMapping;

public class ElasticSearchClient {

    private static JestClient client = null;
    private static final String server = "http://cmput301.softwareprocess.es:8080";
    private static final String index = "cmput301f18t02";

    static { // need static here since initClient is static
        initClient();
    }

    public static void initClient() {
        // Construct a new Jest client according to configuration via factory
        if (client == null) {
            JestClientFactory factory = new JestClientFactory();
            DroidClientConfig config = new DroidClientConfig.Builder(server).build();
            factory.setDroidClientConfig(config);
            client = factory.getObject();
        }
    }

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

    public static class AddTable extends AsyncTask<String[], Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String[]... indices) {

            String type = "usersLogin";
            String source = "{\"usersLogin\" : {\"properties\" : {\"userID\": {\"type\" : \"integer\"},\"userName\" : {\"type\" : \"string\", \"index\": \"not_analyzed\"},\"creationDate\": {\"type\" : \"string\"},\"userRole\": {\"type\" : \"string\"}}}}";

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

    public static class AddRecord extends AsyncTask<String[], Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String[]... indices) {

            String type = "usersLogin";
            String source = "{\"userName\": \"patrick\", \"creationDate\": \"2018-11-16\", \"userRole\": \"P\", \"userID\": 13 }";
            //String id = "9";

            try {
                //JestResult result = client.execute( new Index.Builder(source).index(index).type(type).id(id).build() );
                JestResult result = client.execute( new Index.Builder(source).index(index).type(type).build() );

                if (!result.isSucceeded()) {
                    Log.e("Error", "ElasticSearch was not able to add record.");
                }
            } catch (Exception e) {
                Log.i("Error", "The application failed - reason: AddRecord.");
            }
            return null; //Void requires return, (it's not void)
        }
    }

    public static class SearchRecord extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... search_parameters){

            String type = "usersLogin";
            String query =  String.format("{\"query\": {\"match\": {\"userName\": \"%s\"}}}", search_parameters[0]);
            try {
                JestResult result = client.execute(  new Search.Builder(query).addIndex(index).addType(type).build() );

                if (result.isSucceeded()){
                    List<SignUp> res = result.getSourceAsObjectList(SignUp.class);
                    if (res.size() != 0){
                        // res.get(0).getUserName();
                    }
                    else{Log.e("meh","nothing found.");}


                } else {
                    Log.e("Error","Some issues with query.");
                }
            } catch (Exception e){
                Log.i("Error","Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return null;
        }
    }
}


/*
{
  "fields": [
    "userID"
  ],
  "query": {
    "match_all": {}
  },
  "sort": {
    "userID": "desc"
  },
  "size": 1
}
*/