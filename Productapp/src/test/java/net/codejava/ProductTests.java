package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductTests {
	@Autowired
    private ProductRepository repo;
	
	@Test
	@Rollback(false)
	public void testCreateProduct() {
	    Product savedProduct = repo.save(new Product("iPhone 11", 799));
	     
	    assertThat(savedProduct.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testFindProductByName() {
	    Product product = repo.findByName("iPhone 11");    
	    assertThat(product.getName()).isEqualTo("iPhone 11");
	}
	
	@Test
	@Rollback(false)
	public void testUpdateProduct() {
	    Product product = repo.findByName("iPhone 11");
	    product.setPrice(1001);
	     
	    repo.save(product);
	     
	    Product updatedProduct = repo.findByName("iPhone 11");
	     
	    assertThat(updatedProduct.getPrice()).isEqualTo(1001);
	}
	@Test
	@Rollback(false)
	public void testDeleteProduct() {
	    Product product = repo.findByName("iPhone 11");
	     
	    repo.deleteById(product.getId());
	     
	    Product deletedProduct = repo.findByName("iPhone 11");
	     
	    assertThat(deletedProduct).isNull();       
	     
	}
	

}


