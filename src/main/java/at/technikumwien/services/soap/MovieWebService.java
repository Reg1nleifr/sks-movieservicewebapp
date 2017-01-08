package at.technikumwien.services.soap;

import at.technikumwien.entity.Movie;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by Flo & Stefan
 * Web Service Interface
 */
@WebService
interface MovieWebService {
    List<Movie> getAllMovies();
    List<Movie> getMoviesContains(String name);
    void persistMovies(List<Movie> movies);
}