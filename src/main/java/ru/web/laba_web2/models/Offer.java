package ru.web.laba_web2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import ru.web.laba_web2.constants.Engine;
import ru.web.laba_web2.constants.Transmission;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "offer")
@AllArgsConstructor
@Data
public class Offer extends BaseEntity{
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    @Column(name = "engine", nullable = false)
    private Engine engine;
    @Column(name = "imageUrl", length = 255)
    private String imageUrl;
    @Column(name = "milleage", nullable = false)
    private int milleage;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "transmission", nullable = false)
    private Transmission transmission;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "created", columnDefinition = "DATE")
    private LocalDate created;
    @Column(name = "modified", columnDefinition = "DATE")
    private LocalDate modified;

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_uuid", referencedColumnName = "uuid", nullable=false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Model model;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", nullable=false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    protected Offer() {

    }

}
