package ru.web.laba_web2.services.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.laba_web2.constants.Role;
import ru.web.laba_web2.controllers.exceptions.ModelNotFoundException;
import ru.web.laba_web2.models.Model;
import ru.web.laba_web2.models.Offer;
import ru.web.laba_web2.models.User;
import ru.web.laba_web2.repositories.ModelRepository;
import ru.web.laba_web2.repositories.OfferRepository;
import ru.web.laba_web2.repositories.UserRepository;
import ru.web.laba_web2.services.ModelService;
import ru.web.laba_web2.services.OfferService;
import ru.web.laba_web2.services.UserService;
import ru.web.laba_web2.services.dtos.ModelDto;
import ru.web.laba_web2.services.dtos.OfferDto;
import ru.web.laba_web2.services.dtos.UserDto;
import ru.web.laba_web2.utils.ValidationUtil;
import ru.web.laba_web2.viewModel.AddOfferViewModel;
import ru.web.laba_web2.viewModel.DetailOffer;
import ru.web.laba_web2.viewModel.ShowOffer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService<String> {
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private UserRepository userRepository;
    private ModelRepository modelRepository;
    private OfferRepository offerRepository;
    private ModelService modelService;

    private UserService userService;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
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
    public void register(AddOfferViewModel newOffer) {
        if (!this.validationUtil.isValid(newOffer)) {

            this.validationUtil
                    .violations(newOffer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            throw new IllegalArgumentException("Что то пошло не так");
        }

        Offer offer = this.modelMapper.map(newOffer, Offer.class);
        offer.setSeller(userService.findByUsername(newOffer.getSeller()));
        offer.setModel(modelService.findByName(newOffer.getModel()));

        this.offerRepository.saveAndFlush(offer);
    }

    @Override
    public void deleteByOfferDescription(String description) {
        offerRepository.deleteOfferByDescription(description);
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
    public DetailOffer getAll(String offerDescription) {
        Offer offer = offerRepository.findByDescription(offerDescription)
                .orElseThrow(() -> new ModelNotFoundException("Offer with name " + offerDescription + " not found"));
        return modelMapper.map(offer, DetailOffer.class);
    }

    @Override
    public List<ShowOffer> allOffers() {
        return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, ShowOffer.class)).collect(Collectors.toList());
    }

    @Override
    public void editOffer(OfferDto offerDto) {
        if (!this.validationUtil.isValid(offerDto)) {
            this.validationUtil
                    .violations(offerDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else {
            try {
                Offer offer = modelMapper.map(offerDto, Offer.class);
                offerRepository.saveAndFlush(offer);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так");
            }
        }
    }

    @Override
    public List<UserDto> getAllAdmins() {
        List<User> admins = userRepository.findAllByRoleRole(Role.ADMIN);
        return mapUsersToUserDtos(admins);
    }

    private List<UserDto> mapUsersToUserDtos(List<User> users) {
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public int calculateTotalPrice() {
        List<Offer> offers = offerRepository.findAll();
        int totalPrice = offers.stream().mapToInt(Offer::getPrice).sum();
        return totalPrice;
    }

}