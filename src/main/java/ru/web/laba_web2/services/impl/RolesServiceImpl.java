package ru.web.laba_web2.services.impl;


import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.services.dtos.RolesDto;
import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.repositories.RolesRepository;
import ru.web.laba_web2.services.RolesService;
import ru.web.laba_web2.utils.ValidationUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RolesServiceImpl implements RolesService<String> {
    private final ModelMapper modelMapper;
    private RolesRepository rolesRepository;
    private ValidationUtil validationUtil;

    @Autowired
    public RolesServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void register(String role) {
        RolesDto rolesDto = new RolesDto();
        rolesDto.setRole(Role.valueOf(role));

        if (!this.validationUtil.isValid(rolesDto)) {
            this.validationUtil
                    .violations(rolesDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                this.rolesRepository
                        .saveAndFlush(this.modelMapper.map(rolesDto, Roles.class));
            } catch (Exception e) {
                System.out.println("Что то пошло не так");
            }
        }
    }

    @Override
    public void deleteByUuid(String uuid) {
        rolesRepository.deleteByUuid(uuid);
    }

    @Override
    public Optional<RolesDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(rolesRepository.findByUuid(uuid), RolesDto.class));
    }

    @Override
    public List<RolesDto> getAll() {
        return rolesRepository.findAll().stream().map(roles -> modelMapper.map(roles, RolesDto.class)).collect(Collectors.toList());
    }

    @Override
    public void editRoles(RolesDto rolesDto) {
        Roles roles = modelMapper.map(rolesDto, Roles.class);
        rolesRepository.saveAndFlush(roles);
    }

    @Override
    public Roles findByRole(String role) {
        return this.rolesRepository.findByRole(role);
    }

}
