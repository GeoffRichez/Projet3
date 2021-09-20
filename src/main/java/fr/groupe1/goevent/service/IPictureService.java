package fr.groupe1.goevent.service;

import java.util.List;

import fr.groupe1.goevent.entities.Picture;

public interface IPictureService {

	List<Picture> getAllPicture();
	
	Picture getPictureById(Integer pictureId);
	
	void addPicture(Picture picture);

	void updatePicture(Picture picture);

	void deletePicture(Integer pictureId);
	
}
