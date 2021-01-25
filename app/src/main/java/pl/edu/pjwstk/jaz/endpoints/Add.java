package pl.edu.pjwstk.jaz.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.auction.AddAuctionRequest;
import pl.edu.pjwstk.jaz.auction.AuctionService;
import pl.edu.pjwstk.jaz.category.CategoryEntity;
import pl.edu.pjwstk.jaz.category.CategoryRequest;
import pl.edu.pjwstk.jaz.category.CategoryService;
import pl.edu.pjwstk.jaz.user.UserEntity;
import pl.edu.pjwstk.jaz.user.UserService;


@PreAuthorize("hasAnyAuthority('admin')")
@RestController
public class Add {
    final CategoryService categoryService;
    final AuctionService auctionService;
    final UserService userService;

    public Add(CategoryService categoryService, AuctionService auctionService, UserService userService) {
        this.categoryService = categoryService;
        this.auctionService = auctionService;
        this.userService = userService;
    }

    @PostMapping("/add/category")
    public void addCategory(@RequestBody CategoryRequest categoryRequest){
        categoryService.saveCategory(categoryRequest.getName());
    }
    @PostMapping("/add/auction")
    public void addAuction(@RequestBody AddAuctionRequest request){
        System.out.println(request.getCategory());
        UserEntity author = userService.findByUsername(request.getAuthor());
        CategoryEntity categoryEntity = categoryService.findByName(request.getCategory());
        auctionService.saveAuction(request.getTitle(),request.getDescription(), request.getPrice(), author, categoryEntity);
    }
}
