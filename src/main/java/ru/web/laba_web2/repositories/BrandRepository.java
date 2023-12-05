package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.models.Model;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    Optional<Brand> findByUuid(String uuid);

    Brand findBrandByName (String name);

    Optional<Brand> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);

}
