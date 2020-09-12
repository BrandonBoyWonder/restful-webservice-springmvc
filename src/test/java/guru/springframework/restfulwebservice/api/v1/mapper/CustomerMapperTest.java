package guru.springframework.restfulwebservice.api.v1.mapper;

import guru.springframework.restfulwebservice.api.v1.model.CustomerDTO;
import guru.springframework.restfulwebservice.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final String BRANDON = "Brandon";
    public static final String MOORE = "Moore";
    public static final long ID = 1L;

    @Test
    void shouldMapCustomerDTO() {
        //given
        Customer customer1 = Customer.builder()
                .id(ID)
                .firstName(BRANDON)
                .lastName(MOORE)
                .build();
        //when
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.CustomerToCustomerDTO(customer1);

        //then
        assertNotNull(customerDTO);
        assertEquals(ID, customerDTO.getId());
        assertEquals(BRANDON, customerDTO.getFirstName());
        assertEquals(MOORE, customerDTO.getLastName());
        assertEquals("/api/v1/customers/"+Long.valueOf(1L),customerDTO.getCustomerUrl());
    }
}