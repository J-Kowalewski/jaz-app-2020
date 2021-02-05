package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.auction.AddAuctionRequest;
import pl.edu.pjwstk.jaz.auction.EditAuctionRequest;
import pl.edu.pjwstk.jaz.category.CategoryRequest;
import pl.edu.pjwstk.jaz.login.LoginRequest;
import pl.edu.pjwstk.jaz.register.RegisterRequest;

import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
public class EditTest {

    @BeforeClass
    public static void beforeClass(){
        given()
                .body(new RegisterRequest("user","user"))
                .when()
                .contentType(ContentType.JSON)
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("user2","user2"))
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
        var response = given()
                .when()
                .body(new LoginRequest("admin","admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new CategoryRequest("test category"))
                .when()
                .contentType(ContentType.JSON)
                .post("/api/add/category")
                .thenReturn();
    }
    @Test
    public void authorShouldBeAbleToEditAuction(){
        var parameterMap = new HashMap<String,String>();
        parameterMap.put("klucz1","wartosc7");
        parameterMap.put("klucz7","wartosc4");
        var response = given()
                .when()
                .body(new LoginRequest("user2","user2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        var auction = given()
                .cookies(response.getCookies())
                .body(new AddAuctionRequest("test4 title","test4 description", 20.20,
                        "test category", Arrays.asList("link1","link2","link3"), parameterMap))
                .contentType(ContentType.JSON)
                .post("/api/add/auction")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new EditAuctionRequest(Long.valueOf(auction.getHeader("id")),"changed test4 title","changedd test4 description", 20.20,
                        "test category",Arrays.asList("link1","link2","link3"), parameterMap,0L))
                .contentType(ContentType.JSON)
                .put("/api/edit/auction")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());
    }
    @Test
    public void otherUserShouldNotBeAbleToEditAuction(){
        var parameterMap = new HashMap<String,String>();
        parameterMap.put("klucz1","wartosc7");
        parameterMap.put("klucz7","wartosc4");
        var response = given()
                .when()
                .body(new LoginRequest("user2","user2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        var auction = given()
                .cookies(response.getCookies())
                .body(new AddAuctionRequest("test4 title","test4 description", 20.20,
                        "test category",Arrays.asList("link1","link2","link3"), parameterMap))
                .contentType(ContentType.JSON)
                .post("/api/add/auction")
                .thenReturn();
        response = given()
                .when()
                .body(new LoginRequest("user","user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new EditAuctionRequest(Long.valueOf(auction.getHeader("id")),"changed test4 title","changedd test4 description", 20.20,
                        "test category",Arrays.asList("link1","link2","link3"), parameterMap,0L))
                .contentType(ContentType.JSON)
                .put("/api/edit/auction")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }
}
