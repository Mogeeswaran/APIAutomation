package com.personal.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo1GetPetDetailsTest {

    public String baseUrl = "https://petstore.swagger.io/v2/";

    @Test
    public void findValidPetIdTest() throws JsonProcessingException {
        int petId = 5;
        String resource = "pet/" + petId;

        String response = RestAssured
                .given()
                .when().get(baseUrl + resource)
                .then().statusCode(HttpStatus.SC_OK).extract().asString();

        System.out.println(response);
//        Assert.assertTrue(response.contains(":5"),"Not matching id");
//        response.

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response);

        System.out.println(json.get("id"));
        System.out.println(json.get("name").asText());
        System.out.println(json.get("category").get("id"));
        System.out.println(json.get("tags").get(0).get("id").asInt());

    }
}
