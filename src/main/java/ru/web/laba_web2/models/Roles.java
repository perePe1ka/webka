package ru.web.laba_web2.models;

import jakarta.persistence.*;
import ru.web.laba_web2.constants.Role;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles extends BaseEntity {

    private Role role;

    public Roles(Role role) {
        this.role = role;
    }

    public Roles() {
    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return role.toString();
    }
}
