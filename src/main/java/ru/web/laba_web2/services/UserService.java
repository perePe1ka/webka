package ru.web.laba_web2.services;

import org.springframework.cache.annotation.CacheEvict;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.viewModel.EditUser;
import ru.web.laba_web2.viewModel.UserRegistration;

import java.util.List;
import java.util.Optional;

public interface UserService<String> {
    void register(UserRegistration registrationDTO);

    void deleteByUsername(String username);

    Optional<UserDto> findByUuid(String uuid);

    User getUser(String username);

    List<UserDto> getAll();


    EditUser update(EditUser editUser);

    User findByUsername(String userName);
}
