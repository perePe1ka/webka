package ru.web.laba_web2.services.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.repositories.IRolesRepository;
import ru.web.laba_web2.services.IRolesService;
import ru.web.laba_web2.services.dtos.RolesDto;
import ru.web.laba_web2.utils.ValidationUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class RolesServiceImpl implements IRolesService<String> {
    private final ModelMapper modelMapper;
    private IRolesRepository rolesRepository;
    private ValidationUtil validationUtil;


    @Autowired
    public RolesServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setRolesRepository(IRolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    @CacheEvict(cacheNames = "roles", allEntries = true)
    public void register(RolesDto rolesDto) {
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
    public Optional<RolesDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(rolesRepository.findByUuid(uuid), RolesDto.class));
    }

    @Override
    @Cacheable("roles")
    public List<RolesDto> getAll() {
        return rolesRepository.findAll().stream().map(roles -> modelMapper.map(roles, RolesDto.class)).collect(Collectors.toList());
    }
}
