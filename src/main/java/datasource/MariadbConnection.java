package datasource;

import jakarta.persistence.*;

public class MariadbConnection {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankMariadb");
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    public static EntityManager getInstance() {
        EntityManager em = threadLocal.get();
        if (em==null) {
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null) {
            em.close();
            threadLocal.remove();
        }
    }
}