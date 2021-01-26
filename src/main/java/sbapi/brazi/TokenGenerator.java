package sbapi.brazi;
import java.util.UUID;

public class TokenGenerator {	
	
	public String generateToken (){
		
		String longToken = UUID.randomUUID().toString();
		String longToken2 = UUID.randomUUID().toString();
		String Token_id = longToken.concat(longToken2);
		String Token_Generator = Token_id.toUpperCase().replace("-", "");
		return Token_Generator;
	}
}
