package pl.edu.pjwstk.jaz.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.AuthenticationService;

@RestController
public class LoginController {
    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        //uwierzytelnić
        var isRegistered = authenticationService.login(loginRequest.getUsername(),loginRequest.getPassword());
        if (!isRegistered){
            return new ResponseEntity<>("user not registered", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("logged in",HttpStatus.ACCEPTED);
    }
}
