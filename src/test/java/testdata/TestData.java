package testdata;

import config.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {
    private static final TestDataConfig config = ConfigFactory.create(TestDataConfig.class);

    public Integer userid = config.userId();
    public String username = config.userName();
    public String firstname = config.firstName();
    public String newFirstName = config.newFirstName();
    public String lastname = config.lastName();
    public String email = config.userEmail();
    public String password = config.userPassword();
    public String phone = config.userPhone();
    public Integer userStatus = config.userStatus();
    public String noneExistUser = config.nonExistUser();
}
