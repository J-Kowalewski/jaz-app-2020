package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) throws UnauthorizedException {
        //uwierzytelnić
        var islogged = authenticationService.login(loginRequest.getUsername(),loginRequest.getPassword());
        if (!islogged){
            throw new UnauthorizedException();
        }
    }
}
