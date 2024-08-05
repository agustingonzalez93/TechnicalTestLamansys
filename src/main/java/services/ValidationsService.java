package services;

import apiModel.CreateAndModifyUserResponse;
import apiModel.User;
import org.testng.Assert;

import java.util.logging.Logger;

public class ValidationsService {
    private static final Logger logger = Logger.getLogger(User.class.getName());
    private static final int defaultCode = 200;
    private static final String defaultType = "unknown";

    public static void validateUserCreation(User newUser, CreateAndModifyUserResponse createAndModifyUserResponse) {
        logger.info("Se realizan validaciones sobre la creacion del usuario");
        Assert.assertEquals(String.valueOf(newUser.getId()), createAndModifyUserResponse.getMessage(), "No coincide el Id del usuario");
        Assert.assertEquals(defaultCode, createAndModifyUserResponse.getCode(), "No coincide el code");
        Assert.assertEquals(defaultType, createAndModifyUserResponse.getType(), "No coincide el Itype");
    }

    public static void validateNewUserData(User newUser, User userResponse) {
        logger.info("Se realizan validaciones sobre los datos del usuario");
        Assert.assertEquals(newUser.getId(), userResponse.getId(), "No coincide el Id del usuario");
        Assert.assertEquals(newUser.getUsername(), userResponse.getUsername(), "No coincide el username");
        Assert.assertEquals(newUser.getFirstName(), userResponse.getFirstName(), "No coincide el firstName");
        Assert.assertEquals(newUser.getLastName(), userResponse.getLastName(), "No coincide el lastName");
        Assert.assertEquals(newUser.getEmail(), userResponse.getEmail(), "No coincide el email");
        Assert.assertEquals(newUser.getPassword(), userResponse.getPassword(), "No coincide la password");
        Assert.assertEquals(newUser.getPhone(), userResponse.getPhone(), "No coincide el phone");
        Assert.assertEquals(newUser.getUserStatus(), userResponse.getUserStatus(), "No coincide el userStatus");
    }
}
