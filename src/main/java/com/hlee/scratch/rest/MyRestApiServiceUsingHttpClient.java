package com.hlee.scratch.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MyRestApiServiceUsingHttpClient {

    public static void main(String[] args) throws Exception {
        // refer to https://www.baeldung.com/java-9-http-client

        String urlString = "https://images-api.nasa.gov/search?q=clouds";
        MyRestApiServiceUsingHttpClient myService = new MyRestApiServiceUsingHttpClient();
        String result = myService.extractData(urlString);
        System.out.println("result: " + result);
        System.out.println("------------------------------------------");

        postmanEcho();

    }

    public static void postmanEcho() throws IOException, InterruptedException {
        System.out.println("***Sending GET request to postman");
        String urlString = "https://postman-echo.com/get?param1=value1&param2=value2";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();
        HttpResponse response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("response body: " + response.body());
        System.out.println("response status code: " + response.statusCode());

        if (response.statusCode() != 200) {
            throw new RuntimeException("treating as error with response code: " + response.statusCode());
        }
    }

    public String extractData(String urlString) {
        System.out.println("Connecting to " + urlString);

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(urlString))
                    .GET()
                    .build();

            HttpResponse response = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("response body: " + response.body());
            System.out.println("response status code: " + response.statusCode());

            if (response.statusCode() != 200) {
                throw new RuntimeException("treating as error with response code: " + response.statusCode());
            }

            JsonParser parser = new JsonParser();
            JsonElement root = parser.parse(response.body().toString());
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

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
