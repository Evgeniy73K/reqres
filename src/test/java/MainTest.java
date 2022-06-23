import api.CreateUserBody;
import api.TestClient;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MainTest {
    @Test
    public void listUsersTest() {
        TestClient testClient = new TestClient();
        testClient.checkAvatar()
                .checkEmail()
                .checkId()
                .checkFirstName()
                .checkLastName();
        testClient.getUsersList();
        int i = 0;
    }

    @Test
    public void singleUserTest(){
        TestClient testClient = new TestClient();
        testClient.checkIdUser()
                .checkAvatarUser()
                .checkEmailUser();

    }

    @Test
    public void createUserTest(){
        TestClient testClient = new TestClient();
        CreateUserBody createUserBody = new CreateUserBody("morpheus", "leader");
        testClient.createUser(createUserBody);

    }


}