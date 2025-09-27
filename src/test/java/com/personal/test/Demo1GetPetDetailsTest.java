package com.personal.test;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo1GetPetDetailsTest {

    public String baseUrl = "https://petstore.swagger.io/v2/";

    @Test
    public void findValidPetIdTest() {
        int petId = 5;
        String resource = "pet/" + petId;

        String response = RestAssured
                .given()
                .when().get(baseUrl + resource)
                .then().statusCode(HttpStatus.SC_OK).extract().asString();

        System.out.println(response);
//        Assert.assertTrue(response.contains(":5"),"Not matching id");
        response.
    }
}
