package com.example.demo.mapper;

import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperFactory {

    private final UserRepository userRepository;

    public UserDtoMapper createUserDtoMapper() {
        return new UserDtoMapper(userRepository);
    }

    public UserMapper createUserMapper() {
        return new UserMapper();
    }
}
