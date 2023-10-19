package ru.web.laba_web2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.web.laba_web2.dtos.Engine;
import ru.web.laba_web2.dtos.Transmission;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "offer")
@AllArgsConstructor
@Data
public class Offer extends BaseEntity{
    @Column(name = "description", length = 30, nullable = false)
    private String description;
    @Column(name = "engine", nullable = false)
    private Engine engine;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "milleage", nullable = false)
    private int milleage;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "transmission", nullable = false)
    private Transmission transmission;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "created", nullable = false, columnDefinition = "DATE")
    private LocalDate created;
    @Column(name = "modified", nullable = false, columnDefinition = "DATE")
    private LocalDate modified;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer", cascade = CascadeType.REMOVE)
    private Set<Model> model;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer", cascade = CascadeType.REMOVE)
    private Set<User> seller;

    protected Offer() {

    }

}
