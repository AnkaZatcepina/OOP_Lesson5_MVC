package personal.model;
//import com.fasterxml.jackson.databind.ObjectMapper; 
//import com.fasterxml.jackson.databind.ObjectWriter; 

public class UserMapperSemicolon implements Mapper{
    public String map(User user) {
        return String.format("%s;%s;%s;%s\n", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());              
    }

    public User map(String line) {
        String[] lines = line.split(";");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }
}
