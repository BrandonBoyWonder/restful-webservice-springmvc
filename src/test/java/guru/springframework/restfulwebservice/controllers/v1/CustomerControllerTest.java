package guru.springframework.restfulwebservice.controllers.v1;

import guru.springframework.restfulwebservice.api.v1.model.CustomerDTO;
import guru.springframework.restfulwebservice.domain.Customer;
import guru.springframework.restfulwebservice.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    public static final String BRANDON = "Brandon";
    public static final String MOORE = "Moore";
    public static final long ID = 1L;
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getListCustomers() throws Exception{
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .id(ID)
                .firstName(BRANDON)
                .lastName(MOORE)
                .build();

        CustomerDTO customerDTO2 = CustomerDTO.builder()
                .id(2L)
                .firstName("Josh")
                .lastName("Long")
                .build();

        List<CustomerDTO> customerDTOList = Arrays.asList(customerDTO1,customerDTO2);

        when(customerService.getAllCustomers()).thenReturn(customerDTOList);

        //then
        mockMvc.perform(get("/api/v1/customers/")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",hasSize(2)));

    }

    @Test
    void testGetCustomerById() throws Exception{
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .id(ID)
                .firstName(BRANDON)
                .lastName(MOORE)
                .build();

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO1);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(BRANDON)));
    }
}