package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.laba_web2.models.Brand;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
