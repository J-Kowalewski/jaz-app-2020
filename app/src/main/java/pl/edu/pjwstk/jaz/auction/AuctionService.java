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
import java.util.Set;

@Service
@Transactional
public class AuctionService {
    @PersistenceContext
    private final EntityManager entityManager;

    public AuctionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public AuctionEntity saveAuction(String title, String description, Double price, UserEntity author,
                            CategoryEntity category) {
        AuctionEntity auctionEntity = new AuctionEntity(title, description, price, category, author);
        entityManager.persist(auctionEntity);

        return auctionEntity;
    }
    public ParameterEntity saveParameter(String key){
        ParameterEntity parameterEntity = new ParameterEntity(key);
        entityManager.persist(parameterEntity);
        return parameterEntity;
    }
    public AuctionParameterEntity saveAuctionParameter(AuctionEntity auction, ParameterEntity parameter, String value){
        AuctionParameterEntity auctionParameterEntity = new AuctionParameterEntity(auction,parameter,value);
        entityManager.persist(auctionParameterEntity);
        return auctionParameterEntity;
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

    public List<AuctionEntity> findAllAuctions() {
        return entityManager.createQuery("SELECT a FROM AuctionEntity a", AuctionEntity.class)
                .getResultList();
    }

    public void update(AuctionEntity auctionEntity) {
        entityManager.merge(auctionEntity);
    }

    public AuctionParameterEntity findAuctionParameterById(Long id) {
        try {
            return entityManager.createQuery("SELECT p FROM AuctionParameterEntity p WHERE p.auction.id =: id", AuctionParameterEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }
    public ParameterEntity findParameterByKey(String key) {
        try {
            return entityManager.createQuery("SELECT p FROM ParameterEntity p WHERE p.key =: key", ParameterEntity.class)
                    .setParameter("key", key)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    public void updateParameter(AuctionParameterEntity auctionParameterEntity){
        ParameterEntity parameterEntity = auctionParameterEntity.getParameter();
        entityManager.merge(parameterEntity);
        entityManager.merge(auctionParameterEntity);
    }
}
