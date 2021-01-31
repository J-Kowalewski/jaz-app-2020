package pl.edu.pjwstk.jaz.auction;

import pl.edu.pjwstk.jaz.category.CategoryEntity;
import pl.edu.pjwstk.jaz.user.UserEntity;

import java.util.List;

public class GetAuctionRequest {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String author;
    private String photo;
    private String parameterKey;
    private String parameterValue;

    public GetAuctionRequest(Long id, String title, String description, Double price, String category, String author,
                             String photo, String parameterKey, String parameterValue) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.author = author;
        this.photo = photo;
        this.parameterKey = parameterKey;
        this.parameterValue = parameterValue;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public GetAuctionRequest() {

    }


    public String getParameterKey() {
        return parameterKey;
    }

    public void setParameterKey(String parameterKey) {
        this.parameterKey = parameterKey;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
