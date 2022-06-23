package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
public class TestClient {

    private String baseUri;
    private String basePath;

    public TestClient() {
        this(TestConfig.Uri.value, TestConfig.Path.value);
    }

    private RequestSpecification getRequestSpec() {
        return given().
                baseUri(baseUri).
                basePath(basePath).
                contentType(ContentType.JSON).
                log().all();
    }

    private RequestSpecification getRequestSpec(Object body) {
        return getRequestSpec().
                body(body);
    }

    public List<UserDataResponse> getUsersList() {
        Response response = getRequestSpec().when().
                get("users?page=2");
        return response.then().statusCode(200).log().all()
                .extract()
                .body()
                .jsonPath().getList("data", UserDataResponse.class);
    }

    public UserDataResponse getUserList() {
        Response response = getRequestSpec().when().
                get("users/2");
        return response.then().statusCode(200).log().all()
                .extract()
                .body()
                .jsonPath().getObject("data", UserDataResponse.class);
    }

    public CreateUserResponse createUser(CreateUserBody body) {
        Response response = getRequestSpec().when().body(body).
                post("users/");
        return response.then().statusCode(201).log().all()
                .extract()
                .body()
                .jsonPath().getObject("data", CreateUserResponse.class);
    }



    public TestClient checkAvatar(){
        getUsersList().stream().forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        return this;
    }

    public TestClient checkEmail(){
        getUsersList().stream().forEach(x -> Assert.assertTrue(x.getEmail().endsWith("reqres.in")));
        return this;
    }

    public TestClient checkId(){
        List<String> ids = getUsersList().stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < ids.size(); i++) {
            Assert.assertFalse(ids.get(i).isEmpty());

    }
        return this;
    }

    public TestClient checkFirstName(){
        List<String> names = getUsersList().stream().map(x -> x.getFirst_name().toString()).collect(Collectors.toList());
        for (int i = 0; i < names.size(); i++) {
            Assert.assertFalse(names.get(i).isEmpty());

        }
        return this;
    }

    public TestClient checkLastName(){
        List<String> names = getUsersList().stream().map(x -> x.getLast_name().toString()).collect(Collectors.toList());
        for (int i = 0; i < names.size(); i++) {
            Assert.assertFalse(names.get(i).isEmpty());

        }
        return this;
    }

    public TestClient checkAvatarUser(){
        getUserList().getAvatar().contains(getUserList().getId().toString());
        return this;
    }

        public TestClient checkEmailUser(){
            getUserList().getEmail().endsWith("reqres.in");
            return this;
        }

        public TestClient checkIdUser(){
            Assert.assertFalse(getUserList().getId().describeConstable().isEmpty());
            return this;
        }





}