package pl.edu.pjwstk.jaz;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // username haslo itp (TO CO W SQL)
}
