package fr.groupe1.goevent.service;

import java.util.List;

import fr.groupe1.goevent.entities.Adress;

public interface IAdressService {
	
	List<Adress> getAllAdress();

	Adress getAdressById(Integer adressId);

	void addAdress(Adress adress);

	void updateAdress(Adress adress);

	void deleteAdress(int adressId);

}
