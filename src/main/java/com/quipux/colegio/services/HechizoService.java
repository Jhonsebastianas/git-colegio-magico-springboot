package com.quipux.colegio.services;

import com.quipux.colegio.manager.HechizoManager;
import com.quipux.colegio.models.HechizoEntity;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// RETO 4: Expón las "Puertas del Colegio" (APIs REST) usando anotaciones de Jakarta
@Path("/hechizos")                        // (1) Ruta base del endpoint JAX-RS
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component                                // (2) Componente gestionado por Spring
public class HechizoService {

    @Autowired
    private HechizoManager hechizoManager;

    // RETO 4.1: Endpoint para crear un hechizo.
    @POST                                 // (3) Verbo HTTP para creación
    public Response crearHechizo(HechizoEntity hechizo) {
        try {
            HechizoEntity creado = hechizoManager.registrarHechizo(hechizo);
            return Response.status(Response.Status.CREATED).entity(creado).build();    // (4) Código HTTP 201
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build(); // (5) Código HTTP 400
        }
    }

    // RETO 4.2 y 4.3: Endpoint para buscar hechizos por tipo (ej: /hechizos?tipo=Fuego)
    @GET                                  // (6) Verbo HTTP para lectura
    public Response buscarPorTipo(@QueryParam("tipo") String tipo) {   // (7) Parámetro de consulta QueryParam
        List<HechizoEntity> lista = hechizoManager.buscarMagia(tipo);
        return Response.ok(lista).build();
    }
}