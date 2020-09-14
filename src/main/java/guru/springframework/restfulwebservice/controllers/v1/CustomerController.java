package guru.springframework.restfulwebservice.controllers.v1;

import guru.springframework.restfulwebservice.api.v1.model.CustomerDTO;
import guru.springframework.restfulwebservice.api.v1.model.CustomerListDTO;
import guru.springframework.restfulwebservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/customers")
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

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String id) {
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(Long.valueOf(id)),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return  new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);

    }
    @PutMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id ,@RequestBody CustomerDTO customerDTO){
        return  new ResponseEntity<CustomerDTO>(customerService.saveCustomerByDTO(Long.valueOf(id),customerDTO)
                ,HttpStatus.OK);

    }
    @PatchMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable String id ,@RequestBody CustomerDTO customerDTO){
        return  new ResponseEntity<CustomerDTO>(customerService.patchCustomer(Long.valueOf(id),customerDTO)
                ,HttpStatus.OK);

    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomerById(Long.valueOf(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
