package cz.spsmb.rest;

import cz.spsmb.dao.CarRepository;
import cz.spsmb.dao.CatRepository;
import cz.spsmb.model.Car;
import cz.spsmb.model.Cat;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/Cars")
public class CarResource {

    @Inject
    CarRepository carRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Car> cars = carRepository.listAll();
        return Response.ok().entity(cars).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Car car = carRepository.findById(id);
        return Response.ok().entity(car).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        carRepository.deleteById(id);
        return Response.ok().entity("ok").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Car car) {
        car.setId(0l);
        if (car.getBrand() != null && car.getModel() != null && car.getColor() != null) {
            carRepository.persist(car);
            return Response.ok().entity("ok").build();
        } else {
            return Response.status(400).entity("Cat must have attributes \"name\" , \"age\" and \"color\".").build();
        }

    }
}
