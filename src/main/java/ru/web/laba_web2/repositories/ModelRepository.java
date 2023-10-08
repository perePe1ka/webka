package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.web.laba_web2.models.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
