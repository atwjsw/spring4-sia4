package spittr.data.neo4j;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by wenda on 8/21/2017.
 */
@NodeEntity
public class Order {

    @GraphId
    private Long id;

    private String customer;

    private String type;

    @RelatedTo(type="HAS_ITEMS")
    private Set<Item> items = new LinkedHashSet<Item>();

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", type='" + type + '\'' +
                ", items=" + items +
                '}';
    }
}
