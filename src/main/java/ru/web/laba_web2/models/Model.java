package ru.web.laba_web2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import ru.web.laba_web2.constants.Category;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "model")
@AllArgsConstructor
@Data
public class Model extends BaseEntity{
    @Column(name = "name", length = 255, nullable = false)
    private String name; //имя модели
    @Column(name = "category", nullable = false)
    private Category category;
    @Column(name = "imageUrl", length = 512)
    private String imageUrl; //ссылка на юрл
    @Column(name = "startYear", nullable = false)
    private int startYear;
    @Column(name = "endYear", nullable = false)
    private int endYear;
    @Column(name = "created", columnDefinition = "DATE")
    private LocalDate created;
    @Column(name = "modified", columnDefinition = "DATE")
    private LocalDate modified;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_uuid", referencedColumnName = "uuid", nullable=false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Brand brand;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model", cascade = CascadeType.REMOVE)
    private Set<Offer> offers;

    protected Model() {

    }
}
