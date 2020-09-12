package guru.springframework.restfulwebservice.api.v1.mapper;

import guru.springframework.restfulwebservice.api.v1.model.CategoryDTO;
import guru.springframework.restfulwebservice.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
