package services;

import com.sun.tools.javac.util.List;
import entity.Movie;

import javax.jws.WebService;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService
public interface MovieWebService {

    public List<Movie> getAllMovies();
}
