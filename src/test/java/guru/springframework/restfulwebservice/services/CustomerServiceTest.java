package guru.springframework.restfulwebservice.services;

import guru.springframework.restfulwebservice.api.v1.mapper.CustomerMapper;
import guru.springframework.restfulwebservice.api.v1.model.CustomerDTO;
import guru.springframework.restfulwebservice.domain.Customer;
import guru.springframework.restfulwebservice.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    public static final String BRANDON = "Brandon";
    public static final long ID = 1L;
    public static final String MOORE = "Moore";
    @Mock
    CustomerRepository customerRepository;


    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() throws Exception{
        //given
        Customer customer1 = Customer.builder()
                .id(ID)
                .firstName(BRANDON)
                .lastName(MOORE).build();

        Customer customer2 = Customer.builder()
                .id(2L)
                .firstName("Jared")
                .lastName("Du Toit").build();

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(2, customerDTOS.size());
    }

    @Test
    void getCustomerById() throws Exception{
        Customer customer1 = Customer.builder()
                .id(ID)
                .firstName(BRANDON)
                .lastName(MOORE).build();
        Optional<Customer> optionalCustomer = Optional.of(customer1);

        when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);
        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        //then
        assertEquals(ID, customerDTO.getId());
        assertEquals(BRANDON, customerDTO.getFirstName());
        assertEquals(MOORE, customerDTO.getLastName());
    }
}