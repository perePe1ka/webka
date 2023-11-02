package ru.web.laba_web2.services;

import ru.web.laba_web2.services.dtos.BrandDto;

import java.util.List;
import java.util.Optional;

public interface BrandService<String>{
    BrandDto register(BrandDto brandDto);

//    Brand create(BrandDto brandDto);

    void deleteByUuid(String uuid);

    Optional<BrandDto> findByUuid(String uuid);

    List<BrandDto> getAll();

    void editBrand(BrandDto brandDto);
}
