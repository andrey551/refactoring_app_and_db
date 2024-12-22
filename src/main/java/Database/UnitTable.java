package Database;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.CurrencyUnit;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UnitTable implements UnitTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public CurrencyUnit getCurrencyUnitById(Long id) {
        begin();
        CurrencyUnit ret = (CurrencyUnit) entityManager.createQuery("SELECT CurrencyUnit from CurrencyUnit a where a.id = ?1")
                .setParameter(1, id)
                .getResultList()
                .get(0);
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
