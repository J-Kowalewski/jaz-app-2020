package pl.edu.pjwstk.jaz.parameter;

import pl.edu.pjwstk.jaz.auction.AuctionEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "auction_parameter")
public class AuctionParameterEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private AuctionEntity auction;
    @Id
    @OneToOne
    @JoinColumn(name = "parameter_id", referencedColumnName = "id")
    private ParameterEntity parameter;

    private String value;

    public AuctionParameterEntity(AuctionEntity auction, ParameterEntity parameter, String value) {
        this.auction = auction;
        this.parameter = parameter;
        this.value = value;
    }

    public AuctionParameterEntity() {

    }

    public AuctionEntity getAuction() {
        return auction;
    }

    public void setAuction(AuctionEntity auction) {
        this.auction = auction;
    }

    public ParameterEntity getParameter() {
        return parameter;
    }

    public void setParameter(ParameterEntity parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
