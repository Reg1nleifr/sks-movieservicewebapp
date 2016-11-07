package at.technikumwien.services;

import at.technikumwien.entity.Studio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Stefan on 26.10.2016.
 */
@Stateless
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
}