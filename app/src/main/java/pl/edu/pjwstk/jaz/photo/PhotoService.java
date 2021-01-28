package pl.edu.pjwstk.jaz.photo;


import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.auction.AuctionEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PhotoService {
    @PersistenceContext
    private final EntityManager entityManager;

    public PhotoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<String> getPhotos(Long id){
        return entityManager.createQuery("SELECT p.link FROM PhotoEntity p WHERE p.auction.id =:id", String.class)
                .setParameter("id", id)
                .getResultList();
    }
    public void savePhoto(String link, AuctionEntity auctionEntity){
        PhotoEntity photoEntity = new PhotoEntity(link,auctionEntity);
        entityManager.persist(photoEntity);
    }
}
