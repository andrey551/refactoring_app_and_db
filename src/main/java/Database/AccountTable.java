package Database;

import jakarta.ejb.Singleton;

import Model.Account;
import Raw.RawAccount;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Singleton
public class AccountTable implements AccountTableRemote{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tad");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public Long checkAccount(String username, String password) {
        Account account = entityManager.find(Account.class, username);
        if(account.getPassword().equals(password)) {
            return account.getId();
        }
        return null;
    }

    @Override
    public Long addAccount(RawAccount account) {
        entityManager.persist(account);
        return entityManager.find(Account.class, account.getUsername()).getId();
    }

    @Override
    public Long getIdByUsernameAndPassword(RawAccount account) {

        Account acc = entityManager.find(Account.class, account.getUsername());
        if(acc == null) return -1L;
        return acc.getId();
    }

    @Override
    public long updatePassword(Account account, String newPassword) {
        Account account1 = entityManager.find(account.getClass(), account.getId());
        account1.setPassword(newPassword);
        return entityManager.merge(account1).getId();
    }

    @Override
    public long deleteAccount(Account account) {
        entityManager.remove(account);
        return 0L;
    }

    @Override
    public boolean checkUsername(RawAccount account) {
        return entityManager.find(
                Account.class,
                account.getUsername()) != null;
    }

    @Override
    public EntityManager getManager() {
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
