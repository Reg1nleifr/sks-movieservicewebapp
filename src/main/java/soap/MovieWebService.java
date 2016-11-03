package soap;

import entity.Movie;
import xml.helpers.MovieRootElement;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService
interface MovieWebService {
    MovieRootElement getAllMovies();
    MovieRootElement getMoviesContains(String name);
    String persistMoviesFromSoap(String soapInput);
    String persistMovies(List<Movie> movies);
}