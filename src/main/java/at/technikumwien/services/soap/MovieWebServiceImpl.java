package at.technikumwien.services.soap;

import at.technikumwien.entity.Movie;
import at.technikumwien.services.facades.MovieService;
import at.technikumwien.services.soap.helpers.MovieRootElement;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService(endpointInterface = "at.technikumwien.services.soap.MovieWebService",
        serviceName = "MovieService",
        portName = "MovieServicePort")
public class MovieWebServiceImpl implements MovieWebService {

    @Inject
    private MovieService movieService;

    @Override
    @WebMethod
    public MovieRootElement getAllMovies() {
        return new MovieRootElement(movieService.getAll());
    }


    @Override
    @WebMethod
    public MovieRootElement getMoviesContains(String name)
    {
        return new MovieRootElement(movieService.contains(name));
    }

    @Override
    public void persistMovies(List<Movie> movies) {
        movieService.persistTransactionally(movies);
    }

}