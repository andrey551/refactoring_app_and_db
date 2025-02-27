package Database;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.Consultant;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ConsultantTable implements ConsultantTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Consultant> getConsultantsByUserId(Long id) {
        begin();
        List<Consultant> ret = (List<Consultant>) entityManager.createQuery("SELECT Consultant from Consultant a where a.user_id = ?1")
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
