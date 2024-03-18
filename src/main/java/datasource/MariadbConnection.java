package datasource;

import jakarta.persistence.*;

/**
 * This class is responsible for managing the connection to the MariaDB database.
 * It uses the EntityManagerFactory to create EntityManager instances and stores them in a ThreadLocal variable.
 * This ensures that each thread has its own EntityManager instance.
 */
public class MariadbConnection {
    /**
     * The EntityManagerFactory used to create EntityManager instances.
     */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankMariadb");

    /**
     * A ThreadLocal variable that stores the EntityManager for each thread.
     */
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    /**
     * Returns the EntityManager instance for the current thread.
     * If no EntityManager exists for the current thread, a new one is created and stored in the ThreadLocal variable.
     *
     * @return the EntityManager instance for the current thread
     */
    public static EntityManager getInstance() {
        EntityManager em = threadLocal.get();
        if (em==null) {
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    /**
     * Closes the EntityManager for the current thread and removes it from the ThreadLocal variable.
     */
    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null) {
            em.close();
            threadLocal.remove();
        }
    }
}