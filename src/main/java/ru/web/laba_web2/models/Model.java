package ru.web.laba_web2.models;

import jakarta.persistence.*;
import ru.web.laba_web2.constants.Category;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "model")
public class Model extends TimeClass{

    public Model(LocalDateTime created,
                 LocalDateTime modified,
                 String name,
                 Category category,
                 String imageURL,
                 Integer startYear,
                 Integer endYear,
                 Brand brand) {
        super(created, modified);
        this.name = name;
        this.category = category;
        this.imageUrl = imageURL;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
    }


    private String name; //имя модели
    @Enumerated(EnumType.STRING)
    private Category category;

    private String imageUrl; //ссылка на юрл

    private int startYear;

    private int endYear;

    private Brand brand;

    private Set<Offer> offers;

    public Model() {

    }


    @Column(name = "name", length = 255, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "category", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    @Column(name = "imageUrl", length = 512)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Column(name = "startYear", nullable = false)
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }
    @Column(name = "endYear", nullable = false)
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
    @ManyToOne
    @JoinColumn(name = "brand_uuid", referencedColumnName = "uuid", nullable = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "model", cascade = CascadeType.REMOVE)
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return name;
    }
}
