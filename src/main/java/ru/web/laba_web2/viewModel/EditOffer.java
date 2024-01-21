package ru.web.laba_web2.viewModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.web.laba_web2.constants.Engine;
import ru.web.laba_web2.constants.Transmission;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.utils.UniqueOffer;

import java.time.LocalDateTime;

public class EditOffer extends BaseViewModel {
    @UniqueOffer
    private String description;

    private Engine engine;

    private String imageUrl;

    private int milleage;

    private int price;

    private Transmission transmission;

    private int year;

    private String model;

    private User seller;

    private LocalDateTime created;

    @NotEmpty(message = "Описание не может быть пустым")
    @Size(min = 5, max = 250, message = "Описание должно содержать минимум 5 символов")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Выберите двигатель!")
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @NotEmpty(message = "Укажите ссылку на фотографию!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull(message = "Пробег не может быть пустым")
    @Min(value = 1, message = "Пробег не может быть отрицательным")
    public int getMilleage() {
        return milleage;
    }

    public void setMilleage(int milleage) {
        this.milleage = milleage;
    }

    @NotNull(message = "Цена не может быть пустой")
    @Min(value = 1, message = "Цена не может быть отрицательной")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NotNull(message = "Выберите вид трансмиссии!")
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @NotNull(message = "Год не может быть пустым")
    @Min(value = 1, message = "Год не может быть отрицательным")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @NotEmpty(message = "Выберите модель")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return description;
    }
}
