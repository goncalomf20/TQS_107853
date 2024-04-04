package cars.lab3_2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import cars.lab3_2.controller.CarController;
import cars.lab3_2.service.CarManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@WebMvcTest(CarController.class)
public class SevenTwoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService carManagerService;

    @Test
    void test() {

        RestAssuredMockMvc.mockMvc(mockMvc);

        given().when().get("/api/cars").then().statusCode(200);
    }
}
