package hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import javax.persistence.OneToMany;

@Entity
@Table(name="orders")
public class Order{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="customer_name")
	private String customer_name;
	
	public Order() {}
	
	public Order(Date date, String customer_name) {
		this.date = date;
		this.customer_name = customer_name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getName() {
		return this.customer_name;
	}
	
	public void setName(String customer_name) {
		this.customer_name = customer_name;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return "Users [id=" + id + ", date=" + formatter.format(date) + ", customer_name= " + customer_name + "]";
	}
	
	@OneToMany(mappedBy = "primaryKey.order", cascade = CascadeType.ALL)
	private Set<Order_Detail> order_detail = new HashSet<Order_Detail>();
	public Set<Order_Detail> getOrderDetail(){
		return this.order_detail;
	}
	
	public void setOrderDetail(Set<Order_Detail> order_detail) {
		this.order_detail = order_detail;
	}
	
	public void addOrderDetail(Order_Detail order_detail) {
		this.order_detail.add(order_detail);
	}
	
}