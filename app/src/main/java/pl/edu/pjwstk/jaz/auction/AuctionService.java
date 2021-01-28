package pl.edu.pjwstk.jaz.auction;

import liquibase.pro.packaged.A;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.User;
import pl.edu.pjwstk.jaz.category.CategoryEntity;
import pl.edu.pjwstk.jaz.parameter.AuctionParameterEntity;
import pl.edu.pjwstk.jaz.parameter.ParameterEntity;
import pl.edu.pjwstk.jaz.user.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuctionService {
    @PersistenceContext
    private final EntityManager entityManager;

    public AuctionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public AuctionEntity saveAuction(String title, String description, Double price, UserEntity author,
                            CategoryEntity category, String parameterKey, String parameterValue) {
        AuctionEntity auctionEntity = new AuctionEntity(title, description, price, category, author);
        entityManager.persist(auctionEntity);

        ParameterEntity parameterEntity = new ParameterEntity(parameterKey);
        entityManager.persist(parameterEntity);

        AuctionParameterEntity auctionParameterEntity = new AuctionParameterEntity(auctionEntity, parameterEntity, parameterValue);
        entityManager.persist(auctionParameterEntity);

        return auctionEntity;
    }

    public AuctionEntity findById(Long id) {
        try{
            return entityManager.createQuery("SELECT a FROM AuctionEntity a WHERE a.id =: id", AuctionEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }

    public List<Object[]> findAllAuctions() {
        return entityManager.createQuery("SELECT a.id,a.title,a.description,a.price,a.category.name,a.author.username, p.link FROM AuctionEntity a, PhotoEntity p WHERE p.auction.id=a.id", Object[].class)
                .getResultList();
    }

    public void update(AuctionEntity auctionEntity) {
        entityManager.merge(auctionEntity);
    }
    public AuctionParameterEntity findParameterById(Long id) {
        return entityManager.createQuery("SELECT p FROM AuctionParameterEntity p WHERE p.auction.id =: id", AuctionParameterEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public void updateParameter(AuctionParameterEntity auctionParameterEntity){
        ParameterEntity parameterEntity = auctionParameterEntity.getParameter();
        entityManager.merge(parameterEntity);
        entityManager.merge(auctionParameterEntity);
    }
}
