package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.Offer;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findAllByModelName(String modelName);
    List<Offer> findAllByUserName(String userName);
}
