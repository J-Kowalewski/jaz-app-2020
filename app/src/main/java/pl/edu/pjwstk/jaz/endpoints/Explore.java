package pl.edu.pjwstk.jaz.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.auction.AuctionService;
import pl.edu.pjwstk.jaz.auction.GetAuctionRequest;
import pl.edu.pjwstk.jaz.category.CategoryEntity;
import pl.edu.pjwstk.jaz.category.CategoryService;
import pl.edu.pjwstk.jaz.photo.PhotoService;
import pl.edu.pjwstk.jaz.user.UserEntity;
import pl.edu.pjwstk.jaz.user.UserService;

import java.util.List;

@PreAuthorize("hasAnyAuthority('user')")
@RestController
public class Explore {
    private final AuctionService auctionService;
    private final CategoryService categoryService;
    private final PhotoService photoService;
    private final UserService userService;

    public Explore(AuctionService auctionService, CategoryService categoryService, PhotoService photoService, UserService userService) {
        this.auctionService = auctionService;
        this.categoryService = categoryService;
        this.photoService = photoService;
        this.userService = userService;
    }


    @GetMapping("/explore")
    public List<Object[]> explore(){
        return auctionService.findAllAuctions();
    }

    @GetMapping("/explore/auction/{id}")
    public GetAuctionRequest exploreAuction(@PathVariable Long id){
        var auction = auctionService.findById(id);
        var photo = photoService.getPhotos(id);
        var parameter = auctionService.findParameterById(id);
        return new GetAuctionRequest(
                auction.getId(),
                auction.getTitle(),
                auction.getDescription(),
                auction.getPrice(),
                auction.getCategory().getName(),
                auction.getAuthor().getUsername(),
                photo,
                parameter.getParameter().getKey(),
                parameter.getValue());
    }

    @GetMapping("/explore/category/{id}")
    public CategoryEntity exploreCategory(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @GetMapping("/explore/photo/{id}")
    public List<String> explorePhoto(@PathVariable Long id){
        return photoService.getPhotos(id);
    }
    @GetMapping("/explore/user")
    public UserEntity exploreUser() {
        return userService.getLoggedUser();
    }
    @GetMapping("/explore/category")
    public List<CategoryEntity> exploreAllCategory() {
        return categoryService.findAll();
    }
}
