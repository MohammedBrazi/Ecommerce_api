package sbapi.brazi.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sbapi.brazi.models.User;


@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
	
	public User findByUsernameAndPassword(String username, String password);
	public User findByUsernameAndEmail(String username, String email);
	

}
