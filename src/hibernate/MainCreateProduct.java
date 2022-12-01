package hibernate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class MainCreateProduct {

	public static void main(String[] args) {
		
		String type = "hibernate";
		
		if (type.equals("jdbc")) {
		
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				
				conection.setAutoCommit(false);
				
				Product product = new Product("coke");
				
				final PreparedStatement stmt = conection.prepareStatement("INSERT INTO products(name"
						+ ") VALUES(?)");		
				
				stmt.setString(1, product.getName());
				
				
				stmt.executeUpdate();
				
				conection.commit();
				//conection.rollback();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		} else if (type.equals("hibernate")) { 
		
			SessionFactory factory = new Configuration().
					                 configure("hibernate.cfg.xml").
					                 addAnnotatedClass(Product.class).
					                 buildSessionFactory();
			
			Session session = factory.getCurrentSession();
			
			try {
				
				Product product = new Product("sprite");
				
				session.beginTransaction();
				
				System.out.println(product);
				session.save(product);
				
				session.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
			
  	    }	
		
		System.out.println("Finished!");
		
	}
	
}
