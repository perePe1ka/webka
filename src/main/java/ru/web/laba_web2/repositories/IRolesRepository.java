package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.models.Roles;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, UUID> {

    Optional<Roles> findByUuid(UUID uuid);

    Optional<Roles> findRolesByRole(Role role);
}
