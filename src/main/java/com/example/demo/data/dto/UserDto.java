package com.example.demo.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Пользователь")
public class UserDto {
    @ApiModelProperty(notes = "Идентификатор", position = 1)
    private Long id;
    @ApiModelProperty(notes = "Логин", position = 2)
    private String login;
    @ApiModelProperty(notes = "Пароль", position = 3, hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ApiModelProperty(notes = "Описание", position = 4)
    private String description;
}
