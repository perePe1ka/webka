package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.web.laba_web2.models.Model;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    List<Model> findAllByBrandName(String brandName);
}
