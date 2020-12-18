package pl.edu.pjwstk.jaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    RegisteredUsers registeredUsers;

    @Autowired
    public RegisterController(RegisteredUsers registeredUsers){ this.registeredUsers = registeredUsers;}

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest){
        registeredUsers.add(registerRequest.getUsername(),registerRequest.getPassword());
        System.out.println("User: "+registerRequest.getUsername()+" added");
    }
}
