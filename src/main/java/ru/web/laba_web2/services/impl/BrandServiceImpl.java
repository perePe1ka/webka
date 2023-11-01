package ru.web.laba_web2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.services.dtos.BrandDto;
import ru.web.laba_web2.models.Brand;
import ru.web.laba_web2.repositories.BrandRepository;
import ru.web.laba_web2.services.BrandService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService<String> {

    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand create(BrandDto brandDto) {
        Brand brand = modelMapper.map(brandDto, Brand.class);
        return brandRepository.saveAndFlush(brand);
    }

    @Override
    public void delete(BrandDto brandDto) {
        brandRepository.deleteByUuid(brandDto.getUuid());
    }

    @Override
    public void deleteByUuid(String uuid) {
        brandRepository.deleteByUuid(uuid);
    }


    @Override
    public Optional<BrandDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findByUuid(uuid), BrandDto.class));
    }

    @Override
    public List<BrandDto> getAll() {
        return brandRepository.findAll().stream().map((brand) -> modelMapper.map(brand, BrandDto.class)).collect(Collectors.toList());
    }

}
