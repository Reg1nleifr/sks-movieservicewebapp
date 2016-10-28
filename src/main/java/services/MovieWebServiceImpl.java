package services;

import com.sun.tools.javac.util.List;
import entity.Movie;

import javax.inject.Inject;

/**
 * Created by Flo on 28/10/2016.
 */
public class MovieWebServiceImpl implements MovieWebService {

    @Inject
    private MovieService movieService;

    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
