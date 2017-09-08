package com.teamwolf.controller;

import com.teamwolf.beans.*;
import com.teamwolf.data.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;

import java.time.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class UserControllerTest
{

//    @Mock
//    private dao;


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    @Autowired
    private UserData userData;


    User user1 = new User();
    User user2 = new User();

    {
        user1.setUsername("cJohnson");
        user1.setGamesPlayed(1);
        user1.setGamesWon(5);
        user1.setUserid(1);
        user2.setUsername("GLaDOS");
        user2.setGamesPlayed(9001);
        user2.setGamesWon(9001);
        user2.setUserid(2);
    }

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        entityManager.persist(user1);
        entityManager.persist(user2);

        //when(bookService.getBook(17)).thenReturn(testBook);
    }



    @Test
    public void getValidUser() throws Exception
    {
        mockMvc.perform(get("/services/user/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.success", is(true)))
               .andExpect(jsonPath("$.username", is("cJohnson")))
        ;

    }


    @Test
    public void getFriends() throws Exception
    {
    }

    @Test
    public void newUser() throws Exception
    {
    }

}