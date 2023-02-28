package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;

        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        createUser();
                        break;
                    case READ:
                        readUser();
                        break;
                    case LIST:
                        listUsers();
                        break;
                    case DELETE:
                        deleteUser();
                        break;
                    case UPDATE:
                        updateUser();
                        break;
                }
            } catch (Exception ee) {
                System.out.printf("Ошибка: %s\n", ee.getMessage());
            }
        }
    }

    private void readUser() {
        String id = prompt("Идентификатор пользователя: ");
        try {
            User user = userController.readUser(id);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createUser() throws Exception {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.saveUser(new User(firstName, lastName, phone));
    }

    private void listUsers() {
        List<User> users = userController.readUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void deleteUser() {
        String id = prompt("Идентификатор пользователя: ");
        try {
            userController.deleteUser(id);
            System.out.println("Пользователь удалён");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateUser() {
        String id = prompt("Идентификатор пользователя: ");
        try {
            User user = userController.readUser(id);
            String firstName = prompt("Имя: ");
            String lastName = prompt("Фамилия: ");
            String phone = prompt("Номер телефона: ");
            userController.updateUser(user, firstName, lastName, phone);
            System.out.println("Пользователь сохранён");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
