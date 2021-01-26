package sbapi.brazi.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sbapi.brazi.models.UserToken;



@Repository
public interface UserTokenRepository extends CrudRepository<UserToken,Integer>{

}
