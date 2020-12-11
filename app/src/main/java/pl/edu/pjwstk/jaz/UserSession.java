package pl.edu.pjwstk.jaz;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {
    public boolean isLogged = false;

    boolean isLoggedIn(){
        return isLogged;
    }

    public void logIn() {
        isLogged = true;
    }
    /*
    jakas zmienna informacja ktora pozwoli okreslic czy uzytkownik jest zalogowany czy nie

    metody do zarządzania ta informacja
 */
}
