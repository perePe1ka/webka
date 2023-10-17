package ru.web.laba_web2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brand")
public class Brand extends BaseEntity{
    private String name; //наименование бренда
    private java.sql.Date created; //дата и время
    private java.sql.Date modified; //дата и время

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Model> model;

    protected Brand() {

    }
    public Brand(String name, Date created, Date modified) {
        this.name = name;
        this.created = created;
        this.modified = modified;
        model = new HashSet<Model>();
    }
    @Column(name = "name", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "created", length = 50, nullable = false)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    @Column(name = "modified", length = 50, nullable = false)
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Group { id=" + id + ", name=" + name + ", created=" + created + ", modified= " + modified + " }";
    }
}
