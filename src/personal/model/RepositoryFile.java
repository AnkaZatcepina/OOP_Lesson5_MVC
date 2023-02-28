package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private Mapper mapper;
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation, Mapper mapper) {
        this.fileOperation = fileOperation;
        this.mapper = mapper;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            if (!line.equals(""))
                users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public String createUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        saveRepository(users);
        return id;
    }

    public void deleteUser(String userId) {
        List<User> users = getAllUsers();
        User foundUser = null;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                foundUser = user;
            }
        }
        if (foundUser != null){
            users.remove(foundUser);}
        saveRepository(users);
    }

    public void updateUser(User user){
        List<User> users = getAllUsers();
        User foundUser = null;
        for (User currentUser : users) {
            if (currentUser.getId().equals(user.getId())) {
                foundUser = currentUser;
            }
        }
        if (foundUser != null){
            foundUser.setFirstName(user.getFirstName());
            foundUser.setLastName(user.getLastName());
            foundUser.setPhone(user.getPhone());
        }
        saveRepository(users);
    }

    private void saveRepository(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

}
