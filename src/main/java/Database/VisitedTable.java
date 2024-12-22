package Database;

import Model.LocationVisited;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class VisitedTable implements VisitedTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Long> getListVisited(Long userID) {
        begin();
        List<Long> ret = entityManager.createQuery("SELECT a.loc_id from LocationVisited a where a.user_id = ?1")
                .setParameter(1, userID)
                .getResultList();
        commit();

        return ret;
    }

    @Override
    public void add(Long userId, Long locationId) {
        entityManager.persist(new LocationVisited(userId, locationId));
    }

    @Override
    public void del(Long userId, Long locationId) {
        entityManager.remove(new LocationVisited(userId, locationId));
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void begin() {
        entityManager.getTransaction().begin();
    }

    @Override
    public void commit() {
        entityManager.getTransaction().commit();
    }
}
