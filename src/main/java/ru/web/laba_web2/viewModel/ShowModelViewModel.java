package ru.web.laba_web2.viewModel;

import ru.web.laba_web2.constants.Category;

public class ShowModelViewModel {
    private String name;
    private Category category;

    private String brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return name;
    }
}
