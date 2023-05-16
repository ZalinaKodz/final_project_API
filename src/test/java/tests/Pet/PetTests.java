package tests.Pet;

import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.responseSpec;

public class PetTests extends TestBase {

    PetsTestData petData = new PetsTestData();

    @Test
    @DisplayName("Create pet")
    void сheckCreatePet() {

        Category category = new Category();
        category.setId(petData.categoryId);
        category.setName(petData.categoryName);

        TagsItem tag = new TagsItem();
        tag.setId(petData.tagId);
        tag.setName(petData.tagName);
        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag);

        PetModel pet = new PetModel();
        pet.setCategory(category);
        pet.setName(petData.petName);
        pet.setPhotoUrls(Arrays.asList(petData.photoUrl));
        pet.setTags(tags);
        pet.setStatus(petData.petStatus);

        PetDataResponse response = given()
                .filter(withCustomTemplates())
                .spec(Specs.request)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(PetDataResponse.class);

        assertEquals(petData.categoryName, response.getCategory().getName());
        assertEquals(petData.petName, response.getName());
        assertEquals(petData.tagName, response.getTags().get(0).getName());
        assertEquals(petData.petStatus, response.getStatus());

    }

    @Test
    @DisplayName("Update pet`s information")
    void updatePet() {

        Category category = new Category();
        category.setId(petData.categoryId);
        category.setName(petData.categoryName);

        TagsItem tag = new TagsItem();
        tag.setId(petData.tagId);
        tag.setName(petData.tagName);
        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag);

        PetModel pet = new PetModel();
        pet.setId(petData.petId);
        pet.setCategory(category);
        pet.setName(petData.petName);
        pet.setPhotoUrls(Arrays.asList(petData.photoUrl));
        pet.setTags(tags);
        pet.setStatus(petData.petStatus);

        PetDataResponse response = given()
                .filter(withCustomTemplates())
                .spec(Specs.request)
                .body(pet)
                .when()
                .put("/pet")
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(PetDataResponse.class);

        assertEquals(petData.categoryName, response.getCategory().getName());
        assertEquals(petData.petName, response.getName());
        assertEquals(petData.tagName, response.getTags().get(0).getName());
        assertEquals(petData.petStatus, response.getStatus());

    }

    @Test
    @DisplayName("Get pet by id")
    void getPetById() {


        Category category = new Category();
        category.setId(petData.categoryId);
        category.setName(petData.categoryName);

        TagsItem tag = new TagsItem();
        tag.setId(petData.tagId);
        tag.setName(petData.tagName);
        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag);

        PetModel pet = new PetModel();
        pet.setCategory(category);
        pet.setName(petData.petName);
        pet.setPhotoUrls(Arrays.asList(petData.photoUrl));
        pet.setTags(tags);
        pet.setStatus(petData.petStatus);

        PetDataResponse response = given()
                .spec(Specs.request)
                .when()
                .get("/pet/" + petData.petId)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(PetDataResponse.class);

        assertEquals(petData.categoryName, response.getCategory().getName());
        assertEquals(petData.petName, response.getName());
        assertEquals(petData.tagName, response.getTags().get(0).getName());
        assertEquals(petData.petStatus, response.getStatus());

    }

    @Test
    @DisplayName("Get pet by status")
    void getPetByStatus() {

        given()
                .filter(withCustomTemplates())
                .spec(Specs.request)
                .when()
                .get("pet/findByStatus?status=" + petData.petStatus)
                .then()
                .spec(responseSpec)
                .body("findAll{it.id =~/./}.id.flatten()",
                        hasItem(922337563));

    }
}
