package fr.formation.afpa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Etudiant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private String dateDeN;
	private String pathPhoto;
	public static final AtomicLong NEXT_ID = new AtomicLong(0);
	private long id = 0;
	private int idee;
	//private ImageView imgview = new ImageView(new Image("C:\\Users\\afpa\\Downloads\\plus.jpg"));
	
	
	ArrayList<Double> notes = new ArrayList<Double>();

	public Etudiant() {

	}

	public Etudiant(String prenom, String nom, String dateDeN , String pathPhoto) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeN = dateDeN;
		this.pathPhoto = pathPhoto;
		this.id = NEXT_ID.getAndIncrement();
	}

	public Etudiant creationEtudiant(Etudiant e) {
		return e;

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	

	

	public int getIdee() {
		return idee;
	}

	public void setIdee(int idee) {
		this.idee = idee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	public static AtomicLong getNextId() {
		return NEXT_ID;
	}

	public ArrayList<Double> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<Double> notes) {
		this.notes = notes;
	}
	

	

	

	
	public String getDateDeN() {
		return dateDeN;
	}

	public void setDateDeN(String dateDeN) {
		this.dateDeN = dateDeN;
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", dateDeN=" + dateDeN + ", pathPhoto=" + pathPhoto
				+ ", notes=" + notes + "]";
	}

	

}
