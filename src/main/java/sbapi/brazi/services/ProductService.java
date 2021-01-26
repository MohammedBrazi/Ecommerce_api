package sbapi.brazi.services;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbapi.brazi.models.Product;
import sbapi.brazi.repositories.ProductRepository;





@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public Iterable<Product> getProducts() {
		
		return productRepository.findAll();
	
	}
	
	public Optional<Product> getProductById(Long id) {
		
		return productRepository.findById(id);
	}
	
	public Product addProduct(String name,String description,double price,Date created_at,BigInteger user_id) {
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setCreatedAt(created_at);
		product.setUserId(user_id);
		return productRepository.save(product);
	}

}
