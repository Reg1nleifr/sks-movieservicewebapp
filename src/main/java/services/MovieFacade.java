package services;

import entity.Movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Stefan on 26.10.2016.
 */
@Stateless
public class MovieFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Movie> getAll(){
        return entityManager.createNamedQuery("Movie.getAll", Movie.class)
                .getResultList();
    }

    public void persist(Movie saveMovie) {
        entityManager.persist(saveMovie);
    }

    public List<Movie> findByName(String name) {
        String searchName = "%"+name+"%";
        return entityManager.createNamedQuery("Movie.getByTitle", Movie.class)
                .setParameter("name", searchName).getResultList();
    }
}
