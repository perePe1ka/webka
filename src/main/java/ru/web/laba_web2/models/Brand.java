package ru.web.laba_web2.models;

import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(name = "brand")
public class Brand extends BaseEntity{

    private String name; //наименование бренда


    private Set<Model> model;

    public Brand() {

    }
    @Column(name = "name", length = 255, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "brand", cascade = CascadeType.REMOVE)
    public Set<Model> getModel() {
        return model;
    }

    public void setModel(Set<Model> model) {
        this.model = model;
    }
}
