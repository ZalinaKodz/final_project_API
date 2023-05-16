package tests.User;

import models.UserData;
import models.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.Specs.responseSpec;

public class PetStoreUserChangeTests extends TestBase {

    @Test
    @DisplayName("Update user")
    void checkUpdateUser() {

        UserData user = new UserData();
        user.setId(testData.id);
        user.setUsername(testData.username);
        user.setFirstName(testData.firstname);
        user.setLastName(testData.lastname);
        user.setEmail(testData.email);
        user.setPassword(testData.password);
        user.setPhone(testData.phone);
        user.setUserStatus(testData.userStatus);

        UserResponse response = given()
                .filter(withCustomTemplates())
                .spec(Specs.request)
                .body(user)
                .when()
                .put("/user/" + testData.username)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(UserResponse.class);

        assertEquals(response.getCode(), 200);
        assertNotNull(response.getType());
        assertNotNull(response.getMessage());

    }

    @Test
    void deletePetTest() {

        UserResponse response = given()
                .filter(withCustomTemplates())
                .spec(Specs.request)
                .when()
                .delete("/user/" + testData.username)
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .as(UserResponse.class);

        assertEquals(response.getCode(), 200);
    }
}
