package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.Offer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    List<Offer> findByModelName(String model);
    Optional<Offer> findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}
