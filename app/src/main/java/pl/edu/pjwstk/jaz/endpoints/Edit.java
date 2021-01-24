package pl.edu.pjwstk.jaz.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.category.CategoryService;
import pl.edu.pjwstk.jaz.category.EditCategoryRequest;


@PreAuthorize("hasAnyAuthority('admin')")
@RestController("/edit")
public class Edit {
    final CategoryService categoryService;

    public Edit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public void editCategory(@RequestBody EditCategoryRequest editCategoryRequest){
        categoryService.findByName(editCategoryRequest.getName()).setName(editCategoryRequest.getNewName());
    }
    @GetMapping("/auction")
    public void editAuction(){

    }
}
