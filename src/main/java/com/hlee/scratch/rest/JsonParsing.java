package com.hlee.scratch.rest;

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class JsonParsing {

    public static void main(String[] args) throws FileNotFoundException {

        parseJsonAndSort();
        System.out.println("-----------------------------------------");

        parseJsonToMap();
        System.out.println("-----------------------------------------");
    }

    static void parseJsonAndSort() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(new FileReader("/Users/hlee1/projects-fun/scratch/src/main/resources/sample1.json"));
        JsonObject root = jsonElement.getAsJsonObject();
        JsonArray results = root.getAsJsonArray("results");
        List<JsonObject> list = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            JsonObject jsonObject = results.get(i).getAsJsonObject();
            list.add(jsonObject);
        }

        Collections.sort(list, new Comparator<JsonObject>() {
//            @Override
//            public int compare(JsonObject o1, JsonObject o2) {
//                String v1 = o1.getAsJsonObject("attributes").getAsJsonPrimitive("COMMERCIALNAME_E").getAsString();
//                String v2 = o2.getAsJsonObject("attributes").getAsJsonPrimitive("COMMERCIALNAME_E").getAsString();
//                return v1.compareTo(v2);
//            }

            @Override
            public int compare(JsonObject o1, JsonObject o2) {
                Integer v1 = o1.getAsJsonObject("attributes").getAsJsonPrimitive("OBJECTID").getAsInt();
                Integer v2 = o2.getAsJsonObject("attributes").getAsJsonPrimitive("OBJECTID").getAsInt();
                return v1.compareTo(v2);
            }
        });

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        System.out.println("list:\n" + json);
    }

    static void parseJsonToMap() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(new FileReader("/Users/hlee1/projects-fun/scratch/src/main/resources/sample1.json"));
        System.out.println(jsonElement.toString());

        Map map = new Gson().fromJson(jsonElement.toString(), Map.class);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(map);
        System.out.println("map:\n" + json);

    }


}
