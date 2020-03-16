package com.example.demo.service;

import com.example.demo.data.dto.UserDto;
import com.example.demo.data.entity.User;
import com.example.demo.data.filter.UserFilter;
import com.example.demo.data.filter.UserFilterSpecification;
import com.example.demo.mapper.MapperFactory;
import com.example.demo.mapper.UserDtoMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final MapperFactory mapperFactory;
    private UserDtoMapper dtoMapper;
    private UserMapper mapper;

    @PostConstruct
    private void init() {
        this.dtoMapper = mapperFactory.createUserDtoMapper();
        this.mapper = mapperFactory.createUserMapper();
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return repository.findAll(UserFilterSpecification.getSort()).stream().map(dtoMapper::map).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll(UserFilter filter) {
        UserFilterSpecification specification = new UserFilterSpecification(filter);
        return repository.findAll(specification, specification.getSort()).stream().map(dtoMapper::map).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long countAll() {
        return repository.count();
    }

    @Transactional(readOnly = true)
    public long countAll(UserFilter filter) {
        UserFilterSpecification specification = new UserFilterSpecification(filter);
        return repository.count(specification);
    }

    @Transactional
    public long create(UserDto dto) {
        User entity = new User();
        mapper.map(entity, dto);
        if (Objects.isNull(dto.getPassword())) throw new RuntimeException("Пароль не указан");
        entity.setPassword(dto.getPassword());
        entity = repository.save(entity);
        return entity.getId();
    }

    @Transactional
    public void save(UserDto dto) {
        User entity = findOneUser(dto.getId());
        mapper.map(entity, dto);
        repository.save(entity);
    }

    @Transactional
    public void changePassword(long id, String password, String newPassword) {
        User entity = findOneUser(id);
        if (!Objects.equals(entity.getPassword(), password)) throw new RuntimeException("Пароль не совпадает");
        entity.setPassword(newPassword);
        repository.save(entity);
    }

    @Transactional(readOnly = true)
    public UserDto findOne(long id) {
        return dtoMapper.map(findOneUser(id));
    }

    private User findOneUser(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Пользователь #%s не найден", id)));
    }


    @Transactional
    public void delete(long id) {
        User entity = findOneUser(id);
        repository.delete(entity);
    }
}
