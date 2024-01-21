package ru.web.laba_web2.services;

import ru.web.laba_web2.viewModel.AddOfferViewModel;
import ru.web.laba_web2.viewModel.DetailOffer;
import ru.web.laba_web2.viewModel.EditOffer;
import ru.web.laba_web2.viewModel.ShowOffer;

import java.util.List;
import java.util.Optional;

public interface IOfferService<String> {
    void register(AddOfferViewModel addOfferViewModel);

    void deleteByOfferDescription(String description);

    Optional<EditOffer> findByUuid(String uuid);

    DetailOffer getAll(String offerDescription);

    List<ShowOffer> allOffers();

    void editOffer(EditOffer editOffer);
}