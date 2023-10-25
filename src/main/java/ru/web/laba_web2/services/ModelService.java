package ru.web.laba_web2.services;


import ru.web.laba_web2.dtos.BrandDto;
import ru.web.laba_web2.dtos.ModelDto;
import ru.web.laba_web2.dtos.OfferDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ModelService<UUID> {
    List<ModelDto> getModelsByBrand(String brandName);
    ModelDto register(ModelDto modelDto);

    void delete(ModelDto modelDto);

    void deleteByUuid(UUID uuid);

    void transfer(ModelDto modelDto, BrandDto brandDto);

    Optional<ModelDto> findByUuid(UUID uuid);

    List<ModelDto> getAll();

}
