//package ru.web.laba_web2.services.dtos;
//
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import org.hibernate.validator.constraints.Length;
//import ru.web.laba_web2.viewModel.BaseViewModel;
//
//
//public class BrandDto extends BaseViewModel {
//    private String name; //наименование бренда
//    public BrandDto() {
//
//    }
//
//    @NotNull
//    @NotEmpty
//    @Length(min = 2, message = "Ошибка, введите минимум 2 символа")
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public BrandDto(String name) {
//        this.name = name;
//    }
//
//}
