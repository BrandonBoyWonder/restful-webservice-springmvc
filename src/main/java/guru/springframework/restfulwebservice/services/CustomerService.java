package guru.springframework.restfulwebservice.services;

import guru.springframework.restfulwebservice.api.v1.model.CustomerDTO;
import guru.springframework.restfulwebservice.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    void  deleteCustomerById(Long id);
}
