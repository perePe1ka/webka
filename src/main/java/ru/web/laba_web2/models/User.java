package ru.web.laba_web2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Data
public class User extends BaseEntity{
    @Column(name = "username", length = 20, nullable = false)
    private String username;
    @Column(name = "password", length = 20, nullable = false)
    private String password;
    @Column(name = "firstName", length = 15, nullable = false)
    private String firstName;
    @Column(name = "lastName", length = 15, nullable = false)
    private String lastName;
    @Column(name = "isActive", nullable = false)
    private boolean isActive;
    @ManyToOne(optional = false)
    @JoinColumn(name = "roles_uuid", referencedColumnName = "uuid", nullable=false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Roles role;
    @Column(name = "imageUrl", length = 255)
    private String imageUrl;
    @Column(name = "created", columnDefinition = "DATE")
    private LocalDate created;
    @Column(name = "modified", columnDefinition = "DATE")
    private LocalDate modified;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Offer> offers;


    protected User () {
    }
}
