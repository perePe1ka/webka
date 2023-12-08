package ru.web.laba_web2.viewModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.web.laba_web2.constants.Category;
import ru.web.laba_web2.utils.UniqueBrandName;

public class AddModelViewModel {
    @UniqueBrandName
    private String name; //имя модели

    private Category category;

    private String imageUrl; //ссылка на юрл

    private int startYear;

    private int endYear;
    private String brand;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 25, message = "Название минимум 2 символа")
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
}
