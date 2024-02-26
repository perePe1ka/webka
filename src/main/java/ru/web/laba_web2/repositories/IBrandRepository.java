package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.laba_web2.models.Brand;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, UUID> {
    Optional<Brand> findByUuid(UUID uuid);

    Optional<Brand> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
