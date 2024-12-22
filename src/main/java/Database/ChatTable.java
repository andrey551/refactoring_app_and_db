package Database;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.Chat;
import jakarta.persistence.PersistenceContext;

import java.util.Collections;
import java.util.List;

@Stateless
public class ChatTable implements ChatTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Chat getChatFromXAndY(Long from, Long to) {
        List<Chat> chats = Collections.singletonList(entityManager.find(Chat.class, from));

        Chat ret = null;
        
        for(Chat chat : chats) {
            if(chat.getFrom().equals(from) && chat.getTo().equals(to)) {
                ret = chat;
                break;
            }
        }

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
