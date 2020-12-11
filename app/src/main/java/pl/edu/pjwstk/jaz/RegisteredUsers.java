package pl.edu.pjwstk.jaz;

import java.util.HashMap;
import java.util.Map;

public class RegisteredUsers {
    private final Map<String, User> registeredUsers;

    public RegisteredUsers(){
        registeredUsers = new HashMap<>();

        User admin = new User("admin","admin");
        admin.addAuthority("admin");
        admin.addAuthority("user");
        registeredUsers.put("admin",admin);
    }
    public void add(String username, String password){registeredUsers.put(username, new User(username, password));}

    public boolean isPasswordCorrect(String username, String password) {
        return registeredUsers.get(username).getPassword().equals(password);
    }

    public boolean isRegisteredUser(String username) {
        return registeredUsers.containsKey(username);
    }

    public User getUser(String username) {
        return registeredUsers.get(username);
    }
}
