package Database;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.Message;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class MessageTable implements MessageTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Message> getMessageByChatId(Long id) {
        begin();
        List<Message> ret = (List<Message>) entityManager.createQuery("SELECT Message from Message a where a.chat_id = ?1")
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
