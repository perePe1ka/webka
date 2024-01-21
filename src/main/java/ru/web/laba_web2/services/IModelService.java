package ru.web.laba_web2.services;

import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.viewModel.AddModelViewModel;
import ru.web.laba_web2.viewModel.DetailModel;
import ru.web.laba_web2.viewModel.EditModel;
import ru.web.laba_web2.viewModel.ShowModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IModelService<String> {
    void register(AddModelViewModel newModel);

    void deleteByModelName(String modelName);

    Optional<EditModel> findByUuid(UUID uuid);

    DetailModel getAll(String name);

    List<ShowModel> allModels();

    void editModel(EditModel editModel);

    Model findByName(String name);
}
