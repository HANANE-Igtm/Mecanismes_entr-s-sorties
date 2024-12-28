package Model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import DAO.EmployeDAOImp;

public class EmployeModel {
    private EmployeDAOImp dao;

    public EmployeModel(EmployeDAOImp dao) {
        this.dao = dao;
    }

    // Méthode pour ajouter un employé
    public boolean add(String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste, int balance) {
        // Validation de l'email
        if (!email.contains("@")) {
            System.err.println("L'email que vous avez entré est invalide.");
            return false;
        }

        // Validation du numéro de téléphone
        if (telephone.length() != 10) {
            System.err.println("Le numéro de téléphone doit contenir exactement 10 chiffres.");
            return false;
        }

        // Création de l'objet Employe
        Employe empl = new Employe(nom, prenom, email, telephone, salaire, role, poste, 25);

        // Ajout dans la base de données
        dao.add(empl);
        return true;
    }

    // Méthode pour mettre à jour un employé
    public boolean update(int id, String nom, String prenom, String email, String telephone, double salaire, Role role, Poste poste, int balance) {
        // Validation de l'email
        if (!email.contains("@")) {
            System.err.println("L'email que vous avez entré est invalide.");
            return false;
        }

        // Validation du numéro de téléphone
        if (telephone.length() != 10) {
            System.err.println("Le numéro de téléphone doit contenir exactement 10 chiffres.");
            return false;
        }

        // Création de l'objet Employe
        Employe emp = new Employe(id, nom, prenom, email, telephone, salaire, role, poste, balance);

        // Mise à jour dans la base de données
        dao.update(emp);
        return true;
    }

    // Méthode pour supprimer un employé
    public boolean delete(int id) {
        dao.delete(id);
        return true;
    }

    // Méthode pour récupérer tous les employés
    public Object[][] employes() {
        List<Employe> emp = dao.getAll();
        Object[][] tab = new Object[emp.size()][9]; // Ajout de la colonne balance

        for (int i = 0; i < emp.size(); i++) {
            Employe empl = emp.get(i);
            tab[i][0] = empl.getId();
            tab[i][1] = empl.getNom();
            tab[i][2] = empl.getPrenom();
            tab[i][3] = empl.getEmail();
            tab[i][4] = empl.getTelephone();
            tab[i][5] = empl.getSalaire();
            tab[i][6] = empl.getRole();
            tab[i][7] = empl.getPoste();
            tab[i][8] = empl.getBalance(); // Ajout de la balance
        }

        return tab;
    }
    private boolean checkFileExits(File file) {

        if(!file.exists()) {
            throw new IllegalArgumentException ("le fichier n'existe pas "+file.getPath());

        }
        return true;

    }
    private boolean checkIsFile(File file) {

        if(!file.isFile()) {
            throw new IllegalArgumentException ("le chemin specifie nest pas un fichier "+file.getPath());

        }
        return true;

    }
    private boolean checkIsReadebal(File file) {

        if(!file.canRead()) {
            throw new IllegalArgumentException ("le chemin specifie nest pas lisibles "+file.getPath());

        }
        return true;

    }
    public void importData(String FileName)throws IOException  {
        File file = new File(FileName);
        checkFileExits(file);
        checkIsFile(file);
        checkIsReadebal(file);
        dao.importData(FileName);
    }

    public void exportData(String FileName , List<Employe> data) throws IOException {
        File file = new File(FileName);
        dao.exportData(FileName, data);
    }
}
