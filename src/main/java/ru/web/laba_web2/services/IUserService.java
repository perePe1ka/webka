package ru.web.laba_web2.services;

import ru.web.laba_web2.models.User;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.viewModel.EditUserViewModel;
import ru.web.laba_web2.viewModel.UserRegistrationViewModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService<String> {
    void register(UserRegistrationViewModel registrationDTO);

    void deleteByUsername(String username);

    Optional<UserDto> findByUuid(UUID uuid);

    User getUser(String username);

    List<UserDto> getAll();

    EditUserViewModel update(EditUserViewModel editUser);

    User findByUsername(String userName);
}
