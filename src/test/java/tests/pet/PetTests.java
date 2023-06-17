package tests.pet;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;
import testdata.PetsTestData;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.responseSpec;
import static steps.PetSteps.createPet;


@Epic("Pet store")
@Feature("Pet")
public class PetTests extends TestBase {

    PetsTestData petsData = new PetsTestData();

    @Test
    @DisplayName("Create pet")
    void сheckCreatePet() {

        PetModel pet = createPet(petsData.categoryId, petsData.categoryName, petsData.tagId, petsData.tagName, petsData.petName, petsData.photoUrl, petsData.petStatus);

        PetDataResponse response = given()
                .spec(Specs.request)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(PetDataResponse.class);

        step("Check that category is 'dog'", () ->
                assertEquals(petsData.categoryName, response.getCategory().getName()));
        step("Check that pet name is 'SweetPie'", () ->
                assertEquals(petsData.petName, response.getName()));
        step("Check that tag is 'dog'", () ->
                assertEquals(petsData.tagName, response.getTags().get(0).getName()));
        step("Check that status is 'available'", () ->
                assertEquals(petsData.petStatus, response.getStatus()));

    }


    @Test
    @DisplayName("Get pet by id")
    void getPetById() {

        PetDataResponse response = given()
                .spec(Specs.request)
                .when()
                .get("/pet/" + petsData.petId)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(PetDataResponse.class);

        step("Check that category is 'dog'", () ->
                assertEquals(petsData.categoryName, response.getCategory().getName()));
        step("Check that pet`s name is 'SweetPie'", () ->
                assertEquals(petsData.petName, response.getName()));
        step("Check that tag is 'dog'", () ->
                assertEquals(petsData.tagName, response.getTags().get(0).getName()));
        step("Check that status is 'available'", () ->
                assertEquals(petsData.petStatus, response.getStatus()));

    }

    @Test
    @DisplayName("Get pet by status")
    void getPetByStatus() {

        given()
                .spec(Specs.request)
                .when()
                .get("pet/findByStatus?status=" + petsData.petStatus)
                .then()
                .spec(responseSpec)
                .body("findAll{it.id =~/./}.id.flatten()",
                        hasItem(petsData.statusId));
    }
}
