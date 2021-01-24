package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.login.LoginRequest;
import pl.edu.pjwstk.jaz.register.RegisterRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
public class LoginTest {

    @BeforeClass
    public static void beforeClass() throws Exception{
        given()
                .body(new RegisterRequest("user","user"))
                .when()
                .contentType(ContentType.JSON)
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("admin","admin"))
                .when()
                .contentType(ContentType.JSON)
                .post("/api/register")
                .thenReturn();
    }

    @Test
    public void should_login_registered_user(){
        given()
                .when()
                .body(new LoginRequest("user","user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());
    }

    @Test
    public void should_not_login_unregistered_user(){
        var response = given()
                .when()
                .body(new LoginRequest("jkowalewski","jkowalewski"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.cookies())
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }
    @Test
    public void should_login_admin(){
        given()
                .when()
                .body(new LoginRequest("admin","admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());
    }
    @Test
    public void filterTest1(){
        var response = given()
                .when()
                .body(new LoginRequest("user","user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/edit")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }
    //please help vvvvvvv
    @Test
    public void filterTest2(){
        var response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(new LoginRequest("admin","admin"))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/edit")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void registerTest(){
        given()
                .contentType(ContentType.JSON)
                .body(new RegisterRequest("login","password"))
                .when()
                .post("/api/register")
                .then()
                .statusCode(200);
    }
}

