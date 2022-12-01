package hibernate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class MainCreateOrder{
	public static void main(String[] args) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String type = "hibernate";
		
		if (type.equals("jdbc")) {
		
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				
				conection.setAutoCommit(false);
				
				Order order = new Order(formatter.parse("01/01/2000"), "john");
				
				final PreparedStatement stmt = conection.prepareStatement("INSERT INTO orders(date,"
						+ "customer_name) VALUES(?,?)");		
				
				stmt.setDate(1, new java.sql.Date(order.getDate().getTime()));
				stmt.setString(2, order.getName());
				
				stmt.executeUpdate();
				
				conection.commit();
				//conection.rollback();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		} else if (type.equals("hibernate")) { 
		
			SessionFactory factory = new Configuration().
					                 configure("hibernate.cfg.xml").
					                 buildSessionFactory();
			
			Session session = factory.getCurrentSession();
			
			try {
				
				session.beginTransaction();
				
				Order order = new Order(formatter.parse("01/01/2000"), "karen");
				System.out.println(order);
				session.save(order);
				
				session.getTransaction().commit();
				
				
				/*
				session.beginTransaction();
				// create an order and add products to it
				Order tempOrder = new Order(formatter.parse("11/29/2022"), "bryan");
				Product tempProduct = new Product("mountain dew");
				session.save(tempProduct);
				session.save(tempOrder);
				
				// save the products
				System.out.println("\nSaving Orders & Products ...");
				session.getTransaction().commit();
				*/
				
				/*
				// create an order and add products to it
				Order tempOrder = (Order) session.get(Order.class, 1);
				
				Product tempProduct = (Product) session.get(Product.class, 1);
				
				Order_Detail od = new Order_Detail();
				od.setOrder(tempOrder);
				od.setProduct(tempProduct);
				session.save(od);
				session.getTransaction().commit();
				*/
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
			
  	    }	
		
		System.out.println("Finished!");
	}
	
}