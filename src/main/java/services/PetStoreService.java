package services;

import apiModel.CreateAndModifyUserResponse;
import apiModel.User;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.logging.Logger;

import static org.apache.http.HttpStatus.SC_OK;

public class PetStoreService {

    private static final Logger logger = Logger.getLogger(User.class.getName());
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    public static CreateAndModifyUserResponse postUser(String newUser) {
        logger.info("Se realiza un post al servicio para crear nuevo usuario");
        RequestSpecification request = RestAssured.given();
        setHeaders(request);
        request.body(newUser);
        Response response = request.post(BASE_URL + "/user");
        response.then().statusCode(SC_OK);
        String jsonResponse = response.getBody().asString();
        return new Gson().fromJson(jsonResponse, CreateAndModifyUserResponse.class);
    }

    public static User getUser(String userName) throws InterruptedException {
        logger.info("Se realiza un get sobre el usuario para obtener los datos");
        RequestSpecification request = RestAssured.given();
        setHeaders(request);
        request.pathParam("username", userName);
        Response response = request.get(BASE_URL + "/user/{username}");
        response = retryToGet(response, request);
        response.then().statusCode(SC_OK);
        String jsonResponse = response.getBody().asString();
        return new Gson().fromJson(jsonResponse, User.class);
    }

    public static CreateAndModifyUserResponse putUser(String userToModify, String newUserData) {
        logger.info("Se realiza un put sobre el usuario creado");
        RequestSpecification request = RestAssured.given();
        setHeaders(request);
        request.body(newUserData);
        request.pathParam("username", userToModify);
        Response response = request.put(BASE_URL + "/user/{username}");
        response.then().statusCode(SC_OK);
        String jsonResponse = response.getBody().asString();
        return new Gson().fromJson(jsonResponse, CreateAndModifyUserResponse.class);
    }

    private static void setHeaders(RequestSpecification request) {
        request.header("Content-Type", "application/json");
    }

    private static Response retryToGet(Response response, RequestSpecification request) throws InterruptedException {
        int retries = 5;
        for (int i = 0; i < retries; i++) {
            Thread.sleep(2000);
            if (response.getBody().asString().contains("User not found")) {
                response = request.get(BASE_URL + "/user/{username}");
            } else {
                break;
            }
        }
        return response;
    }
}
