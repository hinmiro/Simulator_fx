package dao;

import datasource.MariadbConnection;
import entity.Simu;
import jakarta.persistence.EntityManager;
import java.util.List;
public class SimuDao {

    public void persist(Simu s) {
        EntityManager em = datasource.MariadbConnection.getInstance();
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        MariadbConnection.closeEntityManager();
    }

    public Simu findSimu(int id) {
        EntityManager em = datasource.MariadbConnection.getInstance();
        Simu s = em.find(Simu.class, id);
        MariadbConnection.closeEntityManager();
        return s;
    }

    public void update(Simu s) {
        EntityManager em = datasource.MariadbConnection.getInstance();
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
        MariadbConnection.closeEntityManager();

    }

    public void delete(Simu s) {
        EntityManager em = datasource.MariadbConnection.getInstance();
        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
        MariadbConnection.closeEntityManager();
    }

    public List<Simu> findAll() {
        EntityManager em = datasource.MariadbConnection.getInstance();
        List<Simu> result = em.createQuery("SELECT s FROM Simu s", Simu.class).getResultList();
        MariadbConnection.closeEntityManager();
        return result;
    }
}