package datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MariadbConnectionTest {
    private static EntityManager em;
    private static ThreadLocal<EntityManager> threadLocal;
    private static EntityManagerFactory emf;
    @BeforeAll
    public static void setUp() {
        System.out.println("***Starting tests***");
        threadLocal = new ThreadLocal<>();
        emf = Persistence.createEntityManagerFactory("bankMariadb");

    }
    @Test
    void getInstanceTest() {
        System.out.println("<Get instance test>");
        em = MariadbConnection.getInstance();
        assertEquals(em, MariadbConnection.getInstance());
    }

    @Test
    void closeEntityManagerTest() {
        em = threadLocal.get();
        if (em == null)
            assertEquals(null, threadLocal.get());
        if (em != null) {
            em.close();
            threadLocal.remove();
        }
    }
}