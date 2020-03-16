package com.example.demo.mapper;

import com.example.demo.data.dto.UserDto;
import com.example.demo.data.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDtoMapper {

    private final UserRepository repository;

    public UserDto map(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        dto.setDescription(entity.getDescription());

        return dto;
    }

}
