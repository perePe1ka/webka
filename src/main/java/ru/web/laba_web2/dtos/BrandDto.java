package ru.web.laba_web2.dtos;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import ru.web.laba_web2.models.Model;

import java.sql.Date;
import java.util.Set;

public class BrandDto {
    private int id;
    private String name; //наименование бренда

    private java.sql.Date created; //дата и время
    private java.sql.Date modified; //дата и время
    private Model model;

    public BrandDto(String name, Date created, Date modified, Model model) {
        this.name = name;
        this.created = created;
        this.modified = modified;
        this.model = model;
    }

    public BrandDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
