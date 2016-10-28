package services;

import java.util.List;
import entity.Movie;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService(endpointInterface = "services.MovieWebService",
            serviceName = "MovieService",
            portName = "MovieServicePort")
public class MovieWebServiceImpl implements MovieWebService {

    @Inject
    private MovieService movieService;

    @WebMethod
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @WebMethod
    public List<Movie> getMoviesContains(String name)
    {
        return movieService.contains(name);
    }


    public void persistMoviesFromList(List<Movie> movies) {
        movies.forEach(movie -> movieService.persist(movie));
    }

}