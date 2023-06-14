package com.hlee.scratch.rest;

import com.google.gson.*;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyRestApiService {

    public static void main(String[] args) {

        String urlString = "https://images-api.nasa.gov/search?q=clouds";

        MyRestApiService myService = new MyRestApiService();
        String result = myService.extractData(urlString);
        System.out.println("result: " + result);
        System.out.println("------------------------------------------");

//        String[] lang = {"Java", "Node", "Kotlin", "JavaScript"};
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String json = gson.toJson(lang);
//        System.out.println("lang: " + json);

        myService.sendPostRequest();

    }

    public String extractData(String urlString) {
        try {
            System.out.println("Connecting to " + urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // If we want to add parameters to a request, we have to set the doOutput property to true,
            // then write a String of the form param1=value¶m2=value to the OutputStream of the HttpUrlConnection instance:
            // SEE [4. Adding Request Parameters] https://www.baeldung.com/java-http-request

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("treating as error with response code: " + responseCode);
            }

            // using gson json parser
            JsonParser parser = new JsonParser();
            JsonElement root = parser.parse(new InputStreamReader(conn.getInputStream()));
            JsonObject jsonObject = root.getAsJsonObject();

            // extract number of items and total_hits

            JsonObject collectionObj = jsonObject.get("collection").getAsJsonObject();
            JsonArray items = collectionObj.get("items").getAsJsonArray();
            int itemSize = items.size();

            int totalHits = collectionObj.get("metadata")
                    .getAsJsonObject()
                    .get("total_hits")
                    .getAsInt();

            /*
            {
                "itemSize": 23,
                "totalHits": 32
            }
             */

            String result = "{" +
                    "\"itemSize\": " + itemSize + "," +
                    "\"totalHits\": " + totalHits +
                    "}";
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPostRequest() {
        try {
            String urlString = "https://reqres.in/api/users";
            System.out.println("Sending POST request to " + urlString);
            URL url = new URL(urlString); // REST api testing website: https://reqres.in/
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // Set the Request Content-Type Header Parameter to send the request body in JSON format
            conn.setRequestProperty("Content-Type", "application/json");
            // set response format type to read the response in the desired format
            conn.setRequestProperty("Accept", "application/json");
            // to send request content to connection output stream, set doOutput property to true
            conn.setDoOutput(true);

            // create a JSON string and write to connection output stream
            String jsonString = "{\"name\": \"Heonkoo\", \"job\": \"Software Engineer\"}";
            try (OutputStream os = conn.getOutputStream()) {
                byte[] content = jsonString.getBytes("utf-8");
                os.write(content, 0, content.length);
            }

            // read the response from input stream
//            try (BufferedReader br = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String line;
//                while ((line = br.readLine()) != null) {
//                    response.append(line.trim());
//                }
//                System.out.println(response.toString());
//            }

            // if response is json, we can leverage json parser
            JsonParser parser = new JsonParser();
            JsonElement root = parser.parse(new InputStreamReader(conn.getInputStream()));
            System.out.println("response:\n" + root);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(root);
            System.out.println("response pretty printing:\n" + json);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
