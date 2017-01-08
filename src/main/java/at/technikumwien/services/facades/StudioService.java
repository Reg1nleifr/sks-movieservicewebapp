package at.technikumwien.services.facades;

import at.technikumwien.entity.Studio;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Flo & Stefan
 * Studio Service
 */
@Stateless
@SecurityDomain("MovieSD")
public class StudioService {

    @PersistenceContext
    private EntityManager entityManager;

    @RolesAllowed("MSRead")
    public List<Studio> getAll(){
        return entityManager.createNamedQuery("Studio.getAll", Studio.class)
                .getResultList();
    }

    @RolesAllowed("MSWrite")
    public void persist(Studio saveStudio) {
        entityManager.persist(saveStudio);
    }

    @RolesAllowed("MSRead")
    public Studio getById(int id) {
        return entityManager.createNamedQuery("Studio.getById", Studio.class)
                .setParameter("id", id).getSingleResult();
    }

    @RolesAllowed("MSWrite")
    public void deleteById(int id) {
        entityManager.createNamedQuery("Studio.deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
