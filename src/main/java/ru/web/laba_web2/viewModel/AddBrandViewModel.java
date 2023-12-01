package ru.web.laba_web2.viewModel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.web.laba_web2.utils.UniqueBrandName;

public class AddBrandViewModel {
    @UniqueBrandName
    private String name;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 10, message = "Название минимум 2 символа")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
