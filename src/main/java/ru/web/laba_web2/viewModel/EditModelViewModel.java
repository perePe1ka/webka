package ru.web.laba_web2.viewModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.web.laba_web2.constants.Category;
import ru.web.laba_web2.utils.UniqueBrandName;

import java.time.LocalDateTime;

public class EditModelViewModel extends BaseViewModel {
    @UniqueBrandName
    private String name;

    private Category category;

    private String imageUrl;

    private int startYear;

    private int endYear;
    private String brand;

    private LocalDateTime created;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 40, message = "Название минимум 2 символа")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Выберите категорию!!!")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NotEmpty(message = "Укажите ссылку на фотографию!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull(message = "Год не может быть пустым")
    @Min(value = 1, message = "Год не может быть отрицательным")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @NotNull(message = "Год не может быть пустым")
    @Min(value = 1, message = "Год не может быть отрицательным")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @NotEmpty(message = "Выберите бренд модели")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

}
