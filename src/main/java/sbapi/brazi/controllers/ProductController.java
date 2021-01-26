package sbapi.brazi.controllers;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sbapi.brazi.models.Product;
import sbapi.brazi.services.ProductService;


@RestController
public class ProductController {

	@Autowired
	private ProductService productService ;
	
	@RequestMapping(value = "/products",method = RequestMethod.GET,produces = "application/json")
	public Iterable<Product> getProducts() {
		return productService.getProducts();
	}
	
	
	@GetMapping(path = "products/{id}")
	public Optional<Product> getProductById(@PathVariable("id") Long product_id) {
		return productService.getProductById(product_id);
	}
	
	public Product addProduct(@RequestParam("name") String name,
						      @RequestParam("description") String description,
							  @RequestParam("price") Double price,
							  @RequestParam("createdAt") Date created_at,
							  @RequestParam("userid")BigInteger user_id)
       	 {
				return productService.addProduct(name, description, price, created_at,user_id);
	}
	
}
