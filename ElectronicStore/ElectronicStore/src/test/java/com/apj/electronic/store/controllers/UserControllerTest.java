package com.apj.electronic.store.controllers;

import com.apj.electronic.store.dtos.PageableResponse;
import com.apj.electronic.store.dtos.UserDto;
import com.apj.electronic.store.entities.Role;
import com.apj.electronic.store.entities.User;
import com.apj.electronic.store.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Set;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest
{

    @MockBean
    private UserService userService;

    private Role role;
    private User user;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init()
    {

        role = Role.builder().roleId("abcd").roleName("NORMAL").build();
        user = User.builder()
                .name("Amrit Jaiswal")
                .email("amrit@gmail.com")
                .about("This is testing crate method")
                .gender("Male")
                .imageName("anb.png")
                .password("amrit")
                .roles(Set.of(role))
                .build();

    }

    @Test
    public void createUserTest() throws Exception
    {
        UserDto dto = mapper.map(user, UserDto.class);
        Mockito.when(userService.createUser(Mockito.any())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObectToJsonString(user))
                        .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").exists());

    }

    @Test
    public void updateUserTest() throws Exception
    {
        String userId="jsadkaj";
        UserDto dto = this.mapper.map(user, UserDto.class);

        Mockito.when(userService.updateUser(Mockito.any(),Mockito.anyString())).thenReturn(dto);

        this.mockMvc.perform(
                MockMvcRequestBuilders.put("/users/"+userId)
                        .header(HttpHeaders.AUTHORIZATION,"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJHdWRkaSIsImlhdCI6MTY5MDU4NTIxNSwiZXhwIjoxNjkwNjAzMjE1fQ.F4mDYEiBDbGXzY7PNhfgR-8murQt375ofLx-GhjWgdT5iS3MyVj-lJC-rCPzS4yYHgvS2HQSB2EKu19Fjb4cvA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObectToJsonString(user))
                        .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists());

    }



    private String convertObectToJsonString(Object user) {

        try
        {
            return new ObjectMapper().writeValueAsString(user);


        }catch (Exception e)
        {

            e.printStackTrace();
            return null;

        }


    }

    @Test
    public void getAllUsersTest() throws Exception {

        UserDto object1 = UserDto.builder().name("Amrit").email("amrit@gmail.com").password("abcd").about("Testing").build();
        UserDto object2 = UserDto.builder().name("Amar").email("amrit@gmail.com").password("abcd").about("Testing").build();
        UserDto object3 = UserDto.builder().name("Amrita").email("amrit@gmail.com").password("abcd").about("Testing").build();
        UserDto object4 = UserDto.builder().name("Aaarof").email("amrit@gmail.com").password("abcd").about("Testing").build();
        UserDto object5 = UserDto.builder().name("Aashids").email("amrit@gmail.com").password("abcd").about("Testing").build();



        PageableResponse<UserDto> pageableResponse = new PageableResponse<>();
        pageableResponse.setContent(Arrays.asList(
                object1,object2,object3,object4,object5
        ));
        pageableResponse.setLastPage(false);
        pageableResponse.setPageSize(10);
        pageableResponse.setPageNumber(100);
        pageableResponse.setTotalElements(11234);


        Mockito.when(userService.getAllUser(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(pageableResponse);


        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk());

    }

}