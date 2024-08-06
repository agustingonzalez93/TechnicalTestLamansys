package utilities;

import apiModel.User;
import apiModel.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ServiceHelper {
    private static final String jsonFilePathCreateUser = "src/test/resources/dataForCreatingUser.json";
    private static final String jsonFilePathModifyUser = "src/test/resources/dataToModifyUser.json";

    public static UserData setInputData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User newUser = objectMapper.readValue(new File(jsonFilePathCreateUser), User.class);
        newUser.setId(new Random().nextInt(900000) + 100000);
        newUser.setUsername(newUser.getUsername().concat(String.valueOf(newUser.getId())));
        return new UserData(newUser, objectMapper);
    }

    public static void setUserWithNewData(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User myJson = objectMapper.readValue(new File(jsonFilePathModifyUser), User.class);
        user.setFirstName(myJson.getFirstName());
        user.setLastName(myJson.getLastName());
        user.setEmail(myJson.getEmail());
        user.setPassword(myJson.getPassword());
        user.setPhone(myJson.getPhone());
    }
}
