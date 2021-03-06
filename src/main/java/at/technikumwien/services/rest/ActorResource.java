package at.technikumwien.services.rest;

import at.technikumwien.entity.Actor;
import at.technikumwien.services.facades.ActorService;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by Flo & Stefan
 * Rest Resource Actor
 */
@Path("actors")
public class ActorResource {


    @Inject
    private ActorService actorService;
    @Context
    UriInfo uriInfo;

    @GET
    @Path("/{actorId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Actor getActor(@PathParam("actorId") int id) {
        return actorService.getAll().get(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Actor> getAllActors() {
        return actorService.getAll();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postActor(Actor actor) {
        actorService.persist(actor);
        String result = actor.getId() + " : " + uriInfo.getPath();
        return Response.status(201).entity(result).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putActor(Actor studio) {
        actorService.persist(studio);
        return Response.status(201).entity(studio.getId()).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteActor(@QueryParam("id") int id) {
        actorService.deleteById(id);
        return Response.status(200).build();
    }
}
