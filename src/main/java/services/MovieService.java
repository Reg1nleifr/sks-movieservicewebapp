package services;

import entity.Actor;
import entity.Movie;
import entity.Studio;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Stefan on 26.10.2016.
 */
@Stateless
public class MovieService {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Movie> getAll(){
        return entityManager.createNamedQuery("Movie.getAll", Movie.class)
                .getResultList();
    }

    //TODO: Actor / Studio rausladen -> managed
    public void persistTransactionally(List<Movie> movies) {
        for (Movie movie: movies) {
            if(actorsExist(movie.getActors())
                    && studioExists(movie.getStudio())
                    && !movieExists(movie)) {
                entityManager.persist(movie);
            }
            else {
                throw new EJBException("Ich mag mich nicht persistieren!");
            }
        }
    }

    private boolean actorsExist(List<Actor> actors) {
        for (Actor actor: actors) {
            if(0 == entityManager.createNamedQuery("Actor.getActorCount", Long.class)
                    .setParameter("birthdate", actor.getBirthdate())
                    .setParameter("firstname", actor.getFirstname())
                    .setParameter("lastname", actor.getLastname())
                    .setParameter("sex", actor.getSex()).getFirstResult()) {
                return false;
            }
        }
        return true;
    }

    private boolean movieExists(Movie movie) {
        return 1 <= entityManager.createNamedQuery("Movie.getMovieCount", Long.class)
                    .setParameter("title", movie.getTitle())
                    .setParameter("description", movie.getDescription())
                    .setParameter("genre", movie.getGenre())
                    .setParameter("releaseyear", movie.getReleaseYear())
                    .setParameter("length", movie.getLength()).getFirstResult();
    }

    private boolean studioExists(Studio studio) {
        return 1 <= entityManager.createNamedQuery("Studio.getStudioCount", Long.class)
                .setParameter("name", studio.getName())
                .setParameter("countrycode", studio.getCountrycode())
                .setParameter("postcode", studio.getPostcode()).getFirstResult();
    }

    public List<Movie> contains(String name) {
        return entityManager.createNamedQuery("Movie.getByTitle", Movie.class)
                .setParameter("name", name.toLowerCase()).getResultList();
    }
}
