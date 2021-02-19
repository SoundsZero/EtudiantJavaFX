package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import fr.formation.afpa.dao.IEtudiantDao;
import fr.formation.afpa.model.Etudiant;

public class EtudiantDaoFile implements IEtudiantDao {
	static List <Etudiant> lEtudiant = new ArrayList<Etudiant>();
	
    File file = new File("etudiantjavaFX.txt");
    
    Etudiant etu = new Etudiant ();
    
	public List<Etudiant> getAll() {

		return lEtudiant;

	}

	
	public void add(Etudiant e) {
	
			lEtudiant.add(e);
		
		
		try {
			OutputStream os = new FileOutputStream("etudiantjavaFX.txt");
			ObjectOutputStream oos = new ObjectOutputStream(os);			
			oos.writeObject(getAll());
			oos.close();
			System.out.println("Nouvelle entrée : " + e.getPrenom());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace(); 
		} catch (IOException e1) {
			e1.printStackTrace();

		}
		
	}

	

	public void update() {

		try {
			OutputStream os = new FileOutputStream("etudiantjavaFX.txt");
			ObjectOutputStream oos = new ObjectOutputStream(os);			
			oos.writeObject(getAll());
			oos.close();
			System.out.println("Réécriture  de la liste");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();

		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Etudiant> recuperationListEtudiant() {
		
		
        List<Etudiant> listeEtudiants = new ArrayList<Etudiant>();
        try {
        	
            if (file.length() != 0) {
                FileInputStream os = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(os);
                lEtudiant =  (List<Etudiant>) ois.readObject();
         
                ois.close();
            } else {
                return listeEtudiants;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return listeEtudiants;
        } catch (IOException e) {
            e.printStackTrace();
            return listeEtudiants;
        }
      
        for (Etudiant stud : lEtudiant) {
			System.out.println("Onglet principale");
        	Etudiant.NEXT_ID.getAndIncrement();
        	}  
        return lEtudiant;
    }
	
}
