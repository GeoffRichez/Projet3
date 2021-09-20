package fr.groupe1.goevent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.groupe1.goevent.entities.Adress;
import fr.groupe1.goevent.service.AdressService;
import fr.groupe1.goevent.service.IAdressService;

@SpringBootTest
public class TestAdress {

	@Autowired
	IAdressService adressService;
		
	@Test
	public void addTestAdress() {
		Adress adress = new Adress();
		adress.setCity("Lyon");
		adress.setRoad("rue du Jambon");
		adress.setZipCode(34200);
		
		adressService.addAdress(adress);
	}
	
	@Test
	public void deletetTestAdress( ) {
		adressService.deleteAdress(1);
	}
}
