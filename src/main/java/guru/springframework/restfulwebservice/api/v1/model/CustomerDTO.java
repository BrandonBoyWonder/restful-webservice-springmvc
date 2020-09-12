package guru.springframework.restfulwebservice.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String customerUrl;

    public String getCustomerUrl() {
        if(id != null){
            return "/api/v1/customers/"+this.id;
        }
        return "";
    }
}
