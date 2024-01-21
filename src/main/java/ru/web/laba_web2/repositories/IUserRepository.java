package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.laba_web2.models.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUuid(UUID uuid);

    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User findUserByUsername(String username);

    @Modifying
    @Transactional
    void deleteByUsername(String username);
}
