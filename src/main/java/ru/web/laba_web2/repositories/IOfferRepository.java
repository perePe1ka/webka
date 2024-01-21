package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.laba_web2.models.Offer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IOfferRepository extends JpaRepository<Offer, UUID> {
    Optional<Offer> findByUuid(UUID uuid);

    Optional<Offer> findByDescription(String description);

    @Modifying
    @Transactional
    void deleteByDescription(String description);
}
