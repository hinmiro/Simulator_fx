package dao;

import datasource.MariadbConnection;
import entity.Simu;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * This class is responsible for performing database operations related to the Simu entity.
 * It uses the EntityManager from the MariadbConnection class to interact with the database.
 */
public class SimuDao {

    /**
     * Persists a Simu entity to the database.
     * @param s the Simu entity to be persisted
     */
    public void persist(Simu s) {
        EntityManager em = datasource.MariadbConnection.getInstance();
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        MariadbConnection.closeEntityManager();
    }

    /**
     * Finds a Simu entity in the database by its id.
     * @param id the id of the Simu entity to be found
     * @return the found Simu entity
     */
    public Simu findSimu(int id) {
        EntityManager em = datasource.MariadbConnection.getInstance();
        Simu s = em.find(Simu.class, id);
        MariadbConnection.closeEntityManager();
        return s;
    }

    /**
     * Updates a Simu entity in the database.
     * @param s the Simu entity to be updated
     */
    public void update(Simu s) {
        EntityManager em = datasource.MariadbConnection.getInstance();
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
        MariadbConnection.closeEntityManager();

    }

    /**
     * Deletes a Simu entity from the database.
     * @param s the Simu entity to be deleted
     */
    public void delete(Simu s) {
        EntityManager em = datasource.MariadbConnection.getInstance();
        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
        MariadbConnection.closeEntityManager();
    }

    /**
     * Finds all Simu entities in the database.
     * @return a list of all Simu entities
     */
    public List<Simu> findAll() {
        EntityManager em = datasource.MariadbConnection.getInstance();
        List<Simu> result = em.createQuery("SELECT s FROM Simu s", Simu.class).getResultList();
        MariadbConnection.closeEntityManager();
        return result;
    }

    /**
     * Finds a specified amount of Simu entities in the database.
     * @param n the amount of Simu entities to be found
     * @return a list of found Simu entities
     */
    public List<Simu> findAmount(int n) {
        EntityManager em = datasource.MariadbConnection.getInstance();
        List<Simu> result = em.createQuery("SELECT s FROM Simu s ORDER BY s.id DESC", Simu.class)
                .setMaxResults(n)
                .getResultList();
        MariadbConnection.closeEntityManager();
        return result;
    }

    /**
     * Gets the maximum id from the Simu entities in the database.
     * @return the maximum id
     */
    public int getMaxIdFromDatabase() {
        EntityManager em = datasource.MariadbConnection.getInstance();
        Integer maxId = (Integer) em.createQuery("SELECT MAX(s.id) FROM Simu s").getSingleResult();
        MariadbConnection.closeEntityManager();
        return maxId != null ? maxId : 0;
    }
}
