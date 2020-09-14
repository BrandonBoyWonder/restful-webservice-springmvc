package guru.springframework.restfulwebservice.controllers.v1;

import guru.springframework.restfulwebservice.api.v1.model.VendorDTO;
import guru.springframework.restfulwebservice.controllers.RestResponseEntityExceptionHandler;
import guru.springframework.restfulwebservice.exceptions.ResourceNotFoundException;
import guru.springframework.restfulwebservice.services.VendorService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest extends AbstractRestControllerTest{

    public static final String URL_TEMPLATE = "/api/v1/vendors/";
    @Mock
    private VendorService vendorService;

    @InjectMocks
    private VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getListVendors() throws Exception{
        VendorDTO vendorDTO1 = VendorDTO.builder()
                .id(1L)
                .name("Brandon")
                .build();

        VendorDTO vendorDTO2 = VendorDTO.builder()
                .id(2L)
                .name("Mark")
                .build();

        List<VendorDTO> vendorDTOS = Arrays.asList(vendorDTO1,vendorDTO2);

        when(vendorService.getAllVendors()).thenReturn(vendorDTOS);

        //then
        mockMvc.perform(get(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors",hasSize(2)));
    }

    @Test
    void TestGetVendorById() throws Exception{
        VendorDTO vendorDTO1 = VendorDTO.builder()
                .id(1L)
                .name("Brandon")
                .build();

        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO1);

        mockMvc.perform(get(URL_TEMPLATE+"/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo("Brandon")));
    }

    @Test
    void testCreateVendor() throws Exception{
        VendorDTO vendorDTO1 = VendorDTO.builder()
                .id(1L)
                .name("Brandon")
                .build();

        VendorDTO vendorDTOReturned = VendorDTO.builder()
                .id(1L)
                .name("Brandon")
                .build();

        when(vendorService.createNewVendor(vendorDTO1)).thenReturn(vendorDTOReturned);

        mockMvc.perform(post(URL_TEMPLATE)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendorDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",equalTo("Brandon")))
                .andExpect(jsonPath("$.vendorUrl",equalTo(vendorDTOReturned.getVendorUrl())));
    }

    @Test
    void testUpdateVendor() throws Exception{
        VendorDTO vendorDTO = VendorDTO.builder()
                .name("Brandon")
                .build();

        VendorDTO vendorDTOReturned = VendorDTO.builder()
                .id(1L)
                .name("Brandon")
                .build();

        when(vendorService.saveVendorByDTO(1L, vendorDTO)).thenReturn(vendorDTOReturned);

        mockMvc.perform(put(URL_TEMPLATE+"/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(vendorDTOReturned.getName())));
    }

    @Test
    void testPatchVendor() throws Exception{
        VendorDTO vendorDTO = VendorDTO.builder()
                .name("Brandon")
                .build();

        VendorDTO vendorDTOReturned = VendorDTO.builder()
                .id(1L)
                .name("Brandon")
                .build();

        when(vendorService.patchVendor(1L, vendorDTO)).thenReturn(vendorDTOReturned);

        mockMvc.perform(patch(URL_TEMPLATE+"/1")
                .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(vendorDTOReturned.getName())));
    }

    @Test
    void testDeleteByVendor() throws Exception{

        mockMvc.perform(delete(URL_TEMPLATE+"/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService,times(1)).deleteVendorById(1L);
    }

    @Test
    void testNotFoundException() throws Exception{
        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(URL_TEMPLATE+"/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}