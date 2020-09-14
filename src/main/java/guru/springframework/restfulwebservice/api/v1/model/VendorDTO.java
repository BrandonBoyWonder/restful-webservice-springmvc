package guru.springframework.restfulwebservice.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VendorDTO {
    private Long id;
    private String name;
    private String vendorUrl;

    public String getVendorUrl() {
        if(id != null){
            return "/api/v1/vendors/"+this.id;
        }
        return "";
    }
}
