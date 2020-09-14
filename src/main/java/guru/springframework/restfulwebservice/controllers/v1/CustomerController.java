package guru.springframework.restfulwebservice.controllers.v1;

import guru.springframework.restfulwebservice.api.v1.model.CustomerDTO;
import guru.springframework.restfulwebservice.api.v1.model.CustomerListDTO;
import guru.springframework.restfulwebservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getCustomersList(){
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerByName(@PathVariable String id) {
        return customerService.getCustomerById(Long.valueOf(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return  customerService.createNewCustomer(customerDTO);

    }
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable String id ,@RequestBody CustomerDTO customerDTO){
        return  customerService.saveCustomerByDTO(Long.valueOf(id),customerDTO);

    }
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable String id ,@RequestBody CustomerDTO customerDTO){
        return customerService.patchCustomer(Long.valueOf(id),customerDTO);

    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable String id){
        customerService.deleteCustomerById(Long.valueOf(id));
    }


}
