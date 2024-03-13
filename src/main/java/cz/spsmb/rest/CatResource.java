package cz.spsmb.rest;

import cz.spsmb.dao.CatRepository;
import cz.spsmb.model.Cat;
import cz.spsmb.model.Person;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cats")
public class CatResource {

    @Inject
    CatRepository catRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Cat> cats = catRepository.listAll();
        return Response.ok().entity(cats).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Cat cat = catRepository.findById(id);
        return Response.ok().entity(cat).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        catRepository.deleteById(id);
        return Response.ok().entity("ok").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Cat cat) {
        cat.setId(0l);
        if (cat.getName() != null && cat.getAge() > 0 && cat.getColor() != null) {
            catRepository.persist(cat);
            return Response.ok().entity("ok").build();
        } else {
            return Response.status(400).entity("Cat must have attributes \"name\" , \"age\" and \"color\".").build();
        }

    }
}
