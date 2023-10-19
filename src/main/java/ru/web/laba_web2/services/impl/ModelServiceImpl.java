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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<Long> {
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
        if (modelDto.getBrand().getUuid() != null) {
            Brand brand = brandRepository.findByUuid(modelDto.getBrand().getUuid()).get();
            model.setBrand(brand);
        }
        if (modelDto.getOffer().getUuid() != null) {
            Offer offer = offerRepository.findByUuid(modelDto.getOffer().getUuid()).get();
            model.setOffer(offer);
        }
        return modelMapper.map(modelRepository.save(model), ModelDto.class);
    }

    @Override
    public void delete(ModelDto modelDto) {
        modelRepository.deleteByUuid(modelDto.getUuid());
    }

    @Override
    public void deleteByUUID(Long uuid) {
        modelRepository.deleteByUuid(uuid);
    }

    @Override
    public void transfer(ModelDto modelDto, BrandDto brandDto, OfferDto offerDto) {
        Model model = modelRepository.findByUuid(modelDto.getUuid()).get();
        Brand brand = brandRepository.findByUuid(brandDto.getUuid()).get();
        Offer offer = offerRepository.findByUuid(offerDto.getUuid()).get();
        model.setBrand(brand);
        model.setOffer(offer);
        modelRepository.save(model);
    }

    @Override
    public Optional<ModelDto> findByUUID(Long uuid) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findByUuid(uuid), ModelDto.class));
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
