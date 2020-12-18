package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RegisteredUsers {
    private final Map<String, User> registeredUsers = new HashMap<>();

    public RegisteredUsers(){
        User admin = new User("admin","admin");
        admin.addAuthority("user");
        admin.addAuthority("admin");
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
