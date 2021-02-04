package pl.edu.pjwstk.jaz.auction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAuctionRequest {
    private String title;
    private String description;
    private Double price;
    private String categoryName;
//    private String parameterKey;
//    private String parameterValue;
    private List<String> photoList;
    private HashMap<String,String> parameterMap;

    public AddAuctionRequest(String title, String description, Double price, String category,
                             List<String> photoList, HashMap<String,String> parameterMap) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryName = category;
        this.photoList = photoList;
        this.parameterMap = parameterMap;
    }

    public HashMap<String, String> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(HashMap<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
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
        return categoryName;
    }

    public void setCategory(String category) {
        this.categoryName = category;
    }

}
