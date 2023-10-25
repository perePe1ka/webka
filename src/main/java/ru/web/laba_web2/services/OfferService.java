package ru.web.laba_web2.services;

import ru.web.laba_web2.dtos.ModelDto;
import ru.web.laba_web2.dtos.OfferDto;
import ru.web.laba_web2.dtos.UserDto;
import java.util.List;
import java.util.Optional;
public interface OfferService<UUID> {
    OfferDto register(OfferDto offerDto);

    void delete(OfferDto offerDto);

    void deleteByUuid(UUID uuid);

    void transfer(OfferDto offerDto, ModelDto modelDto, UserDto userDto);

    Optional<OfferDto> findByUuid(UUID uuid);

    List<OfferDto> getAll();

    List<OfferDto> findByModelName(String model);
}