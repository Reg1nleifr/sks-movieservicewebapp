package at.technikumwien.services.facades;

import at.technikumwien.entity.Actor;
import at.technikumwien.entity.Movie;
import at.technikumwien.entity.Studio;
import org.jboss.annotation.security.SecurityDomain;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flo & Stefan
 * Movie Service
 */
@Stateless
@SecurityDomain("MovieSD")
public class MovieService {

    private final Logger logger = Logger.getLogger(MovieService.class);
    private List<Movie> cachedMovies;

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    SessionContext ctx;

    @RolesAllowed("MSRead")
    public Movie getById(int id) {
        if(cachedMovies == null) {
            getAll();
        }
        return cachedMovies.get(id);
    }


    @RolesAllowed("MSRead")
    public List<Movie> getAll() {
        logger.log(Logger.Level.INFO, ctx.getCallerPrincipal());

        if(cachedMovies == null) {
            cachedMovies = entityManager.createNamedQuery("Movie.getAll", Movie.class)
                    .getResultList();
        }
        return cachedMovies;
    }

    @RolesAllowed("MSWrite")
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

    @RolesAllowed("MSWrite")
    private List<Actor> getPersistedActors(List<Actor> actors) {
        List<Actor> persistedActors = new ArrayList<>();
        for (Actor actor: actors) {
            // Throws an exception if there is no or more than one result on the db
            Actor persistedActor = entityManager.createNamedQuery("Actor.getActor", Actor.class)
                    .setParameter("birthdate", actor.getBirthdate())
                    .setParameter("firstname", actor.getFirstname())
                    .setParameter("lastname", actor.getLastname())
                    .setParameter("sex", actor.getSex()).getSingleResult();
            persistedActors.add(persistedActor);
        }
        return persistedActors;
    }

    @RolesAllowed("MSRead")
    private Studio getPersistedStudio(Studio studio) {
        return entityManager.createNamedQuery("Studio.getStudio", Studio.class)
                .setParameter("name", studio.getName())
                .setParameter("countrycode", studio.getCountrycode())
                .setParameter("postcode", studio.getPostcode()).getSingleResult();
    }

    @RolesAllowed("MSRead")
    private boolean movieExists(Movie movie) {
        return  0 != entityManager.createNamedQuery("Movie.getMovie", Movie.class)
                .setParameter("title", movie.getTitle())
                .setParameter("description", movie.getDescription())
                .setParameter("genre", movie.getGenre())
                .setParameter("releaseyear", movie.getReleaseYear())
                .setParameter("length", movie.getLength()).getResultList().size();
    }

    @RolesAllowed("MSRead")
    public List<Movie> contains(String name) {
        return entityManager.createNamedQuery("Movie.getByTitle", Movie.class)
                .setParameter("name", name.toLowerCase()).getResultList();
    }
}
