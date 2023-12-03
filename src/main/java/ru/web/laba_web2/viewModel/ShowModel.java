package ru.web.laba_web2.viewModel;

import ru.web.laba_web2.constants.Category;

public class ShowModel {
    private String name;
    private Category category;

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

    @Override
    public String toString() {
        return name;
    }
}
