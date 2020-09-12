package guru.springframework.restfulwebservice.controllers.v1;

import guru.springframework.restfulwebservice.api.v1.model.CategoryDTO;
import guru.springframework.restfulwebservice.services.CategoryService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    private static final String NAME="Jim";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void testListCategories() throws Exception{
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName(NAME);
        categoryDTO1.setId(1L);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setName("Brandon");
        categoryDTO2.setId(2L);

        List<CategoryDTO> categoryDTOS = Arrays.asList(categoryDTO1,categoryDTO2);

        when(categoryService.getAllCategories()).thenReturn(categoryDTOS);

        mockMvc.perform(get("/api/v1/categories/")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories",hasSize(2)));
    }

    @Test
    void testGetNameCategories() throws Exception{
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName(NAME);
        categoryDTO1.setId(1L);

        when(categoryService.getCategoryByName(NAME)).thenReturn(categoryDTO1);

        mockMvc.perform(get("/api/v1/categories/Jim")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(NAME)));
    }
}