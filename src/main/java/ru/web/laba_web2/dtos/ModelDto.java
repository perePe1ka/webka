package ru.web.laba_web2.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import ru.web.laba_web2.models.Brand;

import java.sql.Date;

public class ModelDto {
    private int id;
    private String name; //имя модели

    private enum category{
        CAR, BUS, TRUCK, MOTORCYCLE
    }

    private String imageUrl; //ссылка на юрл

    private int startYear;

    private int endYear;

    private java.sql.Date created;

    private java.sql.Date modified;
    private Brand brand;

    public ModelDto(String name, String imageUrl, int startYear, int endYear, Date created, Date modified, Brand brand) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.created = created;
        this.modified = modified;
        this.brand = brand;
    }

    public ModelDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
