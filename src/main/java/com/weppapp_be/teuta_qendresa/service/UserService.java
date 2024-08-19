package com.weppapp_be.teuta_qendresa.service;

import com.webapp_be.teuta.dto.UserDto;
import com.webapp_be.teuta.dto.request.UserRequest;
import com.webapp_be.teuta.entity.User;
import com.webapp_be.teuta.exception.ResourceNotFoundException;
import com.webapp_be.teuta.exception.UserAlreadyExists;
import com.webapp_be.teuta.mapper.UserMapper;
import com.webapp_be.teuta.repository.UserRepository;
import com.webapp_be.teuta.util.ReflectionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto create(UserRequest request){
        if(userRepository.existsByUsername(request.username())){
            throw new UserAlreadyExists(String.format(
                    "User with this %s - username already exist",request.username()));
        }
        User user = mapper.toEntity(request);
        setUserPasswordAndRole(request, user);
        User userInDb = userRepository.save(user);
        return mapper.toDto(userInDb);
    }

    public UserDto getById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User with id %s not found", id))
        );
        return mapper.toDto(user);
    }

    public UserDto getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();

        UserDto userDto =  mapper.toDto(loggedUser);
        return userDto;
    }

    public List<UserDto> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public UserDto update(Long id, Map<String, Object> fields){
        User userInDb = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(String.format("User with id %s not found", id)));
        fields.forEach((key, value) ->{
            ReflectionUtil.setFieldValue(userInDb, key, value);
        });
        return mapper.toDto(userRepository.save(userInDb));
    }


    @Transactional
    public void deleteUser(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        userRepository.markAsDeleted(userId, now);
    }

    private void setUserPasswordAndRole(UserRequest request, User user) {
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
    }
}
