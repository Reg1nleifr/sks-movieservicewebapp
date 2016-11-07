package at.technikumwien.services.facades;

import at.technikumwien.entity.Actor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Flo on 06/10/16.
 */
@Stateless
public class ActorService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Actor> getAll() {
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


}