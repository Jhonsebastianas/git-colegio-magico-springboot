package com.quipux.colegio.services;

import com.quipux.colegio.manager.HechizoManager;
import com.quipux.colegio.models.HechizoEntity;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

        // RETO 4.1.2: Usa try/catch.
        // Si el manager lanza Exception, devuelve BAD_REQUEST (400).
        // Si todo sale bien, devuelve CREATED (201) y la entidad.

        try {
            HechizoEntity hechizoGuardado =
                    hechizoManager.registrarHechizo(hechizo);

            return Response.status(Response.Status.CREATED)
                    .entity(hechizoGuardado)
                    .build();

        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // RETO 4.2: Endpoint para buscar hechizos por tipo
    // Ejemplo: /hechizos?tipo=Fuego
    // Usa la anotación HTTP correcta para "leer". (PISTA: @G...)

    @GET
    public Response buscarPorTipo(
            @QueryParam("tipo") String tipo) {

        // RETO 4.3: @QueryParam permite recibir
        // el parámetro "tipo" de la URL.

        List<HechizoEntity> lista =
                hechizoManager.buscarMagia(tipo);

        return Response.ok(lista).build();
    }
}