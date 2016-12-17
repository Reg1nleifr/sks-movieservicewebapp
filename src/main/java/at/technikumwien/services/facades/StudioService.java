package at.technikumwien.services.facades;

import at.technikumwien.entity.Studio;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Stefan on 26.10.2016.
 */
@Stateless
@SecurityDomain("MovieSD")
@RolesAllowed("END_USER")
public class StudioService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Studio> getAll(){
        return entityManager.createNamedQuery("Studio.getAll", Studio.class)
                .getResultList();
    }

    public void persist(Studio saveStudio) {
        entityManager.persist(saveStudio);
    }

    public List<Studio> findByName(String name) {
        String searchName = "%"+name+"%";
        return entityManager.createNamedQuery("Studio.getByName", Studio.class)
                .setParameter("name", searchName).getResultList();
    }

    public Studio getById(int id) {
        return entityManager.createNamedQuery("Studio.getById", Studio.class)
                .setParameter("id", id).getSingleResult();
    }

    public void deleteById(int id) {
        entityManager.createNamedQuery("Studio.deleteById")
                .executeUpdate();
    }
}
