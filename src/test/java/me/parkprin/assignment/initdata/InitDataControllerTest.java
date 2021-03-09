package me.parkprin.assignment.initdata;

import me.parkprin.assignment.orders.OrderController;
import me.parkprin.assignment.products.ProductController;
import me.parkprin.assignment.reviews.ReviewController;
import me.parkprin.assignment.users.UserController;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InitDataControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void reviewFailureTest1() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/initdata")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(InitDataController.class))
                .andExpect(handler().methodName("setting"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response", is(true)))
                .andExpect(jsonPath("$.error", is(nullValue())));



        result = mockMvc.perform(
                get("/api/orders/")
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(OrderController.class))
                .andExpect(handler().methodName("findAll"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.length()", is(7)))
                .andExpect(jsonPath("$.error", is(nullValue())));

        result = mockMvc.perform(
                get("/api/reviews/")
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ReviewController.class))
                .andExpect(handler().methodName("findAll"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.length()", is(1)))
                .andExpect(jsonPath("$.error", is(nullValue())));

        result = mockMvc.perform(
                get("/api/products/")
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(ProductController.class))
                .andExpect(handler().methodName("findAll"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.length()", is(3)))
                .andExpect(jsonPath("$.error", is(nullValue())));

        result = mockMvc.perform(
                get("/api/users/")
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("findAll"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.length()", is(1)))
                .andExpect(jsonPath("$.error", is(nullValue())));
    }


}
