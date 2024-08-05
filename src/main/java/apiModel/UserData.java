package apiModel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserData {
    private User newUser;
    private ObjectMapper objectMapper;

    public UserData(User user, ObjectMapper objectMapper) {
        this.newUser = user;
        this.objectMapper = objectMapper;
    }

    public User getNewUser() {
        return newUser;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}