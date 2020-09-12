package guru.springframework.restfulwebservice.controllers.v1;

import guru.springframework.restfulwebservice.api.v1.model.CustomerDTO;
import guru.springframework.restfulwebservice.api.v1.model.CustomerListDTO;
import guru.springframework.restfulwebservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/customers/")
@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getCustomersList(){
        return new ResponseEntity<CustomerListDTO>
                (new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String id) {
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(Long.valueOf(id)),HttpStatus.OK);
    }

}
