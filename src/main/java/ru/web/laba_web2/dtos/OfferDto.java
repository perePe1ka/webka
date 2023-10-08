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


    private Model model;

    private User seller;

    public OfferDto() {

    }
    public OfferDto(String description, String imageUrl, int milleage, int price, int year, Date created, Date modified, Model model, User seller) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.milleage = milleage;
        this.price = price;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
