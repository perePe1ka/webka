package ru.web.laba_web2.services;

import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.viewModel.AddBrandViewModel;
import ru.web.laba_web2.viewModel.DetailBrand;
import ru.web.laba_web2.viewModel.EditBrand;
import ru.web.laba_web2.viewModel.ShowBrand;

import java.util.List;
import java.util.Optional;

public interface BrandService<String> {
    void register(AddBrandViewModel addBrandViewModel);

    void deleteByName(java.lang.String brandName);

    Optional<EditBrand> findByUuid(String uuid);

    List<ShowBrand> allBrands();

    DetailBrand getAll(String brandName);

    void editBrand(EditBrand editBrand);

    Brand findByName(String name);
}
