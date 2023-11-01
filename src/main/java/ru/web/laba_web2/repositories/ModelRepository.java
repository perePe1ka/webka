package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.laba_web2.models.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    Optional<Model> findByUuid(String uuid);

    void deleteByUuid(String uuid);


}
