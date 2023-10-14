package ru.web.laba_web2.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import ru.web.laba_web2.dtos.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Roles extends BaseEntity {
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<User> user;

    protected Roles() {
    }

    public Roles(Role role,Set<User> user) {
        this.role = role;
        user = new HashSet<User>();
    }

    @Column(name = "role", length = 50, nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
