package pl.edu.pjwstk.jaz.category;

import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    @PersistenceContext
    private final EntityManager entityManager;

    public CategoryService( EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    public void saveCategory(String name){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);

        entityManager.persist(categoryEntity);
    }
    public CategoryEntity findByName(String name){
        try {
            return entityManager.createQuery("SELECT c FROM CategoryEntity c WHERE c.name =: name", CategoryEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    public CategoryEntity findById(Long id){
        try{
            return entityManager.createQuery("SELECT c FROM CategoryEntity c WHERE c.id =: id", CategoryEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }

    }
    public List<CategoryEntity> findAll(){
        return entityManager.createQuery("SELECT c FROM CategoryEntity c",CategoryEntity.class)
                .getResultList();
    }
    public CategoryEntity update(CategoryEntity categoryEntity){
        return entityManager.merge(categoryEntity);
    }
}