package pl.edu.pjwstk.jaz.photo;

public class AddPhotoRequest {
    private String link;
    private Long auction_id;

    public AddPhotoRequest(Long auction_id, String link) {
        this.link = link;
        this.auction_id = auction_id;
    }

    public AddPhotoRequest() {
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }
}
