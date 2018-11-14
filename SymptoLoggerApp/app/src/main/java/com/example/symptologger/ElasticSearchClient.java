package com.example.symptologger;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

import java.util.ArrayList;
import java.util.List;

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
    private static final String server = "http://cmput301.softwareprocess.es:8080/cmput301f18t02";

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

    //AsyncTask enables proper and easy use of the UI thread. This class allows you to perform
    //background operations and publish results on the UI thread without having to manipulate threads and/or handlers.

    public static class DeleteIndices extends AsyncTask<String, Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String... indices) {
            for (String index : indices) {
                try {
                    JestResult result = client.execute(new DeleteIndex.Builder(index).build());
                    if (result.isSucceeded()) {
                    } else {
                        Log.e("Error", "ElasticSearch was not able to delete some index.");
                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and delete index.");
                }
            }
            return null; //Void requires return, (it's not void)
        }
    }

    public static class AddIndices extends AsyncTask<String, Void, Void> { //use Void instead of void for AsyncTask as return type
        @Override
        protected Void doInBackground(String... indices) {
                try {
                    //JestResult result = client.execute(new CreateIndex.Builder("cmput301f18t02").build());
                    JestResult result = client.execute(new Index.Builder("{ \"user\" : { \"properties\" : { \"message\" : {\"type\" : \"string\", \"store\" : \"yes\"} } } }").index("cmput301f18t02").type("user").id("100").build());
                    //JestResult result = client.execute(new PutMapping.Builder(
                    //        "cmput301f18t02",
                    //        "user",
                    //        "{ \"user\" : { \"properties\" : { \"message\" : {\"type\" : \"string\", \"store\" : \"yes\"} } } }"
                    //).build());

                    if (result.isSucceeded()) {

                    } else {
                        //Log.e("Error", "ElasticSearch was not able to delete some index.");

                    }
                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and delete index.");
                }
            return null; //Void requires return, (it's not void)
        }
    }

}


//    public static class AddConcernsTask extends AsyncTask<String, Void, Void> { //use Void instead of void for AsyncTask as return type
//        @Override
//        protected Void doInBackground(String... strings) {
//


            //for (String string : strings) {
                //Index index = new Index.Builder(string).index("testing").type("concern").build();
//                PutMapping putMapping = new PutMapping.Builder(
//                        "testing",
//                        "my_type",
//                        "{ \"my_type\" : { \"properties\" : { \"message\" : {\"type\" : \"string\", \"store\" : \"yes\"} } } }"
//                ).build();
//                try {
//                    JestResult result = client.execute( new DeleteIndex.Builder("cmput301f18t02").build() );
//                    System.out.println("YOOOOO");
//                    if (result.isSucceeded()) {
//                        //concern.setId(result.getId());
//                        System.out.println(result);
//                    } else {
//                        System.out.println(result);
//                        Log.e("Error", "ElasticSearch was not able to add the concern.");
//                    }
//                } catch (Exception e) {
//                    Log.i("Error", "The application failed to build and send the concerns.");
//                }
//            //}
//            return null;
//        }
//
//    }
//}

//    public static class GetConcernsTask extends AsyncTask<String, Void, ArrayList<Concern>>{
//        @Override
//        protected ArrayList<Concern> doInBackground(String... search_parameters){
//            initClient();
//
//            ArrayList<Concern> concerns = new ArrayList<Concern>();
//
//            Search search = new Search.Builder(search_parameters[0])
//                    .addIndex("Testing")
//                    .addType("concern").build();
//
//            try {
//                SearchResult result = client.execute(search);
//                if (result.isSucceeded()){
//                    List<Concern> foundConcerns = result.getSourceAsObjectList(Concern.class);
//                    concerns.addAll(foundConcerns);
//                } else {
//                    Log.e("Error","The search query failed to find any concerns.");
//                }
//            } catch (Exception e){
//                Log.i("Error","Something went wrong when we tried to communicate with the elasticsearch server.");
//            }
//            return concerns;
//        }
//    }



//
//    String query = "{\"query\": {\"match\": {\"ingredients\": \"salmon\"}}}";
//    Search search = new Search.Builder(query)
//            .addIndex("recipes")
//            .addType("recipe")
//            .build();
//try {
//        SearchResult result = client.execute(search);
//        Log.d(TAG, result.getJsonObject().toString());
//        } catch (IOException e) {
//        e.printStackTrace();
//        }