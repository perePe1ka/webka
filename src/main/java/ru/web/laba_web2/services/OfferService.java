package ru.web.laba_web2.services;

import ru.web.laba_web2.dtos.ModelDto;
import ru.web.laba_web2.dtos.OfferDto;
import ru.web.laba_web2.dtos.RolesDto;
import ru.web.laba_web2.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface OfferService<ID>{
    OfferDto register(OfferDto offerDto);

    void expel(OfferDto offerDto);

    void expel(ID id);

    void transfer(OfferDto offerDto, UserDto userDto, ModelDto modelDto);

    Optional<OfferDto> findById(ID id);

    List<OfferDto> getAll();

    List<OfferDto> findByModel(String model);

    List<OfferDto> findByUser(String user);
}
