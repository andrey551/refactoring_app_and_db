package Database;

import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.Wallet;

@Singleton
public class WalletTable implements WalletTableRemote{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tad");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public Wallet getWalletByUserId(Long id) {
        begin();
        Wallet ret = (Wallet) entityManager.createQuery("SELECT Wallet from Wallet a where a.id = ?1")
                .setParameter(1, id)
                .getResultList()
                .get(0);
        commit();

        return ret;
    }

    @Override
    public Long addMoney(Long amount, Long id) {
        Wallet wallet = getWalletByUserId(id);
        wallet.setBalance(wallet.getBalance() + amount);
        entityManager.merge(wallet);
        return wallet.getBalance();
    }

    @Override
    public Long payMoney(long amount, Long id) {
        Wallet wallet = getWalletByUserId(id);
        wallet.setBalance(wallet.getBalance() - amount);
        entityManager.merge(wallet);
        return wallet.getBalance();
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
