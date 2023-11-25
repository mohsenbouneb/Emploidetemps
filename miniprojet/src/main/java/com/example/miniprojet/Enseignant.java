package com.example.miniprojet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Enseignant {
    private final StringProperty Matricule;
    private final StringProperty Nom;
    private final StringProperty Contact;
    private final StringProperty Classe;
    private final StringProperty Matiere;
    private final StringProperty Heure;
    private final StringProperty Date;
    private final StringProperty Enseignantmat;
    private final StringProperty cont;
    private final StringProperty id;
    public Enseignant()
    {
        Matricule = new SimpleStringProperty(this, "Matricule");
        Nom = new SimpleStringProperty(this, "Nom");
        Contact = new SimpleStringProperty(this, "Contact");
        Classe = new SimpleStringProperty(this, "Classe");
        Matiere = new SimpleStringProperty(this, "Matiere");
        Heure = new SimpleStringProperty(this, "Heure");
        Date = new SimpleStringProperty(this, "Date");
        Enseignantmat = new SimpleStringProperty(this, "Enseignantmat");
        id = new SimpleStringProperty(this, "ID");
        cont = new SimpleStringProperty(this, "Matricule");

    }

    public StringProperty idProperty() { return id; }
    public String getid() { return id.get(); }
    public void setid(String newId) { id.set(newId); }
    public StringProperty matriculeProperty() { return Matricule; }
    public String getMatricule() { return Matricule.get(); }
    public void setMatricule(String newId) { Matricule.set(newId); }
    public StringProperty nomProperty() { return Nom; }
    public String getNom() { return Nom.get(); }
    public void setNom(String newName) { Nom.set(newName); }
    public StringProperty contactProperty() { return Contact; }
    public String getContact() { return Contact.get(); }
    public void setContact(String newMobile) { Contact.set(newMobile); }
    public StringProperty classeProperty() {return Classe;}
    public String getClasse() {return Classe.get();}
    public void setClasse(String newClasse) {Classe.set(newClasse);}
    public StringProperty matiereProperty() {return Matiere;}
    public String getMatiere() {return Matiere.get();}
    public void setMatiere(String newMatiere) {Matiere.set(newMatiere);}
    public StringProperty heureProperty() {return Heure;}
    public String getHeure() {return Heure.get();}
    public void setHeure(String newHeure) {Heure.set(newHeure);}
    public StringProperty dateProperty() {return Date;}
    public String getDate() {return Date.get();}
    public void setDate(String newDate) {Date.set(newDate);}
    public StringProperty enseignantProperty() {return Enseignantmat;}
    public String getEnseignant() {return Enseignantmat.get();}
    public void setEnseignant(String newEnseignant) {Enseignantmat.set(newEnseignant);}
    public StringProperty contProperty() {return cont;}
    public String getcont() {return cont.get();}
    public void setcont(String newEnseignant) {cont.set(newEnseignant);}
}
