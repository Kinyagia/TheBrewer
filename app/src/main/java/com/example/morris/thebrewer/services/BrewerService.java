package com.example.morris.thebrewer.services;

import com.example.morris.thebrewer.Constants;
import com.example.morris.thebrewer.models.Brewery;

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
 public static ArrayList<Brewery> processResults(Response response) {
     ArrayList<Brewery> breweries = new ArrayList<>();

     try {
         String jsonData = response.body().string();
         if (response.isSuccessful()) {
             JSONObject snoothJSON = new JSONObject(jsonData);
             JSONArray winesJSON = snoothJSON.getJSONArray("wines");
             for (int i =0; i < winesJSON.length(); i++) {
                 JSONObject breweryJSON = winesJSON.getJSONObject(i);
                 String name = breweryJSON.getString("name");
                 String winery = breweryJSON.getString("winery");
                 String varietal = breweryJSON.getString("varietal");
                 String price = breweryJSON.getString("price");
                 String vintage = breweryJSON.getString("vintage");
                 String type = breweryJSON.getString("type");
                 String link = breweryJSON.getString("link");
                 String image = breweryJSON.getString("image");

                 Brewery brewery = new Brewery(name, winery, varietal, price, vintage, type, link, image);
                 breweries.add(brewery);

             }
         }
     } catch (JSONException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }
     return breweries;
 }

}