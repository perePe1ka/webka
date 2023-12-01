//package ru.web.laba_web2.init;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ru.web.laba_web2.constants.Category;
//import ru.web.laba_web2.constants.Engine;
//import ru.web.laba_web2.constants.Role;
//import ru.web.laba_web2.constants.Transmission;
//import ru.web.laba_web2.services.*;
//import ru.web.laba_web2.services.dtos.*;
//import ru.web.laba_web2.viewModel.AddBrandViewModel;
//
//import java.io.IOException;
//
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private OfferService offerService;
//    private ModelService modelService;
//    private UserService userService;
//    private BrandService brandService;
//    private RolesService rolesService;
//    @Autowired
//    public DataInitializer(OfferService offerService, ModelService modelService, UserService userService, BrandService brandService, RolesService rolesService) {
//        this.offerService = offerService;
//        this.modelService = modelService;
//        this.userService = userService;
//        this.brandService = brandService;
//        this.rolesService = rolesService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        seedData();
//    }
//
//    private void seedData() throws IOException {
//
//        BrandDto brandDto1 = new BrandDto(
//                "KiaBrand"
//        );
//
//        BrandDto brandDto2 = new BrandDto(
//                "HyndaiBrand"
//        );
//
//        ModelDto modelDto1 = new ModelDto(
//                "KiaModel",
//                Category.TRUCK,
//                "https://vlad1.com/image.jpg",
//                2000,
//                2005,
//                brandDto1.getName()
//        );
//
//        ModelDto modelDto2 = new ModelDto(
//                "HyndaiModel",
//                Category.BUS,
//                "https://vlad2.com/image.jpg",
//                2005,
//                2010,
//                brandDto2.getName()
//        );
//
//        RolesDto rolesDto1 = new RolesDto(
//                Role.USER
//        );
//
//        RolesDto rolesDto2 = new RolesDto(
//                Role.ADMIN
//        );
//
//        UserDto userDto1 = new UserDto(
//                "Vladuss_1337",
//                "123456",
//                "Влад",
//                "Михалков",
//                true,
//                rolesDto1.getRole(),
//                "https://vlad5.com/image.jpg"
//        );
//
//        UserDto userDto2 = new UserDto(
//                "Vladuss",
//                "123456",
//                "Владислав",
//                "Усков",
//                true,
//                rolesDto2.getRole(),
//                "https://vlad6.com/image.jpg"
//        );
//
//        OfferDto offerDto1 = new OfferDto(
//                "some news1",
//                Engine.GASOLINE,
//                "https://vlad3.com/image.jpg",
//                200000,
//                2000,
//                Transmission.MANUAL,
//                2023,
//                userDto1.getUsername(),
//                modelDto1.getName()
//                );
//
//        OfferDto offerDto2 = new OfferDto(
//                "some news2",
//                Engine.DIESEL,
//                "https://vlad4.com/image.jpg",
//                10000,
//                3000,
//                Transmission.AUTOMATIC,
//                2022,
//                userDto2.getUsername(),
//                modelDto2.getName()
//                );
//
//        rolesService.register(rolesDto1);
//        rolesService.register(rolesDto2);
//
//        brandService.register(brandDto1);
//        brandService.register(brandDto2);
//
//        userService.register(userDto1);
//        userService.register(userDto2);
//
//        modelService.register(modelDto1);
//        modelService.register(modelDto2);
//
//        offerService.register(offerDto1);
//        offerService.register(offerDto2);
//
//
//        System.out.println(offerService.getAllAdmins());
//    }
//}
//
//
