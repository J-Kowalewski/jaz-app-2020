package pl.edu.pjwstk.jaz.category;

import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
        return entityManager.createQuery("SELECT c FROM CategoryEntity c WHERE c.name =: name", CategoryEntity.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}