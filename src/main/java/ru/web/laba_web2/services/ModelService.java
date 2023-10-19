package ru.web.laba_web2.services;


import ru.web.laba_web2.dtos.BrandDto;
import ru.web.laba_web2.dtos.ModelDto;
import ru.web.laba_web2.dtos.OfferDto;
import java.util.List;
import java.util.Optional;


public interface ModelService<Long> {

    ModelDto register(ModelDto modelDto);

    void delete(ModelDto modelDto);

    void deleteByUUID(Long uuid);

    void transfer(ModelDto modelDto, BrandDto brandDto, OfferDto offerDto);

    Optional<ModelDto> findByUUID(Long uuid);

    List<ModelDto> getAll();

    List<ModelDto> findByModel(String brand);
}
