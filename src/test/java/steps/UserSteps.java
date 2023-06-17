package steps;

import io.qameta.allure.Step;
import models.UserData;

public class UserSteps {
    @Step("Create user")
    public static UserData createUsers(Integer userId, String userName, String firstName, String lastName, String email, String password, String phone, Integer userStatus) {
        UserData user = new UserData();
        user.setId(userId);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(userStatus);

        return user;
    }

    @Step("Update user`s name")
    public static UserData updateUser(Integer userId, String userName, String newFirstName, String lastName, String email, String password, String phone, Integer userStatus) {
        UserData user = new UserData();
        user.setId(userId);
        user.setUsername(userName);
        user.setFirstName(newFirstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(userStatus);

        return user;
    }
}
