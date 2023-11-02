package ru.web.laba_web2.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.services.dtos.ModelDto;
import ru.web.laba_web2.services.dtos.OfferDto;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.Offer;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.ModelRepository;
import ru.web.laba_web2.repositories.OfferRepository;
import ru.web.laba_web2.repositories.UserRepository;
import ru.web.laba_web2.services.OfferService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService<String> {
    private final ModelMapper modelMapper;
    private UserRepository userRepository;
    private ModelRepository modelRepository;
    private OfferRepository offerRepository;
    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDto register(OfferDto offerDto) {
        Offer offer = modelMapper.map(offerDto, Offer.class);
        if (offer.getModel().getUuid() != null) {
            Model model = modelRepository.findByUuid(offerDto.getModelDto().getUuid()).get();
            offer.setModel(model);
        }
        return modelMapper.map(offerRepository.saveAndFlush(offer), OfferDto.class);
    }


    @Override
    public void deleteByUuid(String uuid) {
        offerRepository.deleteByUuid(uuid);
    }

    @Override
    public void transfer(OfferDto offerDto, ModelDto modelDto, UserDto userDto) {
        Offer offer = offerRepository.findByUuid(offerDto.getUuid()).get();
        Model model = modelRepository.findByUuid(modelDto.getUuid()).get();
        User user = userRepository.findByUuid(userDto.getUuid()).get();
        offer.setModel(model);
        offer.setSeller(user);
        offerRepository.save(offer);
    }

    @Override
    public Optional<OfferDto> findByUuid(String uuid) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findByUuid(uuid), OfferDto.class));
    }

    @Override
    public List<OfferDto> getAll() {
        return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, OfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllAdmins() {
        List<User> admins = userRepository.findAllByRoleRole(Role.ADMIN);
        return admins.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void editOffer(OfferDto offerDto) {
        Offer offer = modelMapper.map(offerDto, Offer.class);
        offerRepository.saveAndFlush(offer);
    }
    @Override
    public int calculateTotalPrice() {
        List<Offer> offers = offerRepository.findAll();
        int totalPrice = offers.stream().mapToInt(Offer::getPrice).sum();
        return totalPrice;
    }



//    @Override
//    public Offer create(OfferDto offerDto) {
//        Offer offer = modelMapper.map(offerDto, Offer.class);
//        return offerRepository.save(offer);
//    }
}