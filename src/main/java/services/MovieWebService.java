package services;

import entity.Movie;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService
public interface MovieWebService {

    List<Movie> getAllMovies();
    List<Movie> getMoviesContains(String name);

}
