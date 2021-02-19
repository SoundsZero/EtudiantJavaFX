package fr.formation.afpa.dao;

import java.util.List;

import fr.formation.afpa.model.Etudiant;

public interface IEtudiantDao {
	
	public List<Etudiant> getAll();
	
	public void add(Etudiant e);
	
	public void update();

}
