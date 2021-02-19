package fr.formation.afpa.service;

import java.util.List;

import application.EtudiantDaoFile;
import fr.formation.afpa.model.Etudiant;

public class EtudiantService implements IEtudiantService {
	static EtudiantDaoFile eDao = new EtudiantDaoFile();
	@Override
	public List<Etudiant> listEtudiant() {
		return eDao.getAll();
	}

	@Override
	public void ajouterEtudiant(Etudiant e) {
		eDao.add(e);
	}

	@Override
	public Etudiant modifierEtudiant(Etudiant e) {
		 
		return null;
	}

	

}
