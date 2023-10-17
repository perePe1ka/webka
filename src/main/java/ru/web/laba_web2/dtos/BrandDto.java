package ru.web.laba_web2.dtos;

import java.sql.Date;

public class BrandDto {
    private int id;
    private String name; //наименование бренда

    private java.sql.Date created; //дата и время
    private java.sql.Date modified; //дата и время


    public BrandDto(String name, Date created, Date modified) {
        this.name = name;
        this.created = created;
        this.modified = modified;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Model { id=" + id + ", name=" + name + ", created=" + created + ", modified=" + modified + " }";
    }
}
