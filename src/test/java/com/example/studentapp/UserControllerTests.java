package com.example.studentapp;

import com.example.studentapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    @MockBean
    UserDetailsService userDetailsService;
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    private UserService userService;
    private MockMvc mockMvc;

    @BeforeEach()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).
                apply(springSecurity())
                .build();
    }

//    @Test
//    @WithMockUser(username = "admin",password = "admin",roles = "{USER}")
//    void checkAddUserTest() throws Exception {
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/users/register")
//                        .with(SecurityMockMvcRequestPostProcessors.csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("X-Foo","Duke")
//                        .content("{\"username\":\"duke\",\"password\":\"duke\",\"role\":\"USER\"}"))
//
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "{ADMIN}")
    void checkAllUsersJSONTest() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/users/userslist")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Foo", "Duke"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

//    @Test
//    @WithMockUser(username = "admin",password = "admin",roles = "{USER}")
//    void checkUserViewTest() throws Exception {
//        this.mockMvc
//                .perform(MockMvcRequestBuilders
//                        .get("/users")
//                        .with(SecurityMockMvcRequestPostProcessors.csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("X-Foo","Duke"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attributeExists("role"))
//                .andExpect(MockMvcResultMatchers.model().attribute("role","[USER]"));
//
//
//    }
//
//


}
