//package ru.web.laba_web2.services.dtos;
//
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import org.hibernate.validator.constraints.Length;
//import ru.web.laba_web2.constants.Category;
//import ru.web.laba_web2.viewModel.BaseViewModel;
//
//
//public class ModelDto extends BaseViewModel {
//    private String name; //имя модели
//
//    private Category category;
//
//    private String imageUrl; //ссылка на юрл
//
//    private int startYear;
//
//    private int endYear;
//    private String brand;
//
//
//    public ModelDto() {
//
//    }
//
//    public ModelDto(String name, Category category, String imageUrl, int startYear, int endYear, String brand) {
//        this.name = name;
//        this.category = category;
//        this.imageUrl = imageUrl;
//        this.startYear = startYear;
//        this.endYear = endYear;
//        this.brand = brand;
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
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public int getStartYear() {
//        return startYear;
//    }
//
//    public void setStartYear(int startYear) {
//        this.startYear = startYear;
//    }
//
//    public int getEndYear() {
//        return endYear;
//    }
//
//    public void setEndYear(int endYear) {
//        this.endYear = endYear;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//}
