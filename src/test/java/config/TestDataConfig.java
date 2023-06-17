package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:testdata.properties")
public interface TestDataConfig extends Config {
    @Key("userId")
    Integer userId();

    @Key("username")
    String userName();

    @Key("firstname")
    String firstName();

    @Key("newFirstName")
    String newFirstName();

    @Key("lastname")
    String lastName();

    @Key("email")
    String userEmail();

    @Key("password")
    String userPassword();

    @Key("phone")
    String userPhone();

    @Key("userStatus")
    Integer userStatus();

    @Key("nonexistuser")
    String nonExistUser();
}
