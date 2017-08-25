package spittr.data.mongo;

import com.mongodb.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by wenda on 8/21/2017.
 */
public class OrderRepositoryImpl implements OrderOperations {
    //Inject MongoOperations
    @Autowired
    private MongoOperations mongo;

    @Override
    public List<Order> findOrdersByType(String t) {
        String type = t.equals("NET") ? "WEB" : t;
        System.out.println(type);
        Criteria where = Criteria.where("type").is(type);       //create query
        Query query = new Query(where);
        return mongo.find(query, Order.class);                //perform query
    }
}
