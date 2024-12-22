package Database;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.HospSpec;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class HospspecTable implements HospspecTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<HospSpec> getHospspecByHospitalId(Long id) {
        begin();
        List<HospSpec> ret = (List<HospSpec>) entityManager.createQuery("SELECT HospSpec from HospSpec a where a.hosp_id= ?1")
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
