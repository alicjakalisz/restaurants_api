package com.alicja.restaurants.json_converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alicja.restaurants.exception.SearchException;;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonConverter {

    /**
     * Connects to URL, read the content into String and transforms String into Json Object
     * @param URL
     * @return
     * @throws IOException
     * @throws SearchException
     */
    public JsonObject convertStringURLIntoJsonObject(String URL) throws IOException, SearchException {
        URL url = new URL(URL);
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        if(conn.getResponseCode()!=200){
            throw new SearchException("No results for search of these parameters");
        }
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(result.toString()).getAsJsonObject();
        return jsonObject;
    }


}
