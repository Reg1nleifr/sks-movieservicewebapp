package at.technikumwien.services.facades;

import at.technikumwien.entity.Actor;
import org.jboss.annotation.security.SecurityDomain;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Flo on 06/10/16.
 */

@Stateless
@SecurityDomain("MovieSD")
public class ActorService {

    private final Logger logger = Logger.getLogger(ActorService.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private SessionContext sessionContext;

    @RolesAllowed("MSRead")
    public List<Actor> getAll() {
        logger.info(sessionContext.getCallerPrincipal());
        return entityManager.createNamedQuery("Actor.getAll", Actor.class)
                .getResultList();
    }

    @RolesAllowed("MSWrite")
    public void persist(Actor saveActor) {
        entityManager.persist(saveActor);
    }

    @RolesAllowed("MSRead")
    public List<Actor> findByName(String name) {
        String searchName = name;
        return entityManager.createNamedQuery("Actor.getByName", Actor.class)
                .setParameter("name", searchName).getResultList();
    }

    @RolesAllowed("MSWrite")
    public void deleteById(int id) {
        entityManager.createNamedQuery("Actor.deleteById")
                .executeUpdate();
    }
}