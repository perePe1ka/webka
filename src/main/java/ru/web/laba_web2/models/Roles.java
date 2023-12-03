package ru.web.laba_web2.models;

import jakarta.persistence.*;
import ru.web.laba_web2.constants.Role;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private Role role;

    private Set<User> user;

    public Roles() {
    }
    @Column(name = "role", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.REMOVE)
    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
