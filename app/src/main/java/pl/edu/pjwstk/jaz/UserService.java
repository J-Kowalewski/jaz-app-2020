package pl.edu.pjwstk.jaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService( EntityManager entityManager) {
        this.entityManager=entityManager;
    }
    public void saveUser(String login, String password, Set<String> authorities){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(login);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setAuthorities(authorities);

        entityManager.persist(userEntity);
    }
    public UserEntity findByUsername(String username){
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username =: username", UserEntity.class)
                .setParameter("username",username)
                .getSingleResult();
    }
    public boolean passwordCheck(String password, String encodePass){
        return passwordEncoder.matches(password,encodePass);
    }
}
