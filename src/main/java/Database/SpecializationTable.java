package Database;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.Specialization;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class SpecializationTable implements SpecializationTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Specialization getSpecializationByName(String name) {
        begin();
        Specialization ret = (Specialization) entityManager.createQuery("Select Specialization from Specialization a where a.name = ?1")
                .setParameter(1, name)
                .getResultList().get(0);
        commit();

        return ret;
    }

    @Override
    public List<Specialization> getSpecializationsByHospitalId(Long id) {
        begin();
        List<Specialization> ret = (List<Specialization>) entityManager
                .createQuery("SELECT new Specialization(a.name, a.description) from Specialization a inner join HospSpec b on a.id = b.spec_id where b.hosp_id = ?1")
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
