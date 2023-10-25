package ru.web.laba_web2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.web.laba_web2.constants.Role;

import java.util.Set;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@Data
public class Roles extends BaseEntity {
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<User> user;

    protected Roles() {
    }
}
