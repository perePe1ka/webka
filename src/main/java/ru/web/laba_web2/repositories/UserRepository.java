package ru.web.laba_web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.User;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    List<User> findAllRolesName(String rolesName);
//
//    List<User> findAllOfferName(String OfferName);
}
