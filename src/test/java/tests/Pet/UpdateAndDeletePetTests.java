package tests.Pet;

import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.responseSpec;


public class UpdateAndDeletePetTests extends TestBase {
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
    @DisplayName("Delete pet by id")
    void deletePetTesr() {


        PetResponse response = given()
                .filter(withCustomTemplates())
                .spec(Specs.request)
                .when()
                .delete("/pet/" + petData.petId)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(PetResponse.class);

        assertEquals(response.getCode(), 200);

    }
}
