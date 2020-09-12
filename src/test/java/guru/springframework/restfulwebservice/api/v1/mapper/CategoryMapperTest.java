package guru.springframework.restfulwebservice.api.v1.mapper;

import guru.springframework.restfulwebservice.api.v1.model.CategoryDTO;
import guru.springframework.restfulwebservice.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final String BRANDON = "Brandon";
    public static final long ID = 1L;

    @Test
    void shouldMapCategoryToDTO() throws Exception {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(BRANDON);

        //when
        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.categoryToCategoryDTO(category);

        //then
        assertNotNull(categoryDTO);
        assertEquals(BRANDON, categoryDTO.getName());
        assertEquals(ID,categoryDTO.getId());
    }
}