package pl.edu.pjwstk.jaz.auction;

import liquibase.pro.packaged.A;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.User;
import pl.edu.pjwstk.jaz.category.CategoryEntity;
import pl.edu.pjwstk.jaz.user.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuctionService {
    @PersistenceContext
    private final EntityManager entityManager;

    public AuctionService( EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    public void saveAuction(String title, String description, Double price, UserEntity author, CategoryEntity category){
        AuctionEntity auctionEntity = new AuctionEntity();
        auctionEntity.setTitle(title);
        auctionEntity.setDescription(description);
        auctionEntity.setPrice(price);
        auctionEntity.setAuthor(author);
        auctionEntity.setCategory(category);

        entityManager.persist(auctionEntity);
    }
    public AuctionEntity findByName(String title){
        return entityManager.createQuery("SELECT a FROM AuctionEntity a WHERE a.title =: title", AuctionEntity.class)
                .setParameter("title", title)
                .getSingleResult();
    }
    public AuctionEntity findById(Long id){
        return entityManager.createQuery("SELECT a FROM AuctionEntity a WHERE a.id =: id", AuctionEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public List<AuctionEntity> findAllAuctions(){
        return entityManager.createQuery("SELECT a FROM AuctionEntity a", AuctionEntity.class)
                .getResultList();
    }
    public AuctionEntity update(AuctionEntity auctionEntity){
        return entityManager.merge(auctionEntity);
    }
}
