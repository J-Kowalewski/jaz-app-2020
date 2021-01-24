package pl.edu.pjwstk.jaz.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.category.CategoryRequest;
import pl.edu.pjwstk.jaz.category.CategoryService;


@PreAuthorize("hasAnyAuthority('admin')")
@RestController("/add")
public class Add {
    final CategoryService categoryService;

    public Add(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public void addCategory(@RequestBody CategoryRequest categoryRequest){
        categoryService.saveCategory(categoryRequest.getName());
    }
    @GetMapping("/auction")
    public void addAuction(){

    }
}
