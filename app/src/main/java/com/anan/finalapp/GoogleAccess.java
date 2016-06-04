package com.anan.finalapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gtoumie on 04/06/2016.
 */
public class GoogleAccess {
    private static final String GOOGLE_PLACES_KEY="AIzaSyC3xaXsgTQ6_-AcWodxYrqP3kbf0NxEOTM";
    private static final String GOOGLE_PLACES_HOST="https://maps.googleapis.com/maps/api/place/textsearch/json?query=[QUERY]&sensor=true&key="+GOOGLE_PLACES_KEY;

    public static String searchPlace(String q){

        BufferedReader input = null;
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();
        try {
            // replace THE_ADDRESS_YOU_WANT with the url you want to comsume.
            URL url = new URL(GOOGLE_PLACES_HOST);

            connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                return null;
            }

            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line="";
            while ((line=input.readLine())!=null){
                response.append(line+"\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally{
            if (input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                connection.disconnect();
            }
        }
        return response.toString();

    }
}
