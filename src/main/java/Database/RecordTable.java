package Database;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.Record;
import jakarta.persistence.PersistenceContext;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Stateless
public class RecordTable implements RecordTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Record> getRecordByUserId(Long id) {

        begin();

        List<Record> ret = (List<Record>) entityManager.createQuery("select a from Record a where a.user_id = ?1 order by a.time desc")
                .setParameter(1, id)
                .getResultList();

        commit();

        return ret;
    }

    @Override
    public Long deleteRecordByTime(Long id, Timestamp time) {
        entityManager.remove(entityManager.find(Record.class, id));
        return id;
    }

    @Override
    public Long addRecord(Record record) {
        entityManager.persist(record);
        return 1L;
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
