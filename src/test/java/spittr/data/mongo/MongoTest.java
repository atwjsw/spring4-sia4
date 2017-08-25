package spittr.data.mongo;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wenda on 8/20/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MongoConfig.class)
public class MongoTest {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    OrderRepository orderRepository;

    @Before
    public void cleanup() {
        // Deleting all orders (just in case something is left over from a previous failed run)
        orderRepository.deleteAll();
    }

    @Test
    public void testMongoRepository() {
        assertEquals(0, orderRepository.count());
        Order order = createAnOrder();
        System.out.println(order);

        // Saving an order
        Order savedOrder = orderRepository.save(order);
        System.out.println(savedOrder);
        assertEquals(1, orderRepository.count());

        // Finding an order by ID
        Order foundOrder = orderRepository.findOne(savedOrder.getId());
        System.out.println(foundOrder);
        assertEquals("Chuck Wagon", foundOrder.getCustomer());
        assertEquals(2, foundOrder.getItems().size());

        // Finding an order by a single field value
        List<Order> chucksOrders = orderRepository.findByCustomer("Chuck Wagon");
        System.out.println(chucksOrders);
        assertEquals(1, chucksOrders.size());
        assertEquals("Chuck Wagon", chucksOrders.get(0).getCustomer());
        assertEquals(2, chucksOrders.get(0).getItems().size());

        // Finding an order by a single field value like
        List<Order> chuckLikeOrders = orderRepository.findByCustomerLike("Chuck");
        System.out.println(chuckLikeOrders);
        assertEquals(1, chuckLikeOrders.size());
        assertEquals("Chuck Wagon", chuckLikeOrders.get(0).getCustomer());
        assertEquals(2, chuckLikeOrders.get(0).getItems().size());

        // Finding an order by multiple field values
        List<Order> chucksWebOrders = orderRepository.findByCustomerAndType("Chuck Wagon", "WEB");
        System.out.println(chucksWebOrders);
        assertEquals(1, chucksWebOrders.size());
        assertEquals("Chuck Wagon", chucksWebOrders.get(0).getCustomer());
        assertEquals(2, chucksWebOrders.get(0).getItems().size());

        List<Order> chucksPhoneOrders = orderRepository.findByCustomerAndType("Chuck Wagon", "PHONE");
        System.out.println(chucksPhoneOrders);
        assertEquals(0, chucksPhoneOrders.size());

        // Finding an order by a custom query method
        List<Order> chucksOrders2 = orderRepository.findChucksOrders("WEB");
        System.out.println(chucksOrders2);
        assertEquals(1, chucksOrders2.size());
        assertEquals("Chuck Wagon", chucksOrders2.get(0).getCustomer());
        assertEquals(2, chucksOrders2.get(0).getItems().size());

        //Finding an order by mixing manual and auto generated implementation
        List<Order> chucksOrders3 = orderRepository.findOrdersByType("NET");
        System.out.println(chucksOrders3);
        assertEquals(1, chucksOrders2.size());
        assertEquals("Chuck Wagon", chucksOrders2.get(0).getCustomer());
        assertEquals(2, chucksOrders2.get(0).getItems().size());

        // Deleting an order
        orderRepository.delete(savedOrder.getId());
        assertEquals(0, orderRepository.count());

    }

    @Test
    public void shouldNotbeNull() {
        assertNotNull(mongoOperations);
        System.out.println(mongoOperations);
    }

    @Test
    public void testMongoOperationsCount() {
        long orderCount = mongoOperations.getCollection("order").count();
        System.out.println(orderCount);
    }

    @Test
    public void testMongoOperationsSave() {
        Order order = createAnOrder();
       mongoOperations.save(order);
    }

    @Test
    public void testMongoOperationsFindById() {
        String id = "599a6170b4d673ae450389d3";
        Order order = mongoOperations.findById(id, Order.class);
        System.out.println(order);
    }

    @Test
    public void testMongoOperationsFindByQuery() {
        List<Order> chucksOrders = mongoOperations.find(Query.query(Criteria.where("client").is("Chuck Wagon")), Order.class);
        System.out.println(chucksOrders);
        List<Order> chucksWebOrders = mongoOperations.find(Query.query(Criteria.where("customer").is("Chuck Wagon").and("type").is("WEB")), Order.class);
        System.out.println(chucksWebOrders);
    }

    @Test
    public void testMongoOperationsRemove() {
        String id = "599a6170b4d673ae450389d3";
        Order order = mongoOperations.findById(id, Order.class);
        System.out.println(order);
        mongoOperations.remove(order);
    }





    private Order createAnOrder() {
        Order order = new Order();
        order.setCustomer("Chuck Wagon");
        order.setType("WEB");
        Item item1 = new Item();
        item1.setProduct("Spring in Action");
        item1.setQuantity(2);
        item1.setPrice(29.99);
        Item item2 = new Item();
        item2.setProduct("Module Java");
        item2.setQuantity(31);
        item2.setPrice(29.95);
        order.setItems(Arrays.asList(item1, item2));
        return order;
    }
}
