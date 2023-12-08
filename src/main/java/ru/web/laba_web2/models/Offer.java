package ru.web.laba_web2.models;

import jakarta.persistence.*;
import ru.web.laba_web2.constants.Engine;
import ru.web.laba_web2.constants.Transmission;

import java.time.LocalDateTime;


@Entity
@Table(name = "offer")
public class Offer extends TimeClass {

    private String description;
    @Enumerated(EnumType.STRING)
    private Engine engine;

    private String imageUrl;

    private int milleage;

    private int price;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private int year;

    private Model model;


    private User seller;

    public Offer(LocalDateTime created,
                 LocalDateTime modified,
                 String description,
                 Engine engine,
                 String imageURL,
                 Integer milleage,
                 Integer price,
                 Transmission transmission,
                 Integer year,
                 Model model,
                 User seller) {
        super(created, modified);
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageURL;
        this.milleage = milleage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.model = model;
        this.seller = seller;
    }

    public Offer() {

    }


    @Column(name = "description", length = 255, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "engine", nullable = false)
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Column(name = "imageUrl", length = 255)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "milleage", nullable = false)
    public int getMilleage() {
        return milleage;
    }

    public void setMilleage(int milleage) {
        this.milleage = milleage;
    }

    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "transmission", nullable = false)
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @ManyToOne
    @JoinColumn(name = "model_uuid", referencedColumnName = "uuid", nullable = false)
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", nullable = false)
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
