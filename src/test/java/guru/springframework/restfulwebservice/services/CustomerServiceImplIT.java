package guru.springframework.restfulwebservice.services;

import guru.springframework.restfulwebservice.api.v1.mapper.CustomerMapper;
import guru.springframework.restfulwebservice.api.v1.model.CustomerDTO;
import guru.springframework.restfulwebservice.bootstrap.Bootstrap;
import guru.springframework.restfulwebservice.domain.Customer;
import guru.springframework.restfulwebservice.repositories.CategoryRepository;
import guru.springframework.restfulwebservice.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() throws Exception{
        System.out.println("Loading Data to Database...");

        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository);
        bootstrap.run();

        System.out.println("Finished Loading Data.");

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchCustomerUpdateFirstName() throws Exception{
        String updatedName = "updatedName";
        Long id = getAnyCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        //save originalNames
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName(updatedName)
                .build();

        customerService.patchCustomer(id,customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName,updatedCustomer.getFirstName());
        assertNotEquals(originalFirstName, updatedCustomer.getFirstName());
        assertEquals(originalLastName,updatedCustomer.getLastName());
    }

    @Test
    public void patchCustomerUpdateLastName() throws Exception{
        String updatedName = "updatedName";
        Long id = getAnyCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        //save originalNames
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = CustomerDTO.builder()
                .lastName(updatedName)
                .build();

        customerService.patchCustomer(id,customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName,updatedCustomer.getLastName());
        assertNotEquals(originalLastName, updatedCustomer.getLastName());
        assertEquals(originalFirstName,updatedCustomer.getFirstName());
    }


    private Long getAnyCustomerIdValue(){
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers found: " + customers.size());

        return customers.get(0).getId();
    }
}
