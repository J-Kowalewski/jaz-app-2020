package pl.edu.pjwstk.jaz.auction;

import pl.edu.pjwstk.jaz.category.CategoryEntity;
import pl.edu.pjwstk.jaz.user.UserEntity;

import java.util.HashMap;
import java.util.List;

public class EditAuctionRequest {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private List<String> photoList;
    private HashMap<String,String> parameters;
    private Long version;

    public EditAuctionRequest(Long id, String title, String description, Double price, String category,
                              List<String> photoList, HashMap<String,String> parameters, Long version) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.photoList = photoList;
        this.parameters = parameters;
        this.version = version;
    }

    public EditAuctionRequest() {

    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
