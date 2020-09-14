package guru.springframework.restfulwebservice.services;

import guru.springframework.restfulwebservice.api.v1.mapper.CustomerMapper;
import guru.springframework.restfulwebservice.api.v1.model.CustomerDTO;
import guru.springframework.restfulwebservice.domain.Customer;
import guru.springframework.restfulwebservice.exceptions.ResourceNotFoundException;
import guru.springframework.restfulwebservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::CustomerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::CustomerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.CustomerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
            return customerRepository.findById(id).map(customer -> {
                if (customerDTO.getFirstName() != null) {
                    customer.setFirstName(customerDTO.getFirstName());
                }

                if (customerDTO.getLastName() != null) {
                    customer.setLastName(customerDTO.getLastName());
                }
                return customerMapper.CustomerToCustomerDTO(customer);
            }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }


    private CustomerDTO saveAndReturnDTO(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.CustomerToCustomerDTO(savedCustomer);
    }
}
