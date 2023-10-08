package ru.web.laba_web2.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.sql.Date;
import java.util.HashSet;

@Entity
@Table(name = "model")
public class Model extends BaseEntity{
    private String name; //имя модели

    private enum category{
        CAR, BUS, TRUCK, MOTORCYCLE
    }

    private String imageUrl; //ссылка на юрл

    private int startYear;

    private int endYear;

    private java.sql.Date created;

    private java.sql.Date modified;


    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable=false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Brand brand;

    public Model(String name, String imageUrl, int startYear, int endYear, Date created, Date modified, Brand brand) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
        this.brand = brand;
    }

    protected Model() {

    }
    @Column(name = "name", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "imageUrl", length = 50, nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Column(name = "startYear", length = 50, nullable = false)
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    @Column(name = "endYear", length = 50, nullable = false)
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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
    @Column(name = "brand", length = 50, nullable = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
