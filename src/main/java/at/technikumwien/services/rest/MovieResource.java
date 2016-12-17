package at.technikumwien.services.rest;

import at.technikumwien.entity.Movie;
import at.technikumwien.services.facades.MovieService;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by Flo on 07/11/2016.
 */
@SecurityDomain("MovieSD")
@RolesAllowed(value = "END_USER")
@Path("/movies")
public class MovieResource extends RestApplication {

    @Inject
    private MovieService movieService;
    @Context
    UriInfo uriInfo;

    @GET
    @Path("{movieId}") //resources/movies/getmovie/1?filter=abc
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Movie getMovie(@PathParam("movieId") int id, @QueryParam("filter") String filter) {
        return movieService.getById(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

}
