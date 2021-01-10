package pl.edu.pjwstk.jaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class RegisterController {
    final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest){
        Set<String> authorities=new HashSet<>();
        authorities.add("user");
        if(registerRequest.getUsername().equals("admin")){
            authorities.add("admin");
        }
        userService.saveUser(registerRequest.getUsername(),registerRequest.getPassword(),authorities);
    }
}
