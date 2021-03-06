package at.technikumwien.services.rest;

import at.technikumwien.entity.Studio;
import at.technikumwien.services.facades.StudioService;
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
 * Rest Resource Studio
 */
@Path("studios")
public class StudioResource {

    @Inject
    private StudioService studioService;
    @Context
    UriInfo uriInfo;

    @GET
    @Path("/{studioId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Studio getStudio(@PathParam("studioId") int id) {
        return studioService.getById(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Studio> getAll() {
        return studioService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postStudio(Studio studio) {
        studioService.persist(studio);
        return Response.status(201).entity(studio.getId()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putStudio(Studio studio) {
        studioService.persist(studio);
        return Response.status(201).entity(studio.getId()).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteStudio(@QueryParam("id") int id) {
        studioService.deleteById(id);
        return Response.status(200).build();
    }
}
