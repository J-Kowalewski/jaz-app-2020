package pl.edu.pjwstk.jaz.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GetAuctionRequest> exploreAuction(@PathVariable Long id){
        var auction = auctionService.findById(id);
        if(auction==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        var photo = photoService.getPhotos(id);
        var parameter = auctionService.findParameterById(id);
        var getAuctionRequest = new GetAuctionRequest(
                auction.getId(),
                auction.getTitle(),
                auction.getDescription(),
                auction.getPrice(),
                auction.getCategory().getName(),
                auction.getAuthor().getUsername(),
                photo,
                parameter.getParameter().getKey(),
                parameter.getValue());
        return new ResponseEntity<>(getAuctionRequest,HttpStatus.OK);
    }

    @GetMapping("/explore/category/{id}")
    public ResponseEntity<CategoryEntity> exploreCategory(@PathVariable Long id){
        var category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @GetMapping("/explore/photo/{id}")
    public ResponseEntity<List<String>> explorePhoto(@PathVariable Long id){
        var photos = photoService.getPhotos(id);
        if(photos.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(photos,HttpStatus.OK);
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
