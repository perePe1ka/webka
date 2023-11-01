package ru.web.laba_web2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.web.laba_web2.constants.Category;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "model")
@EntityListeners(AuditingEntityListener.class)
public class Model extends BaseEntity{

    private String name; //имя модели

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_uuid", referencedColumnName = "uuid", nullable=false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
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
}
