package ru.web.laba_web2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.dtos.BrandDto;
import ru.web.laba_web2.dtos.ModelDto;
import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.repositories.BrandRepository;
import ru.web.laba_web2.repositories.ModelRepository;
import ru.web.laba_web2.services.ModelService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<UUID> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public ModelDto register(ModelDto modelDto) {
        Model model = modelMapper.map(modelDto, Model.class);
        if (modelDto.getBrand().getUuid() != null) {
            Brand brand = brandRepository.findByUuid(modelDto.getBrand().getUuid()).get();
            model.setBrand(brand);
        }
        return modelMapper.map(modelRepository.save(model), ModelDto.class);
    }

    @Override
    public void delete(ModelDto modelDto) {
        modelRepository.deleteByUuid(modelDto.getUuid());
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        modelRepository.deleteByUuid(uuid);
    }

    @Override
    public void transfer(ModelDto modelDto, BrandDto brandDto) {
        Model model = modelRepository.findByUuid(modelDto.getUuid()).get();
        Brand brand = brandRepository.findByUuid(brandDto.getUuid()).get();
        model.setBrand(brand);
        modelRepository.save(model);
    }

    @Override
    public Optional<ModelDto> findByUuid(UUID uuid) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findByUuid(uuid), ModelDto.class));
    }

    @Override
    public List<ModelDto> getAll() {
        return modelRepository.findAll().stream().map((model) -> modelMapper.map(model, ModelDto.class)).collect(Collectors.toList());
    }


    @Override
    public List<ModelDto> getModelsByBrand(String brandName) {
        List<Model> models = modelRepository.findByBrandName(brandName);
        return models.stream()
                .map(model -> modelMapper.map(model, ModelDto.class))
                .collect(Collectors.toList());
    }
}
