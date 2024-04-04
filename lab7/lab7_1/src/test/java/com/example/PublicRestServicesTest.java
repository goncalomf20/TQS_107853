package com.example;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PublicRestServicesTest {

    @Test
    void test1() {
        String url = "https://jsonplaceholder.typicode.com/todos";
        given().when().get(url).then().assertThat().statusCode(200);
    }


    @Test
    void test2() {
        String url = "https://jsonplaceholder.typicode.com/todos/4";
        given().when().get(url).then().assertThat().body("title", equalTo("et porro tempora"));

    }

    @Test
    void test3() {
        String url = "https://jsonplaceholder.typicode.com/todos";
        given().when().get(url).then().assertThat().body("id",hasItems(198,199));
    }   

    @Disabled
    @Test
    void test4() {
        String url = "https://jsonplaceholder.typicode.com/todos";
        given()
            .when()
            .get(url)
            .then()
            //.time(lessThan(2), TimeUnit.SECONDS)  //esta função não funciona, mas é a única que encontrei para realizar esta tarefa
            .statusCode(200)
            .body("id", hasItems(198, 199));
    }


}