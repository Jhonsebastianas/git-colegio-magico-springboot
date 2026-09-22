package com.quipux.colegio.services;

import com.quipux.colegio.manager.HechizoManager;
import com.quipux.colegio.models.HechizoEntity;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.glassfish.jersey.http.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.management.Query;

// RETO 4: Expón las "Puertas del Colegio" (APIs REST) usando anotaciones de Jakarta
// Agrega la anotación para la ruta principal: "/hechizos"
// PISTA: @P...
@Path("/hechizos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HechizoService {

    @Autowired
    private HechizoManager hechizoManager;

    // RETO 4.1: Endpoint para crear un hechizo.
    // Usa la anotación HTTP correcta para "crear". (PISTA: @P...)
    @POST 
    public Response crearHechizo(HechizoEntity hechizo) {
        // RETO 4.1.2: Usa try/catch. Si el manager lanza Exception, devuelve un Response con Status.BAD_REQUEST (400)
        // Si todo sale bien, devuelve un Response con Status.CREATED (201) y la entidad.
        // Escribe tu código aquí:
        try {
            return Response.status(Response.Status.CREATED).entity("201").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("400").build();
        }
    }

    // RETO 4.2: Endpoint para buscar hechizos por tipo, ej: /hechizos?tipo=Fuego
    // Usa la anotación HTTP correcta para "leer". (PISTA: @G...)
    @GET
    public Response buscarPorTipo(@QueryParam("tipo")/* Añade la anotación para leer el query param "tipo" */ String tipo) {
        // RETO 4.3: Usa la anotación correcta dentro de los parámetros de arriba (PISTA: @Q...)
        List<HechizoEntity> lista = hechizoManager.buscarMagia(tipo);
        return Response.ok(lista).build();
    }
}
