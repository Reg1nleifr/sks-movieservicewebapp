package services;

import entity.Actor;
import entity.Movie;
import entity.Studio;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
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

    @TransactionAttribute(value=TransactionAttributeType.REQUIRED) //TODO: Birthdate im Actor ist immer null, fixen, dann klappts! bzw movie existiert irgendwie nie..
    public void persistTransactionally(List<Movie> movies) throws Exception {
        for (Movie movie: movies) {
            if(actorsExist(movie.getActors())
                    && studioExists(movie.getStudio())
                    && !movieExists(movie)) {
                entityManager.persist(movie);
            }
            else {
                throw new Exception("Ich mag mich nicht persistieren!"); //TODO: Eigene Expcetion schmeißen!
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
