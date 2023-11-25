package com.example.miniprojet;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Requetes implements Initializable {
    @FXML
    private ChoiceBox<String> cbclasse2;
    @FXML
    private ChoiceBox<String> cbclasse3;
    @FXML
    private TableView<Enseignant> tabv;
    @FXML
    private TableColumn<Enseignant, String> colid;
    @FXML
    private TableColumn<Enseignant, String> colclasse;
    @FXML
    private TableColumn<Enseignant, String> colmatiere;
    @FXML
    private TableColumn<Enseignant, String> coljour;
    @FXML
    private TableColumn<Enseignant, String> colheure;
    @FXML
    private TableColumn<Enseignant, String> colenseignant;
    @FXML
    private TableColumn<Enseignant, String> colcontact;
    @FXML
    private Button btnchercher3;
    @FXML
    private Button btnchercher2;
    @FXML
    private TextField txtmatiere2;
    @FXML
    private TextField txtid2;
    @FXML
    private Button btnsupp2;
    @FXML
    public void tabv() {
        ObservableList<Enseignant> cour = FXCollections.observableArrayList();
        try {
            pst = this.con.prepareStatement("SELECT c.ID, c.Classe, c.Matiere, c.Heure, c.Date, e.nom, e.Contact FROM cours c JOIN enseignant e ON c.Enseignantmat = e.Matricule;");            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Enseignant crs = new Enseignant();
                crs.setid(rs.getString("c.ID"));
                crs.setClasse(rs.getString("c.Classe"));
                crs.setMatiere(rs.getString("c.Matiere"));
                crs.setHeure(rs.getString("c.Heure"));
                crs.setDate(rs.getString("c.Date"));
                crs.setEnseignant(rs.getString("e.nom"));
                crs.setcont(rs.getString("e.Contact"));
                cour.add(crs);
            }
            tabv.setItems(cour);
            colid.setCellValueFactory(f -> f.getValue().idProperty());

            colclasse.setCellValueFactory(f -> f.getValue().classeProperty());
            colmatiere.setCellValueFactory(f -> f.getValue().matiereProperty());
            colheure.setCellValueFactory(f -> f.getValue().heureProperty());
            coljour.setCellValueFactory(f -> f.getValue().dateProperty());
            colenseignant.setCellValueFactory(f -> f.getValue().enseignantProperty());
            colcontact.setCellValueFactory(f -> f.getValue().contProperty());

        } catch (SQLException ex) {
            Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    void chercher3(ActionEvent event) {
        String searchTerm = cbclasse3.getValue().toString();;
        search(searchTerm);
    }

    @FXML
    private void search(String term) {
        ObservableList<Enseignant> searchResults = FXCollections.observableArrayList();

        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT c.ID, c.Classe, c.Matiere, c.Heure, c.Date, e.nom, e.Contact FROM cours c JOIN enseignant e ON c.Enseignantmat = e.Matricule WHERE c.Classe LIKE ?");
            pst.setString(1, "%" + term + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Enseignant crs = new Enseignant();
                crs.setid(rs.getString("c.ID"));
                crs.setClasse(rs.getString("c.Classe"));
                crs.setMatiere(rs.getString("c.Matiere"));
                crs.setHeure(rs.getString("c.Heure"));
                crs.setDate(rs.getString("c.Date"));
                crs.setEnseignant(rs.getString("e.nom"));
                crs.setcont(rs.getString("e.Contact"));
                searchResults.add(crs);
            }

            tabv.setItems(searchResults);
        } catch (SQLException ex) {
            Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void chercher2(ActionEvent event) {
        String searchTerm = cbclasse2.getValue().toString();
        String searchTerm2 = txtmatiere2.getText();
        search2(searchTerm,searchTerm2);
    }

    @FXML
    private void search2(String term,String term2) {
        ObservableList<Enseignant> searchResults = FXCollections.observableArrayList();

        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT c.ID, c.Classe, c.Matiere, c.Heure, c.Date, e.nom, e.Contact FROM cours c JOIN enseignant e ON c.Enseignantmat = e.Matricule WHERE c.Classe LIKE ? AND c.Matiere  LIKE ?");
            pst.setString(1, "%" + term + "%" );
            pst.setString(2, "%" + term2 + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Enseignant crs = new Enseignant();
                crs.setid(rs.getString("c.ID"));
                crs.setClasse(rs.getString("c.Classe"));
                crs.setMatiere(rs.getString("c.Matiere"));
                crs.setHeure(rs.getString("c.Heure"));
                crs.setDate(rs.getString("c.Date"));
                crs.setEnseignant(rs.getString("e.nom"));
                crs.setcont(rs.getString("e.Contact"));
                searchResults.add(crs);
            }

            tabv.setItems(searchResults);
        } catch (SQLException ex) {
            Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void supprimer(ActionEvent event) {
        if (myIndex >= 0) {
            try {
                PreparedStatement pst = con.prepareStatement("DELETE FROM emploidutemps_db.cours WHERE ID = ?");
                pst.setString(1, txtid2.getText());
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppression des seances");
                alert.setHeaderText("Suppression des seances");
                alert.setContentText("La suppression est terminée avec succès");
                alert.showAndWait();

                tabv();

                txtid2.setText("");

                txtid2.requestFocus();
            } catch (SQLException ex) {
                Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else

        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune ligne sélectionnée");
            alert.setContentText("Veuillez sélectionner une ligne dans le tableau.");
            alert.showAndWait();
        }
    }
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    public void Connect() {



        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/emploiDuTemps_db","java","");
            System.out.println("Connection établie!");
        }
        catch(Exception e){
            System.out.println("non connectée!");
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connect();
        tabv();

        ObservableList<String> classes = FXCollections.observableArrayList("1er", "2eme", "3eme", "4eme", "5eme", "6eme");

        cbclasse2.setItems(classes);
        cbclasse3.setItems(classes);
    }
}