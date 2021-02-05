package pl.edu.pjwstk.jaz.photo;


import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.auction.AuctionEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public List<String> getPhotoLinks(Long id){
        try {
            return entityManager.createQuery("SELECT p.link FROM PhotoEntity p WHERE p.auction.id =:id", String.class)
                    .setParameter("id", id)
                    .getResultList();
        }catch(NoResultException e){
            return null;
        }

    }

    public List<PhotoEntity> getPhotos(Long id){
        try {
            return entityManager.createQuery("SELECT p FROM PhotoEntity p WHERE p.auction.id =:id", PhotoEntity.class)
                    .setParameter("id", id)
                    .getResultList();
        }catch(NoResultException e){
            return null;
        }
    }

    public void savePhoto(String link, AuctionEntity auctionEntity){
        PhotoEntity photoEntity = new PhotoEntity(link,auctionEntity);
        entityManager.persist(photoEntity);
    }

    public void removePhotos(Long id){
        var auctionPhotos = getPhotos(id);
        auctionPhotos.forEach(entityManager::remove);
    }
}
