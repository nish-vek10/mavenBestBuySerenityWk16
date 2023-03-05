package com.bestbuy.servicesInfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicesPojo;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ServicesCRUDTest {

    static String name = "Car Supplies";
    static Object servicesId;

    @Title("This will create a new service")
    @Test
    public void test001() {

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(servicesPojo)
                .when()
                .post(EndPoints.CREATE_NEW_SERVICES)
                .then().log().all().statusCode(201);
    }

    @Title("Verify if the service was created")
    @Test
    public void test002() {

        String part1 = "data.findAll{it.name = '";
        String part2 = "'}.get(0)";

        HashMap<String, ?> servicesMapData = SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(servicesMapData, hasValue(name));
        servicesId = servicesMapData.get("id");
        System.out.println("ID = " + servicesId);
    }

    @Title("Update the service and verify the updated information")
    @Test
    public void test003() {

        name = "Mercedes " + name;

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("servicesID", servicesId)
                .body(servicesPojo)
                .when()
                .patch(EndPoints.UPDATE_SERVICE_BY_ID)
                .then().log().all().statusCode(200);

        String part1 = "data.findAll{it.name = '";
        String part2 = "'}.get(0)";

        HashMap<String, ?> servicesMapData = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(servicesMapData, hasValue(name));
    }

    @Title("Delete the service")
    @Test
    public void test004() {

        SerenityRest.given()
                .pathParam("servicesID", servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICE_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Title("Verify if the service is deleted")
    @Test
    public void test005() {

        SerenityRest.given()
                .pathParam("servicesID", servicesId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then().log().all().statusCode(404);
    }
}
