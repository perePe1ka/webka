package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.laba_web2.models.Model;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    Optional<Model> findByUuid(String uuid);

    Model findModelByName(String name);

    Optional<Model> findByName(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Model m WHERE m.name = :name")
    void deleteModelByName(String name);

}
