package com.quipux.colegio.dao;

import com.quipux.colegio.models.HechizoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import static org.junit.jupiter.api.Assertions.*;

public class Reto2DaoTest {

    private static final String CONSULTA_POR_TIPO =
        "SELECT h FROM HechizoEntity h WHERE h.tipoMagia = :tipo";
    private static final String CONSULTA_POR_NOMBRE =
        "SELECT h FROM HechizoEntity h WHERE h.nombre = :nombre";

    private EntityManager entityManager;
    private TypedQuery<HechizoEntity> query;
    private HechizoDaoImpl dao;
    private Map<String, Object> parametros;
    private List<HechizoEntity> resultados;
    private String consultaEjecutada;
    private HechizoEntity hechizoPersistido;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void configurarDoblesDePrueba() throws ReflectiveOperationException {
        parametros = new HashMap<>();
        resultados = List.of();
        consultaEjecutada = null;
        hechizoPersistido = null;

        query = (TypedQuery<HechizoEntity>) Proxy.newProxyInstance(
                TypedQuery.class.getClassLoader(),
                new Class<?>[]{TypedQuery.class},
                (proxy, method, args) -> {
                    if ("setParameter".equals(method.getName())) {
                        parametros.put((String) args[0], args[1]);
                        return proxy;
                    }
                    if ("getResultList".equals(method.getName())) {
                        return resultados;
                    }
                    return null;
                });
        entityManager = (EntityManager) Proxy.newProxyInstance(
                EntityManager.class.getClassLoader(),
                new Class<?>[]{EntityManager.class},
                (proxy, method, args) -> {
                    if ("persist".equals(method.getName())) {
                        hechizoPersistido = (HechizoEntity) args[0];
                        return null;
                    }
                    if ("createQuery".equals(method.getName())) {
                        consultaEjecutada = (String) args[0];
                        return query;
                    }
                    return null;
                });

        dao = new HechizoDaoImpl();
        Field entityManagerField = HechizoDaoImpl.class.getDeclaredField("entityManager");
        entityManagerField.setAccessible(true);
        entityManagerField.set(dao, entityManager);
    }

    @Test
    public void daoDebeSerRepository() {
        boolean isRepository = HechizoDaoImpl.class.isAnnotationPresent(Repository.class);
        assertTrue(isRepository, "HechizoDaoImpl debe tener la anotación @Repository");
    }

    @Test
    public void guardarHechizoDebePersistirYDevolverElHechizo() {
        HechizoEntity hechizo = new HechizoEntity();

        HechizoEntity resultado = dao.guardarHechizo(hechizo);

        assertSame(hechizo, resultado);
        assertSame(hechizo, hechizoPersistido);
    }

    @Test
    public void buscarPorTipoDebeUsarParametroYDevolverResultados() {
        List<HechizoEntity> hechizos = List.of(new HechizoEntity());
        resultados = hechizos;

        List<HechizoEntity> resultado = dao.buscarPorTipo("fuego");

        assertSame(hechizos, resultado);
        assertEquals(CONSULTA_POR_TIPO, consultaEjecutada);
        assertEquals("fuego", parametros.get("tipo"));
    }

    @Test
    public void buscarPorNombreDebeUsarParametroYDevolverElPrimerResultado() {
        HechizoEntity hechizo = new HechizoEntity();
        resultados = List.of(hechizo);

        HechizoEntity resultado = dao.buscarPorNombre("Lumos");

        assertSame(hechizo, resultado);
        assertEquals(CONSULTA_POR_NOMBRE, consultaEjecutada);
        assertEquals("Lumos", parametros.get("nombre"));
    }

    @Test
    public void buscarPorNombreDebeDevolverNullSiNoHayResultados() {
        assertNull(dao.buscarPorNombre("Desconocido"));
        assertEquals(CONSULTA_POR_NOMBRE, consultaEjecutada);
        assertEquals("Desconocido", parametros.get("nombre"));
    }
}
