package at.technikumwien.services.soap;

import at.technikumwien.entity.Movie;
import at.technikumwien.services.soap.helpers.MovieRootElement;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService
interface MovieWebService {
    MovieRootElement getAllMovies();
    MovieRootElement getMoviesContains(String name);

    void persistMoviesFromSoap(String soapInput) throws Exception;

    void persistMovies(List<Movie> movies);
}