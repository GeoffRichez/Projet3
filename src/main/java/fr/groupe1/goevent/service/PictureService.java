package fr.groupe1.goevent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.groupe1.goevent.entities.Picture;
import fr.groupe1.goevent.repository.IPictureRepository;

@Service
public class PictureService implements IPictureService {

	@Autowired
	private IPictureRepository pictureRepository;
	
	@Override
	public List<Picture> getAllPicture() {
		List<Picture> list = new ArrayList<>();
		pictureRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Picture getPictureById(Integer pictureId) {
		Picture adr = pictureRepository.findById(pictureId).get();
		return adr;
	}

	@Override
	public void updatePicture(Picture picture) {
		pictureRepository.save(picture);
		
	}

	@Override
	public void deletePicture(Integer pictureId) {
		pictureRepository.delete(getPictureById(pictureId));
		
	}

	@Override
	public void addPicture(Picture picture) {
		pictureRepository.save(picture);	
	}
}
