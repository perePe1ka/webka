package ru.web.laba_web2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Roles extends BaseEntity {
    private enum role{
        USER, ADMIN
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<User> user;

    protected Roles() {
    }

    public Roles(Set<User> user) {
        user = new HashSet<User>();
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
