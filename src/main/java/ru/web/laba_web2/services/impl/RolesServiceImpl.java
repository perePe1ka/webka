package ru.web.laba_web2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.services.dtos.RolesDto;
import ru.web.laba_web2.models.Roles;
import ru.web.laba_web2.repositories.RolesRepository;
import ru.web.laba_web2.services.RolesService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class RolesServiceImpl implements RolesService<String> {
    private final RolesRepository rolesRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository, ModelMapper modelMapper) {
        this.rolesRepository = rolesRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Roles create(RolesDto rolesDto) {
        Roles roles = modelMapper.map(rolesDto, Roles.class);
        return rolesRepository.saveAndFlush(roles);
    }

    @Override
    public void delete(RolesDto rolesDto) {
        rolesRepository.deleteByUuid(rolesDto.getUuid());
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

}
