package pl.edu.pjwstk.jaz.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.user.UserEntity;
import pl.edu.pjwstk.jaz.user.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class RegisterController {
    final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        Set<String> authorities=new HashSet<>();
        authorities.add("user");
        if(registerRequest.getUsername().equals("admin")){
            authorities.add("admin");
        }
        UserEntity user = userService.findByUsername(registerRequest.getUsername());

        if(user != null){
            return new ResponseEntity<>("User already registered", HttpStatus.CONFLICT);
        }
        else{
            userService.saveUser(registerRequest.getUsername(),registerRequest.getPassword(),authorities);
            return new ResponseEntity<>("User registered", HttpStatus.CREATED);
        }


    }
}
