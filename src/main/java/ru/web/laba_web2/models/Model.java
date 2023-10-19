package ru.web.laba_web2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import ru.web.laba_web2.dtos.Category;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;

@Entity
@Table(name = "model")
@AllArgsConstructor
@Data
public class Model extends BaseEntity{
    @Column(name = "name", length = 30, nullable = false)
    private String name; //имя модели
    @Column(name = "category", nullable = false)
    private Category category;
    @Column(name = "imageUrl", length = 60)
    private String imageUrl; //ссылка на юрл
    @Column(name = "startYear", nullable = false)
    private int startYear;
    @Column(name = "endYear", nullable = false)
    private int endYear;
    @Column(name = "created", nullable = false, columnDefinition = "DATE")
    private LocalDate created;
    @Column(name = "modified", nullable = false, columnDefinition = "DATE")
    private LocalDate modified;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_uuid", referencedColumnName = "uuid", nullable=false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Brand brand;

    @ManyToOne(optional = false)
    @JoinColumn(name = "offer_uuid", referencedColumnName = "uuid", nullable=false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Offer offer;

    protected Model() {

    }
}
