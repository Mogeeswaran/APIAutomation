package com.personal.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.personal.model.Category;
import com.personal.model.Pet;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.transform.Source;

/**
 * Deserializtion
 */

public class Demo2GetPetById {

    public String baseUrl = "https://petstore.swagger.io/v2/";

    /**
     * Tree like structure - flexible structure - avoid if planning for schema validation
     */
    @Test
    public void demo1GetPetByIdJsonNode(){
        int petId=5;
        String resource="pet/"+petId;

        JsonNode responseJson = RestAssured
                .given()
                .when().get(baseUrl + resource)
                .then().statusCode(HttpStatus.SC_OK).extract().as(JsonNode.class);

        System.out.println(responseJson.get("id"));
        System.out.println(responseJson.get("name").asText());
        System.out.println(responseJson.get("category").get("id"));
        System.out.println(responseJson.get("tags").get(0).get("id").asInt());

        System.out.println(responseJson.get("photoUrls").get(0));

        Assert.assertEquals(responseJson.get("id").asInt(),5);
    }

    /**
     * only advatage is json expression. (Not type safety, auto mapping nested objects, not easy to maintain, not suitable for api automation)
     */
    @Test
    public void demo1GetPetByIdJsonPath(){
        int petId=5;
        String resource="pet/"+petId;

        JsonPath jsonPathObj = RestAssured
                .given()
                .when().get(baseUrl+resource)
                .then().statusCode(HttpStatus.SC_OK).extract().jsonPath();

        System.out.println(jsonPathObj.prettify());
        System.out.println(jsonPathObj.getInt("id"));
        System.out.println(jsonPathObj.getString("name"));
        System.out.println(jsonPathObj.getString("category.id"));
        System.out.println(jsonPathObj.getString("tags[0].id"));
//        System.out.println(jsonPathObj.getList("tags[*].id"));

    }

    /**
     * Pojo class - type safety, easy maintenance, stable for automation, schema validation
     */

    @Test
    public void demo3GetPetByIdPojoclass(){

        Pet petObj = RestAssured
                .given()
                .pathParam("petId", 5)
                .when().get(baseUrl + "pet/{petId}")
                .then().statusCode(HttpStatus.SC_OK).extract().as(Pet.class);

        System.out.println(petObj.getId());
        System.out.println(petObj.getCategory().getId());
        System.out.println(petObj.getTags().get(0).getId());


    }
}



























