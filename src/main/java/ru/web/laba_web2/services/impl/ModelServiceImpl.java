package ru.web.laba_web2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.services.dtos.ModelDto;
import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.repositories.BrandRepository;
import ru.web.laba_web2.repositories.ModelRepository;
import ru.web.laba_web2.services.ModelService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<String> {
    private final ModelMapper modelMapper;
    private BrandRepository brandRepository;
    private ModelRepository modelRepository;
    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelDto register(ModelDto modelDto) {
        Model model = modelMapper.map(modelDto, Model.class);
        if (modelDto.getBrand().getUuid() != null) {
            Brand brand = brandRepository.findById(modelDto.getBrand().getUuid()).get();
            model.setBrand(brand);
        }
        return modelMapper.map(modelRepository.saveAndFlush(model), ModelDto.class);
    }


    @Override
    public void deleteByUuid(String uuid) {
        modelRepository.deleteByUuid(uuid);
    }

    @Override
    public void transfer(ModelDto modelDto, BrandDto brandDto) {
        Model model = modelRepository.findByUuid(modelDto.getUuid()).get();
        Brand brand = brandRepository.findByUuid(brandDto.getUuid()).get();
        model.setBrand(brand);
        modelRepository.saveAndFlush(model);
    }

    @Override
    public Optional<ModelDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findByUuid(uuid), ModelDto.class));
    }

    @Override
    public List<ModelDto> getAll() {
        return modelRepository.findAll().stream().map((model) -> modelMapper.map(model, ModelDto.class)).collect(Collectors.toList());
    }

    @Override
    public void editModel(ModelDto modelDto) {
        Model model = modelMapper.map(modelDto, Model.class);
        modelRepository.saveAndFlush(model);
    }


//    @Override
//    public Model create(ModelDto modelDto) {
//        Model model = modelMapper.map(modelDto, Model.class);
//        return modelRepository.saveAndFlush(model);
//    }
}
