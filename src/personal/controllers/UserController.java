package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;
    private ValidateUser validator = new ValidateUser();

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validator.check(user);
        repository.createUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        throw new Exception("пользователь не найден");
    }

    public void deleteUser(String userId) throws Exception {
        repository.deleteUser(userId);
    }

    public List<User> readUsers() {
        List<User> users = repository.getAllUsers();
        return users;
    }

    public User updateUser(User user, String firstName, String lastName, String phone) throws Exception {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);       
        validator.check(user);
        repository.updateUser(user);
        return user;
    }
}
