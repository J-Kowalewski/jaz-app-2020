package pl.edu.pjwstk.jaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Component //@Service
public class AuthenticationService {
    final UserSession userSession;
    final RegisterController registerController;
    final UserService userService;

    public AuthenticationService(UserSession userSession,  UserService userService,
                                 RegisterController registerController) {
        this.userService = userService;
        this.userSession = userSession;
        this.registerController = registerController;
    }

    public boolean login(String username, String password){
       // if(!username.isEmpty() && !password.isEmpty() && registerController.registeredUsers.isRegisteredUser(username)
       //         &&registerController.registeredUsers.isPasswordCorrect(username,password)){
        UserEntity userEntity = userService.findByUsername(username);
        if(userService.passwordCheck(password, userEntity.getPassword())){
            userSession.logIn();
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(userEntity));
            return true;
        }
        return false;
    }
}
