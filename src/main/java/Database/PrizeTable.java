package Database;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.Prize;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class PrizeTable implements PrizeTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Prize> getListPrizeByDoctorId(Long id) {
        begin();
        List<Prize> ret = (List<Prize>) entityManager.createQuery("SELECT Prize from Prize a where a.id = ?1")
                .setParameter(1, id)
                .getResultList();
        commit();

        return ret;
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
