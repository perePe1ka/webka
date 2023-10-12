package ru.web.laba_web2.dtos;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.User;

import java.sql.Date;
import java.util.Set;

public class OfferDto {
    private int id;
    private String description;
    private Engine engine;

    private String imageUrl;

    private int milleage;

    private int price;
    private Transmission transmission;


    private int year;

    private java.sql.Date created;

    private java.sql.Date modified;


    private ModelDto model;

    private UserDto seller;

    public OfferDto() {

    }

    public OfferDto(String description, Engine engine, String imageUrl, int milleage, int price, Transmission transmission, int year, Date created, Date modified, ModelDto model, UserDto seller) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.milleage = milleage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.created = created;
        this.modified = modified;
        this.model = model;
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMilleage() {
        return milleage;
    }

    public void setMilleage(int milleage) {
        this.milleage = milleage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public UserDto getSeller() {
        return seller;
    }

    public void setSeller(UserDto seller) {
        this.seller = seller;
    }
}
