package ru.web.laba_web2.services;

import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.viewModel.AddModelViewModel;
import ru.web.laba_web2.viewModel.DetailModelViewModel;
import ru.web.laba_web2.viewModel.EditModelViewModel;
import ru.web.laba_web2.viewModel.ShowModelViewModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IModelService<String> {
    void register(AddModelViewModel newModel);

    void deleteByModelName(String modelName);

    Optional<EditModelViewModel> findByUuid(UUID uuid);

    DetailModelViewModel getAll(String name);

    List<ShowModelViewModel> allModels();

    void editModel(EditModelViewModel editModel);

    Model findByName(String name);
}
