package ru.web.laba_web2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.models.Offer;
import ru.web.laba_web2.repositories.OfferRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    private OfferRepository offerRepository;

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public int getAverageCarPrice() {
        List<Offer> offers = offerRepository.findAll();
        int carCount = offers.size();

        if (carCount == 0) {
            return 0;
        }

        int totalCarPrice = offers.stream().mapToInt(Offer::getPrice).sum();
        return totalCarPrice / carCount;
    }

    public List<Offer> getTopOffersByMileage() {
        return offerRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Offer::getMilleage))
                .limit(4)
                .collect(Collectors.toList());
    }


}
