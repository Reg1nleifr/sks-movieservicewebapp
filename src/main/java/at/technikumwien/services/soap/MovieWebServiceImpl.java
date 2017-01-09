package at.technikumwien.services.soap;

import at.technikumwien.entity.Movie;
import at.technikumwien.services.facades.MovieService;

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
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @Override
    @WebMethod
    public List<Movie> getMoviesContains(String name)
    {
        return movieService.contains(name);
    }

    @Override
    @WebMethod
    public void persistMovies(List<Movie> movies) {
        movieService.persistTransactionally(movies);
    }

}