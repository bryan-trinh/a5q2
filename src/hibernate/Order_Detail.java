package hibernate;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="order_detail")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.products",
        joinColumns = @JoinColumn(name = "product_id")),
    @AssociationOverride(name = "primaryKey.orders",
        joinColumns = @JoinColumn(name = "order_id")) })
public class Order_Detail {
	private Order_Detail_ID primaryKey = new Order_Detail_ID();
	
	@EmbeddedId
	public Order_Detail_ID getPrimaryKey() {
		return primaryKey;
	}
	
	public void setPrimaryKey(Order_Detail_ID primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	@Transient
	public Order getOrder() {
		return getPrimaryKey().getOrder();
	}
	
	public void setOrder(Order order) {
		getPrimaryKey().setOrder(order);
	}
	
	@Transient
	public Product getProduct() {
		return getPrimaryKey().getProduct();
	}
	
	public void setProduct(Product product) {
		getPrimaryKey().setProduct(product);
	}
}