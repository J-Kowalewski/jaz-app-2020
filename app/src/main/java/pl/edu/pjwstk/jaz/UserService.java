package pl.edu.pjwstk.jaz;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

@Service
public class UserService {
    private final EntityManager em;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserService(EntityManager em){
        this.em = em;
    }

    public void saveUser(String username){
        var userEntity = new UserEntity();

        //userEntity.setUsername("jkowalewski");
        //userEntity.setPassword("jkowalewski");

        em.persist(userEntity);

        //userEntity.getId() > 0;
    }
    public UserEntity findUserByUsername(String username) {
        return em.createQuery("select ue from UserEntity ue where ue.id= :id",UserEntity.class)
                .setParameter("id",username) // <<< username ^^^^
                .getSingleResult();
    }
}
