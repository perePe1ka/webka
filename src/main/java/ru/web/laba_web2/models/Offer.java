package ru.web.laba_web2.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "offers") //oneToMany
public class Offer extends BaseEntity{
    private String description;

    private enum engine{
        GASOLINE, DIESEL, ELECTRIC, HYBRID
    }

    private String imageUrl;

    private int milleage;

    private int price;

    private enum transmission {
        MANUAL, AUTOMATIC
    }
    private int year;

    private java.sql.Date created;

    private java.sql.Date modified;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Model> model;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<User> seller;

    @Column(name = "description", length = 50, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "imageUrl", length = 50, nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Column(name = "milleage", length = 50, nullable = false)
    public int getMilleage() {
        return milleage;
    }

    public void setMilleage(int milleage) {
        this.milleage = milleage;
    }
    @Column(name = "price", length = 50, nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    @Column(name = "year", length = 50, nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public Set<Model> getModel() {
        return model;
    }

    public void setModel(Set<Model> model) {
        this.model = model;
    }

    public Set<User> getSeller() {
        return seller;
    }

    public void setSeller(Set<User> seller) {
        this.seller = seller;
    }

    protected Offer() {

    }

    public Offer(String description, String imageUrl, int milleage, int price, int year, Date created, Date modified, Set<Model> model, Set<User> seller) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.milleage = milleage;
        this.price = price;
        this.year = year;
        this.created = created;
        this.modified = modified;
        model = new HashSet<Model>();
        seller = new HashSet<User>();
    }

    @Override
    public String toString() {
        return "Offer { id=" + id + ", description=" + description + ", imageUrl=" + imageUrl + ", milleage=" + milleage + ", price=" + price + ", year=" + year + ", created=" + created + ", modified=" + modified + " }";
    }
}
