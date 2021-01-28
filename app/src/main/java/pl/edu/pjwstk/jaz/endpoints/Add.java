package pl.edu.pjwstk.jaz.endpoints;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.auction.AddAuctionRequest;
import pl.edu.pjwstk.jaz.auction.AuctionEntity;
import pl.edu.pjwstk.jaz.auction.AuctionService;
import pl.edu.pjwstk.jaz.category.CategoryEntity;
import pl.edu.pjwstk.jaz.category.CategoryRequest;
import pl.edu.pjwstk.jaz.category.CategoryService;
import pl.edu.pjwstk.jaz.photo.AddPhotoRequest;
import pl.edu.pjwstk.jaz.photo.PhotoService;
import pl.edu.pjwstk.jaz.user.UserEntity;
import pl.edu.pjwstk.jaz.user.UserService;


@PreAuthorize("hasAnyAuthority('admin')")
@RestController
public class Add {
    final CategoryService categoryService;
    final AuctionService auctionService;
    final UserService userService;
    final PhotoService photoService;

    public Add(CategoryService categoryService, AuctionService auctionService, UserService userService, PhotoService photoService) {
        this.categoryService = categoryService;
        this.auctionService = auctionService;
        this.userService = userService;
        this.photoService = photoService;
    }

    @PostMapping("/add/category")
    public ResponseEntity<String> addCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryEntity categoryEntity = categoryService.findByName(categoryRequest.getName());

        if(categoryEntity != null){
            return new ResponseEntity<>("Category with the same name already created", HttpStatus.CONFLICT);
        }
        else{
            categoryService.saveCategory(categoryRequest.getName());
            return new ResponseEntity<>("Category created", HttpStatus.CREATED);
        }
    }
    @PreAuthorize("hasAnyAuthority('user')")
    @PostMapping("/add/auction")
    public ResponseEntity<Void> addAuction(@RequestBody AddAuctionRequest request){
        UserEntity author = userService.getLoggedUser();
        CategoryEntity categoryEntity = categoryService.findByName(request.getCategory());
        if(categoryEntity==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            var auction = auctionService.saveAuction(request.getTitle(),request.getDescription(), request.getPrice(), author,
                    categoryEntity, request.getParameterKey(), request.getParameterValue());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("id",auction.getId().toString());
            return new ResponseEntity<>(responseHeaders,HttpStatus.CREATED);
        }
    }
    @PreAuthorize("hasAnyAuthority('user')")
    @PostMapping("/add/photo")
    public ResponseEntity<Void> addPhoto(@RequestBody AddPhotoRequest request){
        AuctionEntity auctionEntity = auctionService.findById(request.getAuction_id());
        if(userService.getLoggedUser().getId().equals(auctionEntity.getAuthor().getId()) ||
                userService.getLoggedUser().getAuthorities().contains("admin")) {
            photoService.savePhoto(request.getLink(), auctionEntity);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }
}
