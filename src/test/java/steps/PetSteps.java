package steps;

import io.qameta.allure.Step;
import models.Category;
import models.PetModel;
import models.TagsItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetSteps {

    @Step("Create pet")
    public static PetModel createPet(Integer categoryId, String categoryName, Integer tagId, String tagName, String petName, String photoUrl, String petStatus) {
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        TagsItem tag = new TagsItem();
        tag.setId(tagId);
        tag.setName(tagName);
        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag);

        PetModel pet = new PetModel();
        pet.setCategory(category);
        pet.setName(petName);
        pet.setPhotoUrls(Arrays.asList(photoUrl));
        pet.setTags(tags);
        pet.setStatus(petStatus);

        return pet;
    }


    @Step("Update pet`s name")
    public static PetModel updatePetName(Integer categoryId, String categoryName, Integer tagId, String tagName, Integer petId, String newPetName, String photoUrl, String petStatus) {
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        TagsItem tag = new TagsItem();
        tag.setId(tagId);
        tag.setName(tagName);
        List<TagsItem> tags = new ArrayList<>();
        tags.add(tag);

        PetModel pet = new PetModel();
        pet.setId(petId);
        pet.setCategory(category);
        pet.setName(newPetName);
        pet.setPhotoUrls(Arrays.asList(photoUrl));
        pet.setTags(tags);
        pet.setStatus(petStatus);

        return pet;
    }

}
