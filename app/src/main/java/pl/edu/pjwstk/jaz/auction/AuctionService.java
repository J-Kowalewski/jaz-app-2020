package pl.edu.pjwstk.jaz.auction;

import liquibase.pro.packaged.A;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.category.CategoryEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class AuctionService {
    @PersistenceContext
    private final EntityManager entityManager;

    public AuctionService( EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    public void saveAuction(String title, String description, Double price){
        AuctionEntity auctionEntity = new AuctionEntity();
        auctionEntity.setTitle(title);
        auctionEntity.setDescription(description);
        auctionEntity.setPrice(price);

        entityManager.persist(auctionEntity);
    }
    public AuctionEntity findByName(String title){
        return entityManager.createQuery("SELECT a FROM AuctionEntity a WHERE a.title =: title", AuctionEntity.class)
                .setParameter("title", title)
                .getSingleResult();
    }
}
