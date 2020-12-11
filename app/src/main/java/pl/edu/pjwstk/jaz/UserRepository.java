package pl.edu.pjwstk.jaz;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private Map<String, User> userMap = new HashMap<>();

    public void add(User user) {
        throw new UnsupportedOperationException("TODO");
    }

    public Optional<User> findByUsername(String username){
        throw new UnsupportedOperationException("TODO");
    }

}
