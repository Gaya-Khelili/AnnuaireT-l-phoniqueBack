package upn_gestionContact;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import upn_gestionContact.entities.Address;
import upn_gestionContact.entities.Contact;
import upn_gestionContact.entities.Phone;
import upn_gestionContact.services.ContactServiceImpl;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class M2ProjectSpringJpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(M2ProjectSpringJpaApplication.class, args);
	}
}
