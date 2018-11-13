package com.example.symptologger;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class ElasticSearchConcernController {
    private static JestDroidClient client;

    public static class AddConcernsTask extends AsyncTask<Concern, Void, Void> {
        @Override
        protected Void doInBackground(Concern... concerns){
            verifySettings();

            for (Concern concern : concerns){
                Index index = new Index.Builder(concern).index("testing").type("concern").build();

                try{
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()){
                        concern.setId(result.getId());
                    } else {
                        Log.e("Error","ElasticSearch was not able to add the concern.");
                    }
                } catch (Exception e) {
                    Log.i("Error","The application failed to build and send the concerns.");
                }
            }
            return null;
        }

    }

    public static class GetConcernsTask extends AsyncTask<String, Void, ArrayList<Concern>>{
        @Override
        protected ArrayList<Concern> doInBackground(String... search_parameters){
            verifySettings();

            ArrayList<Concern> concerns = new ArrayList<Concern>();

            Search search = new Search.Builder(search_parameters[0])
                                                .addIndex("Testing")
                                                .addType("concern").build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    List<Concern> foundConcerns = result.getSourceAsObjectList(Concern.class);
                    concerns.addAll(foundConcerns);
                } else {
                    Log.e("Error","The search query failed to find any concerns.");
                }
            } catch (Exception e){
                Log.i("Error","Something went wrong when we tried to communicate with the elasticsearch server.");
            }
            return concerns;
        }
    }

    public static void verifySettings() {
        if (client == null){
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/cmput301f18t02test");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
