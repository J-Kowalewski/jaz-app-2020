package pl.edu.pjwstk.jaz.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.auction.AuctionService;
import pl.edu.pjwstk.jaz.auction.EditAuctionRequest;
import pl.edu.pjwstk.jaz.category.CategoryService;
import pl.edu.pjwstk.jaz.category.EditCategoryRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@PreAuthorize("hasAnyAuthority('admin')")
@RestController
public class Edit {
    @PersistenceContext
    private final EntityManager entityManager;
    final CategoryService categoryService;
    final AuctionService auctionService;

    public Edit(EntityManager entityManager, CategoryService categoryService, AuctionService auctionService) {
        this.entityManager = entityManager;
        this.categoryService = categoryService;
        this.auctionService = auctionService;
    }

    @PutMapping("/edit/category")
    public void editCategory(@RequestBody EditCategoryRequest editCategoryRequest){
        var category = categoryService.findByName(editCategoryRequest.getName());
        category.setName(editCategoryRequest.getNewName());
        categoryService.update(category);
    }
    @PutMapping("/edit/auction")
    public void editAuction(@RequestBody EditAuctionRequest request) {
        var category = categoryService.findByName(request.getCategory());
        var auction = auctionService.findById(request.getId());
        auction.setTitle(request.getTitle());
        auction.setDescription(request.getDescription());
        auction.setPrice(request.getPrice());
        auction.setCategory(category);
        auctionService.update(auction);
    }
}
