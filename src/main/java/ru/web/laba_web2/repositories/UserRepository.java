package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUuid(String uuid);

    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);

    User findUserByUsername(String username);

    @Modifying
    @Transactional
    void deleteByUsername(String username);
}
