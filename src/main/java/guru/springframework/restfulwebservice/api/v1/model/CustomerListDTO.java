package guru.springframework.restfulwebservice.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerListDTO {
    private List<CustomerDTO> customers;
}
