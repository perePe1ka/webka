package ru.web.laba_web2.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;


import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "brand")
@AllArgsConstructor
@Data
public class Brand extends BaseEntity{
    @Column(name = "name", length = 30, nullable = false)
    private String name; //наименование бренда
    @Column(name = "created", nullable = false, columnDefinition = "DATE")
    private LocalDate created; //дата и время
    @Column(name = "modified", nullable = false, columnDefinition = "DATE")
    private LocalDate modified; //дата и время

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand", cascade = CascadeType.REMOVE)
    private Set<Model> model;

    protected Brand() {

    }
}
