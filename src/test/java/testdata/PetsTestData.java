package testdata;

import config.PetTestDataConfig;
import org.aeonbits.owner.ConfigFactory;

public class PetsTestData {
    private static final PetTestDataConfig config = ConfigFactory.create(PetTestDataConfig.class);

    public Integer categoryId = config.categoryId();
    public String categoryName = config.categoryName();
    public Integer tagId = config.tagId();
    public String tagName = config.tagName();
    public String petName = config.petName();
    public String photoUrl = config.photoUrl();
    public String petStatus = config.petStatus();
    public Integer petId = config.petId();
    public Integer statusId = config.statusId();
    public String newPetName = config.newPetName();

}


