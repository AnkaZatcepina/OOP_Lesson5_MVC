package personal.controllers;

import java.util.regex.Pattern;

import personal.model.User;

public class ValidateUser {
    //регулярное на отсутствие пробелов
    private Pattern patternName = Pattern.compile("^\\S+$");
    //регулярное на цифры
    private Pattern patternPhone = Pattern.compile("^\\d*$");
    public void check(User user) throws Exception {
        if (!patternName.matcher(user.getFirstName()).find()){
            throw new Exception("некорректное имя");
        };
        if (!patternName.matcher(user.getLastName()).find()){
            throw new Exception("некорректная фамилия");
        };
        if (!patternPhone.matcher(user.getPhone()).find()){
            throw new Exception("некорректный номер");
        };
    };
    public boolean checkFirstName(User user){
        user.getFirstName();
        return true;
    };
}
