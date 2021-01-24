package pl.edu.pjwstk.jaz;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.register.RegisterController;
import pl.edu.pjwstk.jaz.user.UserService;

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
        var userEntity = userService.findByUsername(username);
        if(userService.passwordCheck(password, userEntity.getPassword())){
            userSession.logIn();
            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(userEntity));
            return true;
        }
        return false;
    }
}
