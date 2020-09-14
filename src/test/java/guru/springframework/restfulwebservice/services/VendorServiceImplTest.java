package guru.springframework.restfulwebservice.services;

import guru.springframework.restfulwebservice.api.v1.mapper.VendorMapper;
import guru.springframework.restfulwebservice.api.v1.model.VendorDTO;
import guru.springframework.restfulwebservice.domain.Vendor;
import guru.springframework.restfulwebservice.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class VendorServiceImplTest {

    public static final String NAME="Brandon";
    public static final Long ID = 1L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);

    }

    @Test
    void getAllVendors() {
        //given
        List<Vendor> vendors = Arrays.asList(Vendor.builder().build(),Vendor.builder().build(),Vendor.builder().build());

        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        //then
        assertEquals(3,vendorDTOS.size());
    }

    @Test
    void getVendorById() {
        Vendor vendor1 = Vendor.builder()
                .id(ID)
                .name(NAME)
                .build();
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor1));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        assertEquals(ID,vendorDTO.getId());
        assertEquals(NAME,vendorDTO.getName());
    }

    @Test
    void createNewVendor() {
        VendorDTO vendorDTO = VendorDTO.builder()
                .name(NAME)
                .build();

        Vendor savedVendor = Vendor.builder()
                .id(ID)
                .name(NAME)
                .build();

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals(ID, savedVendorDTO.getId());
        assertEquals(NAME, savedVendorDTO.getName());
        assertEquals("/api/v1/vendors/"+ID,savedVendorDTO.getVendorUrl());
    }

    @Test
    void saveCustomerByDTO() {
        VendorDTO vendorDTO = VendorDTO.builder()
                .id(ID)
                .name(NAME)
                .build();

        Vendor savedVendor = Vendor.builder()
                .id(ID)
                .name(NAME)
                .build();

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedVenderDTO = vendorService.saveVendorByDTO(ID,vendorDTO);

        assertEquals(savedVenderDTO.getName(),vendorDTO.getName());

    }

    @Test
    void deleteVendorById() {
        vendorRepository.deleteById(ID);

        verify(vendorRepository,times(1)).deleteById(ID);
    }
}