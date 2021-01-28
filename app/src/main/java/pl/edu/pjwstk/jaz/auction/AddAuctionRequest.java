package pl.edu.pjwstk.jaz.auction;

public class AddAuctionRequest {
    private String title;
    private String description;
    private Double price;
    private String categoryName;
    private String parameterKey;
    private String parameterValue;

    public AddAuctionRequest(String title, String description, Double price, String category,
                             String parameterKey, String parameter_Value) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryName = category;
        this.parameterKey = parameterKey;
        this.parameterValue = parameter_Value;
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
        return categoryName;
    }

    public void setCategory(String category) {
        this.categoryName = category;
    }

}
