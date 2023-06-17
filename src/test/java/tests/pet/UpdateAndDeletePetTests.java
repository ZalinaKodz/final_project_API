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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.responseSpec;
import static steps.PetSteps.updatePetName;

@Epic("Pet store")
@Feature("Pet")
public class UpdateAndDeletePetTests extends TestBase {

    PetsTestData petsData = new PetsTestData();

    @Test
    @DisplayName("Update pet`s information")
    void updatePet() {
        PetModel pet = updatePetName(petsData.categoryId, petsData.categoryName, petsData.tagId, petsData.tagName, petsData.petId, petsData.newPetName, petsData.photoUrl, petsData.petStatus);

        PetDataResponse response = given()
                .spec(Specs.request)
                .body(pet)
                .when()
                .put("/pet")
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(PetDataResponse.class);

        step("Check that category is 'dog'", () ->
                assertEquals(petsData.categoryName, response.getCategory().getName()));
        step("Check that pet name is 'Charlie'", () ->
                assertEquals(petsData.newPetName, response.getName()));
        step("Check that tag is 'dog'", () ->
                assertEquals(petsData.tagName, response.getTags().get(0).getName()));
        step("Check that status is 'available'", () ->
                assertEquals(petsData.petStatus, response.getStatus()));

    }

    @Test
    @DisplayName("Delete pet by id")
    void deletePetTest() {

        PetResponse response = given()
                .spec(Specs.request)
                .when()
                .delete("/pet/" + petsData.petId)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(PetResponse.class);

        step("Check that status code is '200'", () ->
                assertEquals(200, response.getCode()));

    }
}
