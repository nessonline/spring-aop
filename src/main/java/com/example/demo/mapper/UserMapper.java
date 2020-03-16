package com.example.demo.mapper;

import com.example.demo.data.dto.UserDto;
import com.example.demo.data.entity.User;

public class UserMapper {

    public void map(User entity, UserDto dto) {
        entity.setLogin(dto.getLogin());
        entity.setDescription(dto.getDescription());
    }

}
