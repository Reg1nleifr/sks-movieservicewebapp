package at.technikumwien.services.facades;

import at.technikumwien.entity.Actor;
import org.jboss.logging.Logger;
import org.jboss.security.annotation.SecurityDomain;

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
@RolesAllowed("END_USER")
public class ActorService {

    private final Logger logger = Logger.getLogger(ActorService.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private SessionContext sessionContext;



    public List<Actor> getAll() {
        logger.info(sessionContext.getCallerPrincipal());
        //ctx.isCallerInRole("END_USER")

        return entityManager.createNamedQuery("Actor.getAll", Actor.class)
                .getResultList();
    }

    public void persist(Actor saveActor) {
        entityManager.persist(saveActor);
    }

    public List<Actor> findByName(String name) {
        String searchName = name;
        return entityManager.createNamedQuery("Actor.getByName", Actor.class)
                .setParameter("name", searchName).getResultList();
    }


    public void deleteById(int id) {
        entityManager.createNamedQuery("Actor.deleteById")
                .executeUpdate();
    }
}