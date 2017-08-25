package spittr.data.mongo;

import java.util.List;

/**
 * Created by wenda on 8/21/2017.
 */
public interface OrderOperations {
    List<Order> findOrdersByType(String t);
}
