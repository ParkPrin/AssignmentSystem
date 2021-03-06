package me.parkprin.assignment.users;

import me.parkprin.assignment.config.JwtTokenConfigure;

import me.parkprin.assignment.security.WithMockJwtAuthentication;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UserControllerTest {
    private MockMvc mockMvc;

    private JwtTokenConfigure jwtTokenConfigure;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setJwtTokenConfigure(JwtTokenConfigure jwtTokenConfigure) {
        this.jwtTokenConfigure = jwtTokenConfigure;
    }

    @Before
    public void dataSetting() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/initdata")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    @DisplayName("????????? ?????? ????????? (?????????, ??????????????? ???????????? ?????? ??????)")
    public void loginFailureTest() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"principal\":\"tester@gmail.com\",\"credentials\":\"4321\"}")
        );
        result.andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("login"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error.status", is(401)))
        ;
    }



    @Test
    @DisplayName("????????? ?????? ?????????(?????????, ??????????????? ????????? ??????)")
    public void loginSuccessTest() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"principal\":\"tester@gmail.com\",\"credentials\":\"1234\"}")
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("login"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.token").exists())
                .andExpect(jsonPath("$.response.token").isString())
                .andExpect(jsonPath("$.response.user.name", is("tester")))
                .andExpect(jsonPath("$.response.user.email", is("tester@gmail.com")))
                .andExpect(jsonPath("$.response.user.loginCount").exists())
                .andExpect(jsonPath("$.response.user.loginCount").isNumber())
                .andExpect(jsonPath("$.response.user.lastLoginAt").exists())
        ;
    }



    @Test
    @DisplayName("??? ?????? ?????? ?????? ????????? (????????? ???????????? ?????? ??????)")
    public void meFailureTest() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/users/me")
                        .accept(MediaType.APPLICATION_JSON)
                        .header(jwtTokenConfigure.getHeader(), "Bearer " + randomAlphanumeric(60))
        );
        result.andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error.status", is(401)))
                .andExpect(jsonPath("$.error.message", is("Unauthorized")))
        ;
    }

    @Test
    @WithMockJwtAuthentication
    @DisplayName("??? ?????? ?????? ?????? ????????? (????????? ????????? ??????)")
    public void meSuccessTest() throws Exception {


        ResultActions result = mockMvc.perform(
                get("/api/users/me")
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("me"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.name", is("tester")))
                .andExpect(jsonPath("$.response.email", is("tester@gmail.com")))
                .andExpect(jsonPath("$.response.loginCount").exists())
                .andExpect(jsonPath("$.response.loginCount").isNumber())
        ;
    }
}
