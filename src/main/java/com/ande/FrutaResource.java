package com.ande;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

@Path("frutas")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FrutaResource {

    @Inject
    EntityManager entityManager;

    @GET
    public List<Fruta> get() {
        return entityManager.createNamedQuery("Fruta.findAll", Fruta.class).getResultList();
    }

    @GET
    @Path("{id}")
    public Fruta getFruta(Integer id) {
        Fruta entityFruta = entityManager.find(Fruta.class, id);
        return entityFruta;
    }
    
    @POST
    @Transactional
    public Response create(Fruta fruta) {
        entityManager.persist(fruta);
        return Response.ok(fruta).status(201).build();
    }

    @POST
    @Path("caja")
    @Transactional
    public Response setCreateCaja(Caja caja) {
        entityManager.persist(caja);
        return Response.ok(caja).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Fruta update (Integer id, Fruta fruta) {
        Fruta entityFruta = entityManager.find(Fruta.class, id);

        if (entityFruta == null) {
            throw new WebApplicationException("La fruta no se encuentra " + id, 404);
        }

        entityFruta.setNombre(fruta.getNombre());
        entityManager.persist(entityFruta);

        return entityFruta;
    }

    // @PATCH
    // @Transactional

    // public Fruta update (Integer id, Fruta fruta) {
    //     Fruta entityFruta = entityManager.find(Fruta.class, id);
    //     entityFruta.setNombre(fruta.getNombre());
    //     return entityFruta;
    // }
}
