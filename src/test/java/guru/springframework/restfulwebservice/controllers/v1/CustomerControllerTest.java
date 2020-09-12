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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest extends AbstractRestControllerTest{

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

    @Test
    void testCreateCustomer() throws Exception{
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .firstName(BRANDON)
                .lastName(MOORE)
                .build();

        CustomerDTO customerDTOReturned = CustomerDTO.builder()
                .id(ID)
                .firstName(customerDTO1.getFirstName())
                .lastName(customerDTO1.getLastName())
                .build();

        when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(customerDTOReturned);

        mockMvc.perform(post("/api/v1/customers/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(customerDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",equalTo(BRANDON)))
                .andExpect(jsonPath("$.customerUrl", equalTo(customerDTOReturned.getCustomerUrl())));
    }

    @Test
    void testUpdateCustomer() throws Exception{
        CustomerDTO customerDTO1 = CustomerDTO.builder()
                .firstName(BRANDON)
                .lastName(MOORE)
                .build();

        CustomerDTO customerDTOReturned = CustomerDTO.builder()
                .firstName(customerDTO1.getFirstName())
                .lastName(customerDTO1.getLastName())
                .build();

        when(customerService.saveCustomerByDTO(anyLong(),any(CustomerDTO.class))).thenReturn(customerDTOReturned);

        mockMvc.perform(put("/api/v1/customers/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(customerDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(BRANDON)))
                .andExpect(jsonPath("$.lastName", equalTo(MOORE)));
    }
}