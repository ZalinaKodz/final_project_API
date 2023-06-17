package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:testdata.properties")

public interface PetTestDataConfig extends Config {
    @Key("categoryId")
    Integer categoryId();

    @Key("categoryName")
    String categoryName();

    @Key("tagId")
    Integer tagId();

    @Key("tagName")
    String tagName();

    @Key("petName")
    String petName();

    @Key("photoUrl")
    String photoUrl();

    @Key("petStatus")
    String petStatus();

    @Key("petId")
    Integer petId();

    @Key("statusId")
    Integer statusId();

    @Key("newPetName")
    String newPetName();

}
