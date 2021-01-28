package pl.edu.pjwstk.jaz.auction;

import pl.edu.pjwstk.jaz.category.CategoryEntity;

public class EditAuctionRequest {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String parameterKey;
    private String parameterValue;

    public EditAuctionRequest(Long id, String title, String description, Double price, String category,
                              String parameterKey, String parameterValue) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.parameterKey = parameterKey;
        this.parameterValue = parameterValue;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
