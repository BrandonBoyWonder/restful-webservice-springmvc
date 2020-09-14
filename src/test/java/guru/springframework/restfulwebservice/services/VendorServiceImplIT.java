package guru.springframework.restfulwebservice.services;

import guru.springframework.restfulwebservice.api.v1.mapper.VendorMapper;
import guru.springframework.restfulwebservice.api.v1.model.VendorDTO;
import guru.springframework.restfulwebservice.bootstrap.Bootstrap;
import guru.springframework.restfulwebservice.domain.Vendor;
import guru.springframework.restfulwebservice.repositories.CategoryRepository;
import guru.springframework.restfulwebservice.repositories.CustomerRepository;
import guru.springframework.restfulwebservice.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class VendorServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() throws Exception{
        System.out.println("Loading Data to Database...");

        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository, vendorRepository);
        bootstrap.run();

        System.out.println("Finished Loading Data.");

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void testPatchVendorUpdateName() {
        String updatedName = "updatedName";
        Long id = getAnyVendorId();

        Vendor originalVendor = vendorRepository.getOne(id);
        assertNotNull(originalVendor);

        //save originalNames
        String originalName = originalVendor.getName();

        VendorDTO vendorDTO = VendorDTO.builder()
                .name(updatedName)
                .build();

        vendorService.patchVendor(id,vendorDTO);

        Vendor updatedVendor = vendorRepository.findById(id).get();

        assertNotNull(updatedVendor);
        assertEquals(updatedVendor.getName(),updatedName);
        assertNotEquals(originalName,updatedVendor.getName());

    }

    private Long getAnyVendorId() {
        List<Vendor> vendors = vendorRepository.findAll();

        System.out.println("Vendors found: " + vendors.size());

        return vendors.get(0).getId();
    }
}
