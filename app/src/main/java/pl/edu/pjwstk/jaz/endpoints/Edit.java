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
import pl.edu.pjwstk.jaz.photo.PhotoService;
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
    private final PhotoService photoService;

    public Edit(EntityManager entityManager, CategoryService categoryService, AuctionService auctionService,
                UserService userService, PhotoService photoService) {
        this.entityManager = entityManager;
        this.categoryService = categoryService;
        this.auctionService = auctionService;
        this.userService = userService;
        this.photoService = photoService;
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
        if(auction.getVersion().equals(request.getVersion())){
            if(userService.getLoggedUser().getId().equals(auction.getAuthor().getId()) ||
                    userService.getLoggedUser().getAuthorities().contains("admin")){

                auction.setTitle(request.getTitle());
                auction.setDescription(request.getDescription());
                auction.setPrice(request.getPrice());
                auction.setCategory(category);
                auction.setVersion(auction.getVersion()+1);
                auctionService.update(auction);

                photoService.removePhotos(request.getId());
                var photoLinks = request.getPhotoList();
                photoLinks.forEach(s -> {
                    photoService.savePhoto(s,auction);
                });

                var requestParameterMap = request.getParameters();
                auctionService.deleteAuctionParameters(auction.getId());

                requestParameterMap.forEach((key, value) -> {
                    var parameterEntity = auctionService.findParameterByKey(key);
                    if(parameterEntity!=null){
                        System.out.println("Parameter with key: "+key+" already exists");
                    }
                    else{
                        parameterEntity = auctionService.saveParameter(key);
                    }
                    auctionService.saveAuctionParameter(auction,parameterEntity,value);
                });
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
