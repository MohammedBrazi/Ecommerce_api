package sbapi.brazi.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import javax.mail.PasswordAuthentication;
import sbapi.brazi.services.UserService;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import sbapi.brazi.TokenGenerator;
import javax.mail.Authenticator;
import sbapi.brazi.models.User;
import javax.mail.Transport;
import java.util.Properties;
import sbapi.brazi.Response;
import javax.mail.Message;
import javax.mail.Session;
import java.util.Date;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService ;
	
	@RequestMapping(value = "/signin",method = RequestMethod.POST,produces = "application/json")
	public Response login(@RequestParam(value ="username") String user,@RequestParam(value ="password") String pass) throws JsonProcessingException{
		 
		User userData = userService.getUser(user, pass);
		Response response = new Response();
		if (userData == null) {
			    response.setCode("signin");
				response.setStatus(false);
				response.setMessage("Credential Invalid");
				return response;
		}
		else {
				response.setStatus(true);
				response.setMessage(userService.getUser(user, pass));
				return response;
		}
		
	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/signup",method = RequestMethod.POST,produces = "application/json")
	public Response Signup (
			@RequestParam("username") String username,
			@RequestParam("firstname")String firstname,
			@RequestParam("lastname")String lastname,
			@RequestParam("email")String email,
			@RequestParam("phone")String phone,
			@RequestParam("adress")String adresse,
			@RequestParam("type")String type,
			@RequestParam("password")String password){
			
		
				Response response = new Response();
		
	if (username.equals("") || firstname.equals("") || lastname.equals("") || email.equals("") ||
		phone.equals("") || adresse.equals("") || type.equals("") || password.equals(""))
			{
				response.setCode("signup");
				response.setStatus(false);
				response.setMessage("Credential Empty or Invalid");
				return response;
			}
		else 
			{
				User userRegister = userService.registerUser(username, firstname, lastname, email, phone, adresse, type, password);
				response.setCode("User registered Successfully");
				response.setStatus(true);
				response.setMessage(userRegister);
				return response;
			}
	}
	
	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Response resetPassword(@RequestParam("username") String username,
								  @RequestParam("email")String email) throws MessagingException 
		{
		
				Response response = new Response();
			  	Properties mailProperties = new Properties();
			  	mailProperties.put("mail.smtp.host","smtp.gmail.com");
			  	mailProperties.put("mail.smtp.port", 465);
			  	mailProperties.put("spring.mail.username", "brazimohammed49@gmail.com");
			  	mailProperties.put("spring.mail.password", "med99999");
		        mailProperties.put("mail.smtp.auth", true);
		        mailProperties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		        mailProperties.put("mail.smtp.socketFactory.fallback",false);
		        mailProperties.put("mail.debug",true);
		        mailProperties.put("mail.defaultEncoding","UTF-8");

		        Authenticator auth = new Authenticator() {
		            public PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication("brazimohammed49@gmail.com", "med99999");
		            }
		        };

		        Session session =Session.getInstance(mailProperties,auth);
				MimeMessage smm = new MimeMessage(session);
				smm.setFrom("Market_Place_Management");
				smm.setRecipients(Message.RecipientType.TO,email);
				smm.setSubject("Password Reset");
				smm.setSentDate(new Date());
				
			User optional = userService.searchPassword(username, email);
			
			if (optional != null) {
				String usertok = (new TokenGenerator()).generateToken();
				userService.SaveUserToken(username, email, usertok);
				String msg = "<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<meta charset=\"ISO-8859-1\">\r\n" + 
						"<title>Insert title here</title>\r\n" + 
						"</head>\r\n" +
						"<body>\r\n" + 
						"<p>to reset your password please click <a href=\"http://localhost:8080/index.html?token="+usertok+"\">here</a></p>" + 
						"</body>\r\n" + 
						"</html>";
				
				smm.setContent(msg, "text/html");
				Transport.send(smm);	
				response.setCode("restorePassword");
				response.setStatus(true);
				response.setMessage("Message sent Successfully to "+email);
				return response;	
				   
		}
		else {
				response.setCode("restore Password");
				response.setStatus(false);
				response.setMessage("Credential Not Found in Database");
				return response;
		}
		
	}
	
	
	
	
	

}
