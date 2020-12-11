package pl.edu.pjwstk.jaz;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class ReadinessController {
    @PersistenceContext
    private EntityManager entityManager;

    @PreAuthorize("hasAnyAuthority('view-readiness','admin')")
    @GetMapping("is-ready")
    @Transactional
    public void readinessTest(){

    }
}