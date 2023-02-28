package personal;

import personal.controllers.UserController;
import personal.model.FileOperation;
import personal.model.FileOperationImpl;
import personal.model.Repository;
import personal.model.RepositoryFile;
import personal.model.UserMapperComma;
import personal.model.UserMapperSemicolon;
import personal.views.ViewUser;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("users_new.txt");
        Repository repository = new RepositoryFile(fileOperation, new UserMapperSemicolon());
        UserController controller = new UserController(repository);
        ViewUser view = new ViewUser(controller);
        view.run();
    }
}
