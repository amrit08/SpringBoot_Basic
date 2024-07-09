package com.apj.electronic.store.services;
import com.apj.electronic.store.dtos.PageableResponse;
import com.apj.electronic.store.dtos.UserDto;
import com.apj.electronic.store.entities.Role;
import com.apj.electronic.store.entities.User;
import com.apj.electronic.store.repositories.RoleRepository;
import com.apj.electronic.store.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    User user;

    Role role;

    String roleId;

    @Autowired
    private ModelMapper mapper;

    @BeforeEach
    public void init(){

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


        roleId = "abcd";
    }


    @Test
    public void createUserTest()
    {

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        Mockito.when(roleRepository.findById(Mockito.anyString())).thenReturn(Optional.of(role));

        UserDto user1 = userService.createUser(mapper.map(user, UserDto.class));

        System.out.println(user1.getName());

        Assertions.assertNotNull(user1);

        Assertions.assertEquals("Amrit Jaiswal",user1.getName());

    }

    @Test
    public void updateUserTest()
    {
        String userId="sddfshfdaa";
        UserDto userDto=UserDto.builder()
                .name("Amrit Prasad Jaiswal")
                .about("This is testing updateUser method")
                .gender("Male")
                .imageName("kdjl.png")
                .build();

        Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        UserDto updatedUser = userService.updateUser(userDto, userId);
        System.out.println(updatedUser.getName());
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(userDto.getName(),updatedUser.getName(),"Name is not valid");

    }

    @Test
    public void deleteUserTest()
    {
        String userId="kjadsanfjdasahsd";

        Mockito.when(userRepository.findById("kjadsanfjdasahsd")).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        Mockito.verify(userRepository ,Mockito.times(1)).delete(user);


    }

    @Test
    public void getAllUsersTest()
    {
       User user1 = User.builder()
                .name("Amar")
                .email("amrit@gmail.com")
                .about("This is testing crate method")
                .gender("Male")
                .imageName("anb.png")
                .password("amrit")
                .roles(Set.of(role))
                .build();

       User user2 = User.builder()
                .name("Amrita")
                .email("amrit@gmail.com")
                .about("This is testing crate method")
                .gender("Male")
                .imageName("anb.png")
                .password("amrit")
                .roles(Set.of(role))
                .build();


        List<User> userList= Arrays.asList(user,user1,user2);
        Page<User> page = new PageImpl<>(userList);
        Mockito.when(userRepository.findAll((Pageable) Mockito.any())).thenReturn(page);
        PageableResponse<UserDto> allUser = userService.getAllUser(1,2,"name","asc");
        Assertions.assertEquals(3,allUser.getContent().size());

    }


    @Test
    public void getUserByIdTest(){
        String userId = "mnxcjdlssjfoiw";
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUserById(userId);
        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(user.getName(),userDto.getName(),"Name NOT matched");
    }

    @Test
    public void getUserByEmailTest()
    {

        String emailId="akmcnx,nsJIWW";
        Mockito.when(userRepository.findByEmail(emailId)).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUserByEmail(emailId);
        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(user.getEmail(),userDto.getEmail(),"Emial not matched ");

    }

    @Test
    public void searchUserTest()
    {

        User user1 = User.builder()
                .name("Amar prasad")
                .email("amrit@gmail.com")
                .about("This is testing crate method")
                .gender("Male")
                .imageName("anb.png")
                .password("amrit")
                .roles(Set.of(role))
                .build();

        User user2 = User.builder()
                .name("Amrita prasad")
                .email("amrit@gmail.com")
                .about("This is testing crate method")
                .gender("Male")
                .imageName("anb.png")
                .password("amrit")
                .roles(Set.of(role))
                .build();

        User user3= User.builder()
                .name("Aman prasad")
                .email("amrit@gmail.com")
                .about("This is testing crate method")
                .gender("Male")
                .imageName("anb.png")
                .password("amrit")
                .roles(Set.of(role))
                .build();

        String keywords="prasad";
        Mockito.when(userRepository.findByNameContaining(keywords)).thenReturn(Arrays.asList(user,user1,user2,user3));

        List<UserDto> userDtos = userService.searchUser(keywords);

        Assertions.assertEquals(4,userDtos.size(),"size not matched");


    }


    @Test
    public void findUserByEmailOptionalTest()
    {
        String email="kasjawuoqe";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Optional<User> userByEmailOptional = userService.findUserByEmailOptional(email);
        Assertions.assertTrue(userByEmailOptional.isPresent());
        User user1 = userByEmailOptional.get();
        Assertions.assertEquals(user.getEmail(),user1.getEmail(),"Email not matched");


    }



}
