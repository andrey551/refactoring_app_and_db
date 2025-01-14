package Database;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.User;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserTable implements UserTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public User getUserByAccountId(Long id) {
        begin();
        User ret = (User) entityManager.createQuery("SELECT a from User a where a.id = ?1")
                .setParameter(1, id)
                .getResultList()
                .get(0);
        commit();

        return ret;
    }

    @Override
    public void deleteUserByAccountId(Long id) {
        entityManager.remove(getUserByAccountId(id));
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
