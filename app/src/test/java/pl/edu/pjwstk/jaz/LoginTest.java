package pl.edu.pjwstk.jaz;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
public class LoginTest {

    @BeforeClass
    public static void beforeClass() throws  Exception{
        given()
                .body(new RegisterRequest("admin","admin"))
                .when()
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("user","user"))
                .when()
                .post("/api/register")
                .thenReturn();
    }

    @Test
    public void should_login_registered_user(){
        given()
                .when()
                .body(new LoginRequest("user","user"))
                .post("/api/login")
                .then()
                .statusCode(200);
    }

    @Test
    public void should_not_login_unregistered_user(){
        given()
                .when()
                .body(new LoginRequest("jkowalewski","jkowalewski"))
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    @Test
    public void should_login_admin(){
        given()
                .when()
                .body(new LoginRequest("admin","admin"))
                .post("/api/login")
                .then()
                .statusCode(200);
    }
}
