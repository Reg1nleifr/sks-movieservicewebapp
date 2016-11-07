package at.technikumwien.managedbeans;

import at.technikumwien.entity.Movie;
import at.technikumwien.services.facades.MovieService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Stefan on 26.10.2016.
 */
@RequestScoped
@Named("reqMovie")
public class MovieManaged {
    @Inject
    MovieService movieService;

    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

}

