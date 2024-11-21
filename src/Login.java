/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;
import java.util.regex.*;

public class Login {

    private static final String PASSWORD_COMPLEXITY_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    private HashMap<String, String> userCredentials = new HashMap<>();
    private HashMap<String, String> userFullNames = new HashMap<>();

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password.isEmpty()) return false;
        Pattern pattern = Pattern.compile(PASSWORD_COMPLEXITY_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String registerUser(String firstName, String lastName, String username, String password) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted. Please ensure your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. Please ensure the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }
        if (userCredentials.containsKey(username)) {
            return "Username already exists. Please choose another username.";
        }

        userCredentials.put(username, password);
        userFullNames.put(username, firstName + " " + lastName);
        return "User has been registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    public String returnLoginStatus(boolean status, String username) {
        if (status) {
            String fullName = userFullNames.get(username);
            return "Welcome " + fullName + "! It is great to see you again.";
        }
        return "Username or password incorrect. Please try again.";
    }
}
