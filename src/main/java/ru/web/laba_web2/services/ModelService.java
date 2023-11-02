package ru.web.laba_web2.services;

import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.dtos.ModelDto;

import java.util.List;
import java.util.Optional;

public interface ModelService<String> {
    ModelDto register(ModelDto modelDto);

//    Model create(ModelDto modelDto);

    void deleteByUuid(String uuid);

    void transfer(ModelDto modelDto, BrandDto brandDto);

    Optional<ModelDto> findByUuid(String uuid);

    List<ModelDto> getAll();

    void editModel(ModelDto modelDto);

    List<ModelDto> getModelsSortedByYear();
}
