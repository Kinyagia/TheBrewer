package com.example.morris.thebrewer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by morris on 6/5/17.
 */

public class BrewerService {
    private static OkHttpClient client = new OkHttpClient();
    public static void findBreweries(String name, Callback callback) {

//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.API_KEY);
        urlBuilder.addQueryParameter(Constants.NAME_QUERY_PARAMETER, name);
        urlBuilder.addQueryParameter("format", "json");


        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


        public ArrayList<Brewery> processResults (Response response) {
            ArrayList<Brewery> breweries = new ArrayList<>();

            try {
                String jsonData = response.body().string();
                if (response.isSuccessful()) {
                    JSONObject beerJSON = new JSONObject(jsonData);
                    JSONArray dataJSON = beerJSON.getJSONArray("data");
                    for (int i = 0; i < dataJSON.length(); i++) {
                        JSONObject breweryJSON = dataJSON.getJSONObject(i);
                        String name = breweryJSON.optString("name");
                        String isOrganic = breweryJSON.optString("isOrganic", "Not Known");

                        Brewery brewery = new Brewery(name, isOrganic);
                        breweries.add(brewery);
                    }
                }



            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return breweries;
        }

    }

