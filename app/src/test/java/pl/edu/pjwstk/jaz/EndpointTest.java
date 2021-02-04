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
import pl.edu.pjwstk.jaz.category.EditCategoryRequest;
import pl.edu.pjwstk.jaz.login.LoginRequest;
import pl.edu.pjwstk.jaz.photo.AddPhotoRequest;
import pl.edu.pjwstk.jaz.register.RegisterRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
public class EndpointTest {

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
//    @Test
//    public void shouldAddAuction(){
//        var response = given()
//                .when()
//                .body(new LoginRequest("user","user"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .body(new AddAuctionRequest("test1 title","test1 description", 20.20,
//                        "test category","test1Key","test1Value"))
//                .contentType(ContentType.JSON)
//                .post("/api/add/auction")
//                .then()
//                .statusCode(HttpStatus.CREATED.value());
//    }
//    @Test
//    public void shouldNotAddAuctionWithWrongCategory(){
//        var response = given()
//                .when()
//                .body(new LoginRequest("user","user"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .body(new AddAuctionRequest("test2 title","test2 description", 20.20,
//                        "wrong category","test2Key","test2Value"))
//                .contentType(ContentType.JSON)
//                .post("/api/add/auction")
//                .then()
//                .statusCode(HttpStatus.BAD_REQUEST.value());
//    }
//    @Test
//    public void adminShouldBeAbleToAddCategory() {
//        var response = given()
//                .when()
//                .body(new LoginRequest("admin", "admin"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .body(new CategoryRequest("test category 8"))
//                .when()
//                .contentType(ContentType.JSON)
//                .post("/api/add/category")
//                .then()
//                .statusCode(HttpStatus.CREATED.value());
//    }
//    @Test
//    public void userShouldNotBeAbleToAddCategory() {
//        var response = given()
//                .when()
//                .body(new LoginRequest("user", "user"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .body(new CategoryRequest("test category 2"))
//                .when()
//                .contentType(ContentType.JSON)
//                .post("/api/add/category")
//                .then()
//                .statusCode(HttpStatus.FORBIDDEN.value());
//    }
//    @Test
//    public void authorShouldBeAbleToAddPhotoToAuction(){
//        var response = given()
//                .when()
//                .body(new LoginRequest("user2","user2"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        var auction = given()
//                .cookies(response.getCookies())
//                .body(new AddAuctionRequest("test4 title","test4 description", 20.20,
//                        "test category","test4Key","test4Value"))
//                .contentType(ContentType.JSON)
//                .post("/api/add/auction")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .body(new AddPhotoRequest(Long.valueOf(auction.getHeader("id")),"testlink"))
//                .contentType(ContentType.JSON)
//                .post("/api/add/photo")
//                .then()
//                .statusCode(HttpStatus.ACCEPTED.value());
//    }
//    @Test
//    public void otherUserShouldNotBeAbleToAddPhotoToAuction(){
//        var response = given()
//                .when()
//                .body(new LoginRequest("user2","user2"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        var auction = given()
//                .cookies(response.getCookies())
//                .body(new AddAuctionRequest("test4 title","test4 description", 20.20,
//                        "test category","test4Key","test4Value"))
//                .contentType(ContentType.JSON)
//                .post("/api/add/auction")
//                .thenReturn();
//        response = given()
//                .when()
//                .body(new LoginRequest("user","user"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .body(new AddPhotoRequest(Long.valueOf(auction.getHeader("id")),"testlink"))
//                .contentType(ContentType.JSON)
//                .post("/api/add/photo")
//                .then()
//                .statusCode(HttpStatus.FORBIDDEN.value());
//    }
//    @Test
//    public void authorShouldBeAbleToEditAuction(){
//        var response = given()
//                .when()
//                .body(new LoginRequest("user2","user2"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        var auction = given()
//                .cookies(response.getCookies())
//                .body(new AddAuctionRequest("test4 title","test4 description", 20.20,
//                        "test category","test4Key","test4Value"))
//                .contentType(ContentType.JSON)
//                .post("/api/add/auction")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .body(new EditAuctionRequest(Long.valueOf(auction.getHeader("id")),"edited test title",
//                        "edited test description", 20.20, "test category","test4Key","test4Value"))
//                .contentType(ContentType.JSON)
//                .put("/api/edit/auction")
//                .then()
//                .statusCode(HttpStatus.ACCEPTED.value());
//    }
//    @Test
//    public void otherUserShouldNotBeAbleToEditAuction(){
//        var response = given()
//                .when()
//                .body(new LoginRequest("user2","user2"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        var auction = given()
//                .cookies(response.getCookies())
//                .body(new AddAuctionRequest("test4 title","test4 description", 20.20,
//                        "test category","test4Key","test4Value"))
//                .contentType(ContentType.JSON)
//                .post("/api/add/auction")
//                .thenReturn();
//        response = given()
//                .when()
//                .body(new LoginRequest("user","user"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//        given()
//                .cookies(response.getCookies())
//                .body(new EditAuctionRequest(Long.valueOf(auction.getHeader("id")),"edited test title",
//                        "edited test description", 20.20, "test category","test4Key","test4Value"))
//                .contentType(ContentType.JSON)
//                .put("/api/edit/auction")
//                .then()
//                .statusCode(HttpStatus.FORBIDDEN.value());
//    }
    @Test
    public void adminShouldBeAbleToEditCategory() {
        var response = given()
                .when()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new EditCategoryRequest("test category","edited test category"))
                .when()
                .contentType(ContentType.JSON)
                .put("/api/edit/category")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void userShouldNotBeAbleToEditCategory() {
        var response = given()
                .when()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new EditCategoryRequest("test category","edited test category"))
                .when()
                .contentType(ContentType.JSON)
                .put("/api/edit/category")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }
    @Test
    public void getCorrectIdAuctionTest() {
        var response = given()
                .when()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/explore/auction/11")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void getIncorrectIdAuctionTest() {
        var response = given()
                .when()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/explore/auction/15685681")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
    @Test
    public void getCorrectIdPhotoTest() {
        var response = given()
                .when()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/explore/photo/11")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void getIncorrectIdPhotoTest() {
        var response = given()
                .when()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/explore/photo/15685681")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
    @Test
    public void getCorrectIdCategoryTest() {
        var response = given()
                .when()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/explore/category/4")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void getIncorrectIdCategoryTest() {
        var response = given()
                .when()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/explore/category/15685681")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
    @Test
    public void getAuctionListTest() {
        var response = given()
                .when()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/explore")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}