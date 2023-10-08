package ru.web.laba_web2.dtos;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import ru.web.laba_web2.models.User;

import java.util.Set;

public class RolesDto {
    private int id;

    private enum role{
        USER, ADMIN
    }

    private User user;

    public RolesDto() {

    }

    public RolesDto(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
