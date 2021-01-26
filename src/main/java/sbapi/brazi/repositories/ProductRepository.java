package sbapi.brazi.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sbapi.brazi.models.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

		
}
