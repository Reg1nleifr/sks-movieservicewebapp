package at.technikumwien.services.rest;

import at.technikumwien.controller.RestApplication;
import at.technikumwien.entity.Movie;
import at.technikumwien.services.MovieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Flo on 07/11/2016.
 */
@Path("/movies")
public class MovieResource extends RestApplication {

    @Inject
    private MovieService movieService;

    @GET
    @Path("/{movieId}") //resources/movies/getmovie/1?filter=abc
    @Produces({MediaType.TEXT_PLAIN})
    public String getMovie(@PathParam("movieId") int id, @QueryParam("filter") String filter) {
        return movieService.getById(id).toString();
    }


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> getAll() {
        return movieService.getAll();
    }

}
