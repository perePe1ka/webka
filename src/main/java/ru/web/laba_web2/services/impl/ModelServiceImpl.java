package ru.web.laba_web2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.dtos.BrandDto;
import ru.web.laba_web2.dtos.ModelDto;
import ru.web.laba_web2.dtos.OfferDto;
import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.Offer;
import ru.web.laba_web2.repositories.BrandRepository;
import ru.web.laba_web2.repositories.ModelRepository;
import ru.web.laba_web2.repositories.OfferRepository;
import ru.web.laba_web2.services.ModelService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<Integer> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private OfferRepository offerRepository;


    @Override
    public ModelDto register(ModelDto modelDto) {
        Model model = modelMapper.map(modelDto, Model.class);
        if (modelDto.getBrand().getId() != 0) {
            Brand brand = brandRepository.findById(modelDto.getBrand().getId()).get();
            model.setBrand(brand);
        }
        if (modelDto.getOffer().getId() != 0) {
            Offer offer = offerRepository.findById(modelDto.getOffer().getId()).get();
            model.setOffer(offer);
        }
        return modelMapper.map(modelRepository.save(model), ModelDto.class);
    }

    @Override
    public void expel(ModelDto modelDto) {
        modelRepository.deleteById(modelDto.getId());
    }

    @Override
    public void expel(Integer id) {
        modelRepository.deleteById(id);
    }

    @Override
    public void transfer(ModelDto modelDto, BrandDto brandDto, OfferDto offerDto) {
        Model model = modelRepository.findById(modelDto.getId()).get();
        Brand brand = brandRepository.findById(brandDto.getId()).get();
        Offer offer = offerRepository.findById(offerDto.getId()).get();
        model.setBrand(brand);
        model.setOffer(offer);
        modelRepository.save(model);
    }

    @Override
    public Optional<ModelDto> findById(Integer id) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findById(id), ModelDto.class));
    }

    @Override
    public List<ModelDto> getAll() {
        return modelRepository.findAll().stream().map((model) -> modelMapper.map(model, ModelDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ModelDto> findByModel(String brand) {
        return modelRepository.findAllByBrandName(brand).stream().map((model) -> modelMapper.map(model, ModelDto.class)).collect(Collectors.toList());
    }
}
