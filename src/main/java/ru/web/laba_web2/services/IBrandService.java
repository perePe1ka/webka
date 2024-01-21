package ru.web.laba_web2.services;

import ru.web.laba_web2.viewModel.AddBrandViewModel;
import ru.web.laba_web2.viewModel.DetailBrand;
import ru.web.laba_web2.viewModel.EditBrand;
import ru.web.laba_web2.viewModel.ShowBrand;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBrandService<String> {
    void register(AddBrandViewModel addBrandViewModel);

    void deleteByName(String brandName);

    Optional<EditBrand> findByUuid(UUID uuid);

    List<ShowBrand> allBrands();

    DetailBrand getAll(String brandName);

    void editBrand(EditBrand editBrand);
}
