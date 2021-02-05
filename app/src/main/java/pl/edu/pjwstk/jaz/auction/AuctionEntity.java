package pl.edu.pjwstk.jaz.auction;

import pl.edu.pjwstk.jaz.User;
import pl.edu.pjwstk.jaz.category.CategoryEntity;
import pl.edu.pjwstk.jaz.parameter.AuctionParameterEntity;
import pl.edu.pjwstk.jaz.user.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "auction")
public class AuctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Double price;

    private Long version;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private UserEntity author;

    @OneToMany(mappedBy = "auction")
    private Set<AuctionParameterEntity> parameters;

    public AuctionEntity(String title, String description, Double price, CategoryEntity category, UserEntity author){
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.author = author;
    }

    public AuctionEntity() {
    }

    public Set<AuctionParameterEntity> getParameters() {
        return parameters;
    }

    public void setParameters(Set<AuctionParameterEntity> parameters) {
        this.parameters = parameters;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
