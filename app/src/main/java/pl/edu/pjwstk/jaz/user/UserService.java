package pl.edu.pjwstk.jaz.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.user.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class UserService {
    @PersistenceContext
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
        try{
            return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username =: username", UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    public boolean passwordCheck(String password, String encodePass){
        return passwordEncoder.matches(password,encodePass);
    }
}
