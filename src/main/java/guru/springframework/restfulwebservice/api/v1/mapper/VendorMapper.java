package guru.springframework.restfulwebservice.api.v1.mapper;

import guru.springframework.restfulwebservice.api.v1.model.VendorDTO;
import guru.springframework.restfulwebservice.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
