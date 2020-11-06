package pl.edu.pjwstk.jaz.average;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageControllerIT {
    @Test
    public void should_calculate_simple_average_1() {
        given()
                .param("numbers","1,2,3,4")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("2.5"));
    }
    @Test
    public void should_calculate_simple_average_2() {
        given()
                .param("numbers","2,3,5,7")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("4.25"));
    }
    @Test
    public void should_calculate_average_of_zeros() {
        given()
                .param("numbers","0,0,0,0")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
    @Test
    public void should_work_without_parameters() {
        given()
                .param("numbers","")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("nie podano parametrow"));
    }
    @Test
    public void should_skip_zeros_and_commas() {
        given()
                .param("numbers","1,2,3")
                .when()
                .get("/api/average")
                .then()
                .statusCode(200)
                .body(equalTo("2"));
    }
}
