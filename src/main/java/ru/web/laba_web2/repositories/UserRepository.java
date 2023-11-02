package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.models.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUuid(String uuid);

    void deleteByUuid(String uuid);

    List<User> findAllByRoleRole(Role role);
}
