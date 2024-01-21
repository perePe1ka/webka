package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.laba_web2.models.Model;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IModelRepository extends JpaRepository<Model, UUID> {
    Optional<Model> findByUuid(UUID uuid);

    Model findModelByName(String name);

    Optional<Model> findByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
