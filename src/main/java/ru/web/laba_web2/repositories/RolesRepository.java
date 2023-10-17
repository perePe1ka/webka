package ru.web.laba_web2.repositories;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.laba_web2.models.Roles;
@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
