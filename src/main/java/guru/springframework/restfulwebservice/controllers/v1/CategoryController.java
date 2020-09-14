package guru.springframework.restfulwebservice.controllers.v1;

import guru.springframework.restfulwebservice.api.v1.model.CategoryDTO;
import guru.springframework.restfulwebservice.api.v1.model.CategoryListDTO;
import guru.springframework.restfulwebservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable(name = "name") String name){
        return categoryService.getCategoryByName(name);
    }

}
