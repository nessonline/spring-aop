package com.example.demo.rest;

import com.example.demo.config.SwaggerConfig;
import com.example.demo.data.dto.UserDto;
import com.example.demo.data.filter.UserFilter;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/demo/common/user")
@Api(tags = SwaggerConfig.API_USER_CONTROLLER_NAME)
@RequiredArgsConstructor
public class ApiUserController {

    private final UserService service;

    @GetMapping
    @ApiOperation("Возвращает список пользователей")
    public List<UserDto> findAll() {
        return service.findAll();
    }

    @PostMapping("/filter")
    @ApiOperation("Возвращает список пользователей, удовлетворяющих условию фильтра")
    public List<UserDto> findAll(@RequestBody UserFilter filter) {
        return service.findAll(filter);
    }

    @GetMapping("/count")
    @ApiOperation("Возвращает количество пользователей")
    public long countAll() {
        return service.countAll();
    }

    @PostMapping("/count")
    @ApiOperation("Возвращает количество пользователей, удовлетворяющих условию фильтра")
    public long countAll(@RequestBody UserFilter filter) {
        return service.countAll(filter);
    }

    @GetMapping("/{id}")
    @ApiOperation("Возвращает пользователя по идентификатору")
    public UserDto findOne(@PathVariable("id") Long id) {
        return service.findOne(id);
    }

    @PostMapping
    @ApiOperation("Создание пользователя")
    public long create(@RequestBody UserDto dto) {
        dto.setId(null);
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ApiOperation("Сохранение пользователя по идентификатору")
    public void save(@PathVariable("id") Long id, @RequestBody UserDto dto) {
        dto.setId(id);
        service.save(dto);
    }

    @PutMapping("/{id}/changePassword")
    @ApiOperation("Сохранение пароля пользователя по идентификатору")
    public void changePassword(@PathVariable("id") Long id, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword) {
        service.changePassword(id, password, newPassword);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление пользователя по идентификатору")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
