package pl.edu.pjwstk.jaz;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Component //@Service
public class AuthenticationService {

    final UserSession userSession;
    final RegisteredUsers registeredUsers;
    final RegisterController registerController;

    public AuthenticationService(UserSession userSession, RegisteredUsers registeredUsers, RegisterController registerController) {
        this.userSession = userSession;
        this.registeredUsers = registeredUsers;
        this.registerController = registerController;
    }

    public boolean login(String username, String password){
        if(!username.isEmpty() && !password.isEmpty()){
            if(registerController.registeredUsers.isRegisteredUser(username)&&registerController.registeredUsers.isPasswordCorrect(username,password)) {
                userSession.logIn();
                SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(registeredUsers.getUser(username)));
                return true;
            }
        }
        return false;
    }
}
