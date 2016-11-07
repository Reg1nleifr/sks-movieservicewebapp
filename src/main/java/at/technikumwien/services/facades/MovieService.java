package at.technikumwien.services.facades;

import at.technikumwien.entity.Actor;
import at.technikumwien.entity.Movie;
import at.technikumwien.entity.Studio;
import org.jboss.logging.Logger;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 26.10.2016.
 */
@Stateless
public class MovieService {

    private final Logger logger = Logger.getLogger(MovieService.class);
    private List<Movie> cachedMovies;

    @PersistenceContext
    private EntityManager entityManager;

    public Movie getById(int id) {
        if(cachedMovies == null) {
            getAll();
        }
        return cachedMovies.get(id);
    }


    public List<Movie> getAll() {
        if(cachedMovies == null) {
            cachedMovies = entityManager.createNamedQuery("Movie.getAll", Movie.class)
                    .getResultList();
        }
        return cachedMovies;
    }

    public void persistTransactionally(List<Movie> movies) {
        for (Movie movie: movies) {
            List<Actor> actors = getPersistedActors(movie.getActors());
            Studio studio = getPersistedStudio(movie.getStudio());
            if (movieExists(movie))
                throw new EJBException(String.format("Movie %s couldn't be added, reason: already exists!", movie.getTitle()));

            movie.setActors(actors);
            movie.setStudio(studio);
            entityManager.persist(movie);
        }
    }

    private List<Actor> getPersistedActors(List<Actor> actors) {
        List<Actor> persistedActores = new ArrayList<>();
        for (Actor actor: actors) {
            // Throws an exceptoin if there is no or more than one result on the db
            Actor persistedActor = entityManager.createNamedQuery("Actor.getActorCount", Actor.class)
                    .setParameter("birthdate", actor.getBirthdate())
                    .setParameter("firstname", actor.getFirstname())
                    .setParameter("lastname", actor.getLastname())
                    .setParameter("sex", actor.getSex()).getSingleResult();
            persistedActores.add(persistedActor);
        }
        return persistedActores;
    }

    private Studio getPersistedStudio(Studio studio) {
        return entityManager.createNamedQuery("Studio.getStudioCount", Studio.class)
                .setParameter("name", studio.getName())
                .setParameter("countrycode", studio.getCountrycode())
                .setParameter("postcode", studio.getPostcode()).getSingleResult();
    }

    private boolean movieExists(Movie movie) {
        return  0 != entityManager.createNamedQuery("Movie.getMovieCount", Movie.class)
                .setParameter("title", movie.getTitle())
                .setParameter("description", movie.getDescription())
                .setParameter("genre", movie.getGenre())
                .setParameter("releaseyear", movie.getReleaseYear())
                .setParameter("length", movie.getLength()).getResultList().size();
    }

    public List<Movie> contains(String name) {
        return entityManager.createNamedQuery("Movie.getByTitle", Movie.class)
                .setParameter("name", name.toLowerCase()).getResultList();
    }
}
