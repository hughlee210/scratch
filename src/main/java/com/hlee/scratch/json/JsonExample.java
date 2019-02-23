package com.hlee.scratch.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonExample {

    public static void main(String[] args) throws Exception {

        createJson();
    }

    /*
    {
        "wholesalePrice": {
          "rewardNpEntitlementId": "TEST01-OOPS02320_15-0000000009999991",
          "npCampaignId": "TESTID-2939",
          "npSkuId": "TESTID-2939",
          "applicableDate": "2017-08-11T00:01:05.891Z",
          "countryIsoCode": "US",
          "wsp": {
            "currency": "USD",
            "value": 2.23
          },
          "costCodes": ["p"]
        }
    }    
    */

    private static void createJson() throws JsonProcessingException {

        String entId = "TEST01-OOPS02320_15-0000000009999991";
        String campId = "CAMPID-2939";
        String skuId = "SKUID-2939";
        String applicableDate = "2017-08-11T00:01:05.891Z";
        String countryCode = "US";
        String currency = "USD";
        double wspVal = 2.23;

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode wholesalePriceNode = mapper.createObjectNode();
        wholesalePriceNode.put("rewardNpEntitlementId", entId);
        wholesalePriceNode.put("npCampaignId", campId);
        wholesalePriceNode.put("npSkuId", skuId);
        wholesalePriceNode.put("applicableDate", applicableDate);
        wholesalePriceNode.put("countryIsoCode", countryCode);

        ObjectNode wspNode = mapper.createObjectNode();
        wspNode.put("currency", currency);
        wspNode.put("value", wspVal);
        wholesalePriceNode.putPOJO("wsp", wspNode);

        ArrayNode arrayNode = mapper.createArrayNode();
        arrayNode.add("p");
        wholesalePriceNode.putPOJO("costCodes", arrayNode);

        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.putPOJO("wholesalePrice", wholesalePriceNode);

        System.out.println(rootNode);
        System.out.println("---------------------- pretty version ---------------------------------");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));


    }
}
