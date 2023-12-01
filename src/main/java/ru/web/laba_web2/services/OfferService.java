package ru.web.laba_web2.services;

import ru.web.laba_web2.services.dtos.ModelDto;
import ru.web.laba_web2.services.dtos.OfferDto;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.viewModel.ShowOffer;

import java.util.List;
import java.util.Optional;
public interface OfferService<String> {
    void register(OfferDto offerDto);

    void deleteByUuid(String uuid);

    void transfer(OfferDto offerDto, ModelDto modelDto, UserDto userDto);

    Optional<OfferDto> findByUuid(String uuid);

    List<UserDto> getAllAdmins();

    OfferDto getAll(java.lang.String offerDescription);

    List<ShowOffer> allOffers();

    void editOffer(OfferDto offerDto);

    int calculateTotalPrice();
}