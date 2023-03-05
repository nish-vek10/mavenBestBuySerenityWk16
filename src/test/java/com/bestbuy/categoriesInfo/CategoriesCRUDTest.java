package com.bestbuy.categoriesInfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.CategoriesPojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class CategoriesCRUDTest {

    static String name = "Car Supplies";
    static String id = "abcat" + TestUtils.getRandomValue();
    static Object categoriesId;

    @Title("This will create a new category")
    @Test
    public void test001() {

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(categoriesPojo)
                .when()
                .post(EndPoints.CREATE_NEW_CATEGORIES)
                .then().log().all().statusCode(201);
    }

    @Title("Verify if the product was created")
    @Test
    public void test002() {

        String part1 = "data.findAll{it.name = '";
        String part2 = "'}.get(0)";

        HashMap<String, ?> categoriesMapData = SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(categoriesMapData, hasValue(name));
        categoriesId = categoriesMapData.get("id");
        System.out.println("ID = " + categoriesId);
    }

    @Title("Update the product and verify the updated information")
    @Test
    public void test003() {

        name = "Mercedes " + name;

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("categoriesID", categoriesId)
                .body(categoriesPojo)
                .when()
                .patch(EndPoints.UPDATE_CATEGORY_BY_ID)
                .then().log().all().statusCode(200);

        String part1 = "data.findAll{it.name = '";
        String part2 = "'}.get(0)";

        HashMap<String, ?> categoriesMapData = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(categoriesMapData, hasValue(name));
    }

    @Title("Delete the product")
    @Test
    public void test004() {

        SerenityRest.given()
                .pathParam("categoriesID", categoriesId)
                .when()
                .delete(EndPoints.DELETE_CATEGORY_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Title("Verify if the product is deleted")
    @Test
    public void test005() {

        SerenityRest.given()
                .pathParam("categoriesID", categoriesId)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then().log().all().statusCode(404);
    }
}