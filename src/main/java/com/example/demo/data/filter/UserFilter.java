package com.example.demo.data.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "Фильтр пользователей")
public class UserFilter {
    @ApiModelProperty(notes = "Идентификатор", position = 1)
    private List<Integer> id = new ArrayList<>();
    @ApiModelProperty(notes = "Логин", position = 2)
    private String login;
}
