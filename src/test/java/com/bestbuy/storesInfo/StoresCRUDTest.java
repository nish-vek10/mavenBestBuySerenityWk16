package com.bestbuy.storesInfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StoresPojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class StoresCRUDTest {

    static String name = "Lucas Morata";
    static String type = "Biggy";
    static String city = "New Jersey";
    static Object storesId;

    @Title("This will create a new store")
    @Test
    public void test001() {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress("41 Calver Road");
        storesPojo.setAddress2("");
        storesPojo.setCity("New York");
        storesPojo.setState("NY");
        storesPojo.setZip(TestUtils.getRandomValue());
        storesPojo.setLat(44);
        storesPojo.setLng(55);
        storesPojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9");

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(storesPojo)
                .when()
                .post(EndPoints.CREATE_NEW_STORES)
                .then().log().all().statusCode(201);
    }

    @Title("Verify if the store was created")
    @Test
    public void test002() {

        String part1 = "data.findAll{it.name = '";
        String part2 = "'}.get(0)";

        HashMap<String, ?> storesMapData = SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(storesMapData, hasValue(name));
        storesId = storesMapData.get("id");
        System.out.println("ID = " + storesId);
    }

    @Title("Update the store and verify the updated information")
    @Test
    public void test003() {

        name = "Mr. " + name;

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("storesID", storesId)
                .body(storesPojo)
                .when()
                .patch(EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all().statusCode(200);

        String part1 = "data.findAll{it.name = '";
        String part2 = "'}.get(0)";

        HashMap<String, ?> storesMapData = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(storesMapData, hasValue(name));
    }

    @Title("Delete the store")
    @Test
    public void test004() {

        SerenityRest.given()
                .pathParam("storesID", storesId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Title("Verify if the store is deleted")
    @Test
    public void test005() {

        SerenityRest.given()
                .pathParam("storesID", storesId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().log().all().statusCode(404);
    }
}