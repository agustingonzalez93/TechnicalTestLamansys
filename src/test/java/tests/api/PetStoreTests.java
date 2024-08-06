package tests.api;

import apiModel.CreateAndModifyUserResponse;
import apiModel.User;
import apiModel.UserData;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import services.PetStoreService;
import services.ValidationsService;
import utilities.ServiceHelper;

import java.io.IOException;


public class PetStoreTests {
    User userResponse;
    CreateAndModifyUserResponse createUserResponse;
    CreateAndModifyUserResponse modifyUserResponse;

    @BeforeTest
    public void beforeTest() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    public void createUserAndValidateByName() throws IOException, InterruptedException {
        UserData userData = ServiceHelper.setInputData();
        createUserResponse = PetStoreService.postUser(userData.getObjectMapper().writeValueAsString(userData.getNewUser()));
        ValidationsService.validateUserCreation(userData.getNewUser(), createUserResponse);

        userResponse = PetStoreService.getUser(userData.getNewUser().getUsername());
        ValidationsService.validateNewUserData(userData.getNewUser(), userResponse);
    }

    @Test
    public void updateUserAndValidateByName() throws IOException, InterruptedException {
        UserData userData = ServiceHelper.setInputData();
        createUserResponse = PetStoreService.postUser(userData.getObjectMapper().writeValueAsString(userData.getNewUser()));
        ValidationsService.validateUserCreation(userData.getNewUser(), createUserResponse);

        userResponse = PetStoreService.getUser(userData.getNewUser().getUsername());
        ValidationsService.validateNewUserData(userData.getNewUser(), userResponse);

        ServiceHelper.setUserWithNewData(userData.getNewUser());
        modifyUserResponse = PetStoreService.putUser(userData.getNewUser().getUsername(), userData.getObjectMapper().writeValueAsString(userData.getNewUser()));

        userResponse = PetStoreService.getUser(userData.getNewUser().getUsername());
        ValidationsService.validateNewUserData(userData.getNewUser(), userResponse);
    }
}
