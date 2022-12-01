package hibernate;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
 
@Embeddable
public class Order_Detail_ID implements Serializable { 
    private Order orders;
    private Product products;
 
    @ManyToOne(cascade = CascadeType.ALL)
    public Order getOrder() {
        return orders;
    }
 
    public void setOrder(Order order) {
        this.orders = order;
    }
 
    @ManyToOne(cascade = CascadeType.ALL)
    public Product getProduct() {
        return products;
    }
 
    public void setProduct(Product product) {
        this.products = product;
    }
}