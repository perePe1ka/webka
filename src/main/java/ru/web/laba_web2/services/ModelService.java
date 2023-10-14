package ru.web.laba_web2.services;


import ru.web.laba_web2.dtos.BrandDto;
import ru.web.laba_web2.dtos.ModelDto;
import ru.web.laba_web2.dtos.OfferDto;

import java.util.List;
import java.util.Optional;

public interface ModelService<ID> {

    ModelDto register(ModelDto modelDto);

    void expel(ModelDto modelDto);

    void expel(ID id);



    void transfer(ModelDto modelDto, BrandDto brandDto, OfferDto offerDto);

    Optional<ModelDto> findById(ID id);

    List<ModelDto> getAll();

    List<ModelDto> findByModel(String brand);

}
