package ru.web.laba_web2.viewModel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;
import ru.web.laba_web2.utils.UniqueBrandName;

import java.time.LocalDateTime;

public class EditBrand extends BaseViewModel{
    @UniqueBrandName
    private String name;

    private LocalDateTime created;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 40, message = "Название минимум 2 символа")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
