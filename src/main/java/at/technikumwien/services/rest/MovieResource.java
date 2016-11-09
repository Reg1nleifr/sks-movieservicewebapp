package at.technikumwien.services.rest;

import at.technikumwien.entity.Actor;
import at.technikumwien.entity.Movie;
import at.technikumwien.entity.Studio;
import at.technikumwien.services.facades.ActorService;
import at.technikumwien.services.facades.MovieService;
import at.technikumwien.services.facades.StudioService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Flo on 07/11/2016.
 */
public class MovieResource extends RestApplication {

    @Inject
    private MovieService movieService;

    @GET
    @Path("movies/{movieId}") //resources/movies/getmovie/1?filter=abc
    @Produces({MediaType.APPLICATION_JSON})
    public Movie getMovie(@PathParam("movieId") int id, @QueryParam("filter") String filter) {
        return movieService.getById(id);
    }

    @GET
    @Path("movies")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }


    @Inject
    private ActorService actorService;

    @GET
    @Path("/{actorId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Actor getActor(@PathParam("actorId") int id) {
        //return actorService.getById(id);
        return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Actor> getAllActors() {
        return actorService.getAll();
    }


    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postActor(Actor studio) {
        actorService.persist(studio);
        return Response.status(201).entity(studio.getId()).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putActor(Actor studio) {
        actorService.persist(studio);
        return Response.status(201).entity(studio.getId()).build();
    }

    @DELETE
    @Path("/deleteById")
    public Response deleteActor(@QueryParam("id") int id) {
        actorService.deleteById(id);
        return Response.status(200).build();
    }

    @Inject
    private StudioService studioService;

    @GET
    @Path("/{studioId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Studio getStudio(@PathParam("studioId") int id) {
        return studioService.getById(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Studio> getAll() {
        return studioService.getAll();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postStudio(Studio studio) {
        studioService.persist(studio);
        return Response.status(201).entity(studio.getId()).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putStudio(Studio studio) {
        studioService.persist(studio);
        return Response.status(201).entity(studio.getId()).build();
    }

    @DELETE
    @Path("/deleteById")
    public Response deleteStudio(@QueryParam("id") int id) {
        studioService.deleteById(id);
        return Response.status(200).build();
    }

}
