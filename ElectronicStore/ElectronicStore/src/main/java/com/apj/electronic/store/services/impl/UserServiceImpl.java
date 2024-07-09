package com.apj.electronic.store.services.impl;

import com.apj.electronic.store.dtos.PageableResponse;
import com.apj.electronic.store.dtos.UserDto;
import com.apj.electronic.store.entities.Role;
import com.apj.electronic.store.entities.User;
import com.apj.electronic.store.exceptions.ResourceNotFoundException;
import com.apj.electronic.store.helper.Helper;
import com.apj.electronic.store.repositories.RoleRepository;
import com.apj.electronic.store.repositories.UserRepository;
import com.apj.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Value("${user.profile.image.path}")
    private String imagePath;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${normal.role.id}")
    private String normalRoleId;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        //generate unique id in string format
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
        //encoding password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        //dto->entity

        User user = dtoToEntity(userDto);

        //fetch role of normal user and set it to user
        Role role = roleRepository.findById(normalRoleId).get();
        user.getRoles().add(role);
        User savedUser = userRepository.save(user);
        UserDto newDto = entityTODto(savedUser);
        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        //save data
        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityTODto(updatedUser);
        return updatedDto ;
    }

    @Override
    public void deleteUser(String userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id"));
        //delete user profile image

        String fullPath = imagePath + user.getImageName();

        try
        {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        }
        catch (NoSuchFileException ex){
            logger.info("User Image Not Found : {}",ex);
            ex.printStackTrace();


        } catch (IOException e) {
            e.printStackTrace();
        }

        //delete user
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir)
    {
        // page number default starts from 0
        Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page = userRepository.findAll(pageable);
        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);
        return response;

    }

    @Override
    public UserDto getUserById(String userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id"));
        UserDto userDto = entityTODto(user);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not found with given email"));
        UserDto userDto = entityTODto(user);

        return userDto;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityTODto(user)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public Optional<User> findUserByEmailOptional(String email)
    {
        return userRepository.findByEmail(email);
    }


    private UserDto entityTODto(User savedUser) {

//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .email(savedUser.getEmail())
//                .gender(savedUser.getGender())
//                .name(savedUser.getName())
//                .imageName(savedUser.getImageName()).build();




        return mapper.map(savedUser,UserDto.class);
    }


    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();

        return mapper.map(userDto,User.class);
    }


}
