package pl.edu.pjwstk.jaz.endpoints;

import liquibase.pro.packaged.T;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.auction.AuctionEntity;
import pl.edu.pjwstk.jaz.auction.AuctionService;
import pl.edu.pjwstk.jaz.category.CategoryEntity;
import pl.edu.pjwstk.jaz.category.CategoryService;

import java.util.List;

@RestController
public class Explore {
    private final AuctionService auctionService;
    private final CategoryService categoryService;

    public Explore(AuctionService auctionService, CategoryService categoryService) {
        this.auctionService = auctionService;
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyAuthority('user')")
    @GetMapping("/explore")
    public List<AuctionEntity> explore(){
        return auctionService.findAllAuctions();
    }
    @GetMapping("/explore/auction/{id}")
    public AuctionEntity exploreAuction(@PathVariable Long id){
        return auctionService.findById(id);

    }
    @GetMapping("/explore/category/{id}")
    public CategoryEntity exploreCategory(@PathVariable Long id){
        return categoryService.findById(id);
    }
}
