package ru.web.laba_web2.services;

import ru.web.laba_web2.viewModel.AddOfferViewModel;
import ru.web.laba_web2.viewModel.DetailOfferViewModel;
import ru.web.laba_web2.viewModel.EditOfferViewModel;
import ru.web.laba_web2.viewModel.ShowOfferViewModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOfferService<String> {
    void register(AddOfferViewModel addOfferViewModel);

    void deleteByOfferDescription(String description);

    Optional<EditOfferViewModel> findByUuid(UUID uuid);

    DetailOfferViewModel getAll(String offerDescription);

    List<ShowOfferViewModel> allOffers();

    void editOffer(EditOfferViewModel editOffer);
}