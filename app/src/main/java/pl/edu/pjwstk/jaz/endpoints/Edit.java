package pl.edu.pjwstk.jaz.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.auction.AuctionService;
import pl.edu.pjwstk.jaz.auction.EditAuctionRequest;
import pl.edu.pjwstk.jaz.category.CategoryService;
import pl.edu.pjwstk.jaz.category.EditCategoryRequest;
import pl.edu.pjwstk.jaz.user.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@PreAuthorize("hasAnyAuthority('admin')")
@RestController
public class Edit {
    @PersistenceContext
    private final EntityManager entityManager;
    final CategoryService categoryService;
    final AuctionService auctionService;
    private final UserService userService;

    public Edit(EntityManager entityManager, CategoryService categoryService, AuctionService auctionService, UserService userService) {
        this.entityManager = entityManager;
        this.categoryService = categoryService;
        this.auctionService = auctionService;
        this.userService = userService;
    }
    @PutMapping("/edit/category")
    public void editCategory(@RequestBody EditCategoryRequest editCategoryRequest){
        var category = categoryService.findByName(editCategoryRequest.getName());
        category.setName(editCategoryRequest.getNewName());
        categoryService.update(category);
    }
    @PreAuthorize("hasAnyAuthority('user')")
    @PutMapping("/edit/auction")
    public ResponseEntity<Void> editAuction(@RequestBody EditAuctionRequest request) {
        var category = categoryService.findByName(request.getCategory());
        var auction = auctionService.findById(request.getId());
        if(userService.getLoggedUser().getId().equals(auction.getAuthor().getId()) ||
                userService.getLoggedUser().getAuthorities().contains("admin")){

            auction.setTitle(request.getTitle());
            auction.setDescription(request.getDescription());
            auction.setPrice(request.getPrice());
            auction.setCategory(category);
            auctionService.update(auction);

            var parameter = auctionService.findAuctionParameterById(request.getId());
            parameter.setValue(request.getParameterValue());
            parameter.getParameter().setKey(request.getParameterKey());
            auctionService.updateParameter(parameter);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
