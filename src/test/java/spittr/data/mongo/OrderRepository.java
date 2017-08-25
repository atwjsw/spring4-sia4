package spittr.data.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by wenda on 8/21/2017.
 */
public interface OrderRepository extends MongoRepository<Order, String>, OrderOperations {
    List<Order> findByCustomer(String c);
    List<Order> findByCustomerLike(String c);
    List<Order> findByCustomerAndType(String c, String t);
    List<Order> findByCustomerLikeAndType(String c, String t);
    @Query("{'customer': 'Chuck Wagon', 'type' : ?0}")
    List<Order> findChucksOrders(String t);
}
