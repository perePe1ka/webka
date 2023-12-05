package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.models.Roles;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {
    Optional<Roles> findByUuid(String uuid);
    Roles findByRole(Role role);

    @Modifying
    @Transactional
    void deleteByRole(String role);
}
