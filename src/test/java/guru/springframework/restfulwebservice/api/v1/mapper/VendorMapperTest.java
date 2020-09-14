package guru.springframework.restfulwebservice.api.v1.mapper;

import guru.springframework.restfulwebservice.api.v1.model.VendorDTO;
import guru.springframework.restfulwebservice.domain.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {


    public static final long ID = 1L;
    public static final String NAME = "Brandon";

    @Test
    void vendorToVendorDTO() {
        //given
        Vendor vendor = Vendor.builder()
                .id(ID)
                .name(NAME)
                .build();

        //when
        VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);

        //then
        assertNotNull(vendorDTO);
        assertEquals(vendorDTO.getId(),vendor.getId());
        assertEquals(vendorDTO.getName(), vendor.getName());
        assertEquals("/api/v1/vendors/"+Long.valueOf(ID), vendorDTO.getVendorUrl());

    }

    @Test
    void vendorDTOToVendor() {
        //given
        VendorDTO vendorDTO = VendorDTO.builder()
                .id(ID)
                .name(NAME)
                .build();

        //when
        Vendor vendor = VendorMapper.INSTANCE.vendorDTOToVendor(vendorDTO);

        assertNotNull(vendor);
        assertEquals(vendorDTO.getId(),vendor.getId());
        assertEquals(vendorDTO.getName(),vendor.getName());
    }
}