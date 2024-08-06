package tests.api;

import apiModel.CreateAndModifyUserResponse;
import apiModel.User;
import apiModel.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import services.PetStoreService;
import services.ValidationsService;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class PetStoreTests {
    String jsonFilePathCreateUser = "src/test/resources/dataForCreatingUser.json";
    String jsonFilePathModifyUser = "src/test/resources/dataToModifyUser.json";
    User userResponse;
    CreateAndModifyUserResponse createUserResponse;
    CreateAndModifyUserResponse modifyUserResponse;

    @BeforeTest
    public void beforeTest() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void createUserAndValidateByName() throws IOException, InterruptedException {
        UserData userData = setInputData();
        createUserResponse = PetStoreService.postUser(userData.getObjectMapper().writeValueAsString(userData.getNewUser()));
        ValidationsService.validateUserCreation(userData.getNewUser(), createUserResponse);

        userResponse = PetStoreService.getUser(userData.getNewUser().getUsername());
        ValidationsService.validateNewUserData(userData.getNewUser(), userResponse);
    }

    @Test
    public void updateUserAndValidateByName() throws IOException, InterruptedException {
        UserData userData = setInputData();
        createUserResponse = PetStoreService.postUser(userData.getObjectMapper().writeValueAsString(userData.getNewUser()));
        ValidationsService.validateUserCreation(userData.getNewUser(), createUserResponse);

        userResponse = PetStoreService.getUser(userData.getNewUser().getUsername());
        ValidationsService.validateNewUserData(userData.getNewUser(), userResponse);

        setUserWithNewData(userData.getNewUser());
        modifyUserResponse = PetStoreService.putUser(userData.getNewUser().getUsername(), userData.getObjectMapper().writeValueAsString(userData.getNewUser()));

        userResponse = PetStoreService.getUser(userData.getNewUser().getUsername());
        ValidationsService.validateNewUserData(userData.getNewUser(), userResponse);
    }

    public UserData setInputData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User newUser = objectMapper.readValue(new File(jsonFilePathCreateUser), User.class);
        newUser.setId(new Random().nextInt(900000) + 100000);
        newUser.setUsername(newUser.getUsername().concat(String.valueOf(newUser.getId())));
        return new UserData(newUser, objectMapper);
    }

    public void setUserWithNewData(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User myJson = objectMapper.readValue(new File(jsonFilePathModifyUser), User.class);
        user.setFirstName(myJson.getFirstName());
        user.setLastName(myJson.getLastName());
        user.setEmail(myJson.getEmail());
        user.setPassword(myJson.getPassword());
        user.setPhone(myJson.getPhone());
    }
}
