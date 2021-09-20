package fr.groupe1.goevent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.groupe1.goevent.entities.Adress;
import fr.groupe1.goevent.repository.IAdressRepository;

@Service
public class AdressService implements IAdressService{

	@Autowired
	private IAdressRepository adressRepository;
	
	
	@Override
	public List<Adress> getAllAdress() {
		List<Adress> list = new ArrayList<>();
		adressRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Adress getAdressById(Integer adressId) {
		Adress adr = adressRepository.findById(adressId).get();
		return adr;
	}

	@Override
	public void updateAdress(Adress adress) {
		adressRepository.save(adress);
		
	}

	@Override
	public void deleteAdress(int adressId) {
		adressRepository.delete(getAdressById(adressId));
		
	}

	@Override
	public void addAdress(Adress adress) {
		adressRepository.save(adress);	
	}


}
		