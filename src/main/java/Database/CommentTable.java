package Database;

import Raw.RawComment;
import Raw.coordinate;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Model.Comment;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Stateless
public class CommentTable implements CommentTableRemote{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<RawComment> getCommentsByHospitalId(Long id) {
        begin();
        List<RawComment> ret = (List<RawComment>) entityManager.createQuery("SELECT new RawComment(b.name, b.avatar, a.content,a.time, a.rating) from Comment a inner join User b on a.user_id = b.id where a.location_id = ?1 order by a.time desc")
                .setParameter(1, id)
                .getResultList();
        commit();

        return ret;
    }

    @Override
    public boolean addComment(Comment comment) {



        // System.out.println(comment.toString());
        log.info("Comment received: " + comment.toString());

        entityManager.persist(comment);
        return true;
    }

    @Override
    public void deleteComment(Long id) {
        entityManager.remove(entityManager.find(RawComment.class, id));
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
