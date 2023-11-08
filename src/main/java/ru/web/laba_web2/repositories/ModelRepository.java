package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.User;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    Optional<Model> findByUuid(String uuid);

    void deleteByUuid(String uuid);

    Model findByName(String name);

}
