package com.hlee.scratch;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

public class UriTest {
	
    //	@Test
	public void construct_uri_parameter_jersey () {

	    UriBuilder builder = UriBuilder
	            .fromPath("www.leveluplunch.com")
	            .path("/{lanuage}/{type}/")
	            .queryParam("test", "a", "b");

	    URI uri = builder.build("java", "examples");

	    assertEquals(
	            "www.leveluplunch.com/java/examples/?test=a&test=b",
	            uri.toString());
	}
	
    //	@Test
	public void constructUriParameterJersey() {

	    UriBuilder builder = UriBuilder
	            .fromPath("https://www.leveluplunch.com/{language}/{type}")
	            //.path("/{language}/{type}")
	            .queryParam("test", "a", "b");
	    
	    URI uri = builder.build("java", "examples");

	    assertEquals("https://www.leveluplunch.com/java/examples?test=a&test=b", uri.toString());
	    System.out.println("*************************uri: " + uri.toString());
	}

    //	@Test
	public void constructUriParameterJersey2() {

	    UriBuilder builder = UriBuilder
	            .fromPath("http://loalhost:8080/cip-admin/api/pim/wholesalePrice/{skuId}/{countryCode}")
	            .queryParam("applicableDate", "date-here")
	            .queryParam("rewardNpEntitlementId", "entitlementId-001")
	            .queryParam("npCampaignId", "campaignId-123");	    
	    
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("skuId", "sku123");
        pathParams.put("countryCode", "US");
	    URI uri = builder.buildFromMap(pathParams);
	    
	    String expected = "http://loalhost:8080/cip-admin/api/pim/wholesalePrice/sku123/US?applicableDate=date-here&rewardNpEntitlementId=entitlementId-001&npCampaignId=campaignId-123";
	    assertEquals(expected, uri.toString());
	    System.out.println("*************************uri: " + uri.toString());
	}

    //	@Test
	public void constructUriParameterJersey3() {

	    UriBuilder builder = UriBuilder
	            .fromPath("http://loalhost:8080/cip-admin/api/pim/wholesalePrice/{skuId}/{countryCode}");
//	            .queryParam("applicableDate", "date-here")
//	            .queryParam("rewardNpEntitlementId", "entitlementId-001")
//	            .queryParam("npCampaignId", "campaignId-123");	    
	
	    
	    
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("skuId", "sku123");
        pathParams.put("countryCode", "US");
	    URI uri = builder.buildFromMap(pathParams);
	    
	    String expected = "http://loalhost:8080/cip-admin/api/pim/wholesalePrice/sku123/US?applicableDate=date-here&rewardNpEntitlementId=entitlementId-001&npCampaignId=campaignId-123";
	    assertEquals(expected, uri.toString());
	    System.out.println("*************************uri: " + uri.toString());

	}


}
