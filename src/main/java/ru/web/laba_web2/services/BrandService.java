package ru.web.laba_web2.services;

import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.models.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService<String>{
    Brand create(BrandDto brandDto);

    void delete(BrandDto brandDto);

    void deleteByUuid(String uuid);

    Optional<BrandDto> findByUuid(String uuid);

    List<BrandDto> getAll();
}
