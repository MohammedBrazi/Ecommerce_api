package sbapi.brazi.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbapi.brazi.models.User;
import sbapi.brazi.models.UserToken;
import sbapi.brazi.repositories.UserRepository;
import sbapi.brazi.repositories.UserTokenRepository;



@Service
public class UserService {
	
	
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private UserTokenRepository utr;
	
	
	
	public User getUser(String username, String password) {
		
			return ur.findByUsernameAndPassword(username, password);
		
		 }
	
	
	public User registerUser(String username,String firstname,String lastname,String email,String phone,
							 String adresse,String type,String password) 
	{

		User user = new User();
		user.setUsername(username);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddresse(adresse);
		user.setType(type);
		user.setPassword(password);
		return ur.save(user);
	}
	
	public User searchPassword(String username,String email) {
		return ur.findByUsernameAndEmail(username, email);
	}
	
	
	public String SaveUserToken(String user_name,String e_mail,String usertok) {
		
		UserToken ut = new UserToken();
		ut.setUsername(user_name);
		ut.setEmail(e_mail);
		ut.setUserToken(usertok);
		utr.save(ut);
		return usertok;
	}

}
