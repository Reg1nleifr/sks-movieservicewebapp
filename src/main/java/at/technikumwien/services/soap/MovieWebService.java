package at.technikumwien.services.soap;

import at.technikumwien.entity.Movie;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService
interface MovieWebService {
    List<Movie> getAllMovies();

    List<Movie> getMoviesContains(String name);
    void persistMovies(List<Movie> movies);
}