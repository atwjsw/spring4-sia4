package spittr.data.springdata;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by wenda on 8/20/2017.
 */
public class SpitterRepositoryImpl implements SpitterSweeper{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public int eliteSweep() {
//        String update = "UPDATE Spitter spitter " +
//                "SET spitter.status = 'Elite' " +
//                "WHERE spitter.status = 'Newbie' " +
//                "AND spitter.id IN (" +
//                "SELECT s FROM Spitter s WHERE (" +
//                " SELECT COUNT(spittles) FROM s.spittles spittles) > 10000" +
//                ")";

        String update = "UPDATE Spitter spitter " +
                "SET spitter.password = '123456' " +
                "WHERE spitter.username = 'habuma' ";
        return entityManager.createQuery(update).executeUpdate();

    }
}
