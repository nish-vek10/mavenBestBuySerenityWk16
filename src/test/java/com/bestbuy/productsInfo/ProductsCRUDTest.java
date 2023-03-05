package com.bestbuy.productsInfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductsPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {

    static String name = "Duracell - AA Batteries (8-Pack) - NEWLY CREATED";
    static String type = "HardGood";
    static double price = 6.99;
    static String manufacturer = "Duracell";
    static Object productsId;

    @Title("This will create a new product")
    @Test
    public void test001() {

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setUpc("005577" + TestUtils.getRandomValue());
        productsPojo.setShipping(2);
        productsPojo.setDescription("Compatible with select electronic devices; AA size; 8-pack");
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel("DC" + TestUtils.getRandomValue());
        productsPojo.setUrl("http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/" + TestUtils.getRandomValue());
        productsPojo.setImage("http://img.bbystatic.com/BestBuy_US/images/products/" + TestUtils.getRandomValue()
                + ".jpg");

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(productsPojo)
                .when()
                .post(EndPoints.CREATE_NEW_PRODUCT)
                .then().log().all().statusCode(201);
    }

    @Title("Verify if the product was created")
    @Test
    public void test002() {

        String part1 = "data.findAll{it.name = '";
        String part2 = "'}.get(0)";

        HashMap<String, ?> productsMapData = SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(productsMapData, hasValue(name));
        productsId = productsMapData.get("id");
        System.out.println("ID = " + productsId); // 43900
    }

    @Title("Update the product and verify the updated information")
    @Test
    public void test003() {
//        System.out.println("ID = " + productsId);
        name = "HardShell and Long Lasting " + name;
        price = price + 2;

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setPrice(price);

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("productsID", productsId)
                .body(productsPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all().statusCode(200);

        String part1 = "data.findAll{it.name = '";
        String part2 = "'}.get(0)";

        HashMap<String, ?> productsMapData = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(productsMapData, hasValue(name));
    }

    @Title("Delete the product")
    @Test
    public void test004() {

        SerenityRest.given()
                .pathParam("productsID", productsId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Title("Verify if the product is deleted")
    @Test
    public void test005() {

        SerenityRest.given()
                .pathParam("productsID", productsId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all().statusCode(404);
    }
}
