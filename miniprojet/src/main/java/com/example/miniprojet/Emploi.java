package com.example.miniprojet;

import java.io.IOException;
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

public class Emploi implements Initializable {

    @FXML
    private TextField txtmat1;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtcontact;
    @FXML
    private TableView<Enseignant> table;
    @FXML
    private TableColumn<Enseignant, String> matcol;
    @FXML
    private TableColumn<Enseignant, String> nomcol;
    @FXML
    private TableColumn<Enseignant, String> contcol;
    @FXML
    private ChoiceBox<String> cbclasse;
    @FXML
    private TextField txtmatiere;

    @FXML
    private DatePicker cbjour;
    @FXML
    private ChoiceBox<String> cbheure;
    @FXML
    private TextField txtmat2;

    @FXML
    private TableView<Enseignant> table2;
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
    private Button chercher;
    @FXML
    private Button btnadd;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button btnadd2;







    @FXML
    void Add(ActionEvent event) {
        String matricule,name,cont;
        matricule = txtmat1.getText();
        name = txtnom.getText();
        cont = txtcontact.getText();
        if (this.con == null) {
            System.err.println("Error: Connection is null");
            return; // or throw an exception
        }
        try
        {
            PreparedStatement pst = con.prepareStatement("INSERT INTO `emploidutemps_db`.`enseignant` (`Matricule`, `Nom`, `Contact`) VALUES (?,?,?)");
            pst.setString(1, matricule);
            pst.setString(2, name);
            pst.setString(3, cont);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement d'enseignant");

            alert.setHeaderText("Enregistrement d'enseignant");
            alert.setContentText("l'enregistrement terminé avec succès");
            alert.showAndWait();
            table();

            txtmat1.setText("");
            txtnom.setText("");
            txtcontact.setText("");
            txtmat1.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
    } }
    public void table()
    {

        ObservableList<Enseignant> enseignants = FXCollections.observableArrayList();
        try
        {
            pst = this.con.prepareStatement("SELECT Matricule,Nom,Contact FROM emploidutemps_db.enseignant;");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Enseignant ens = new Enseignant();
                    ens.setMatricule(rs.getString("Matricule"));
                    ens.setNom(rs.getString("Nom"));
                    ens.setContact(rs.getString("Contact"));
                    enseignants.add(ens);
                }
            }
            table.setItems(enseignants);
            matcol.setCellValueFactory(f -> f.getValue().matriculeProperty());
            nomcol.setCellValueFactory(f -> f.getValue().nomProperty());
            contcol.setCellValueFactory(f -> f.getValue().contactProperty());



        }

        catch (SQLException ex)
        {
            Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setRowFactory( tv -> {
            TableRow<Enseignant> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();


                    txtmat1.setText(table.getItems().get(myIndex).getMatricule());
                    txtnom.setText(table.getItems().get(myIndex).getNom());
                    txtcontact.setText(table.getItems().get(myIndex).getContact());



                }
            });
            return myRow;
        });


    }


    @FXML
    void delete(ActionEvent event) {
        if (myIndex >= 0) {
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM emploidutemps_db.enseignant WHERE Matricule = ?");
            pst.setString(1, txtmat1.getText());
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression d'enseignant");
            alert.setHeaderText("Suppression d'enseignant");
            alert.setContentText("La suppression est terminée avec succès");
            alert.showAndWait();

            table();

            txtmat1.setText("");
            txtnom.setText("");
            txtcontact.setText("");
            txtmat1.requestFocus();
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

    @FXML
    void update(ActionEvent event) {
        String matricule = txtmat1.getText();
        String name = txtnom.getText();
        String cont = txtcontact.getText();

        if (myIndex >= 0) {
            try {
                PreparedStatement pst = con.prepareStatement("UPDATE `emploidutemps_db`.`enseignant` SET Matricule=?, Nom=?, Contact=? WHERE Matricule=?");
                pst.setString(1, matricule);
                pst.setString(2, name);
                pst.setString(3, cont);
                pst.setString(4, table.getItems().get(myIndex).getMatricule());

                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mise à jour d'enseignant");
                alert.setHeaderText("Mise à jour d'enseignant");
                alert.setContentText("La mise à jour est terminée avec succès");
                alert.showAndWait();

                table();

                txtmat1.setText("");
                txtnom.setText("");
                txtcontact.setText("");
                txtmat1.requestFocus();
            } catch (SQLException ex) {
                Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucune ligne sélectionnée");
            alert.setContentText("Veuillez sélectionner une ligne dans le tableau.");
            alert.showAndWait();
        }
    }
    @FXML
    void cherch(ActionEvent event) {

        String searchTerm = txtmat1.getText();
        search(searchTerm);
    }

    @FXML
    private void search(String term) {
        ObservableList<Enseignant> searchResults = FXCollections.observableArrayList();

        try {
            PreparedStatement pst = con.prepareStatement("SELECT Matricule, Nom, Contact FROM emploidutemps_db.enseignant WHERE Matricule LIKE ?");
            pst.setString(1, "%" + term + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Enseignant ens = new Enseignant();
                ens.setMatricule(rs.getString("Matricule"));
                ens.setNom(rs.getString("Nom"));
                ens.setContact(rs.getString("Contact"));
                searchResults.add(ens);
            }

            table.setItems(searchResults);
        } catch (SQLException ex) {
            Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void table2() {
        ObservableList<Enseignant> cour = FXCollections.observableArrayList();
        try {
            pst = this.con.prepareStatement("SELECT Classe, Matiere, Heure, Date, Enseignantmat FROM emploidutemps_db.cours;");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Enseignant crs = new Enseignant();
                crs.setClasse(rs.getString("Classe"));
                crs.setMatiere(rs.getString("Matiere"));
                crs.setHeure(rs.getString("Heure"));
                crs.setDate(rs.getString("Date"));
                crs.setEnseignant(rs.getString("Enseignantmat"));
                cour.add(crs);
            }
            table2.setItems(cour);
            colclasse.setCellValueFactory(f -> f.getValue().classeProperty());
            colmatiere.setCellValueFactory(f -> f.getValue().matiereProperty());
            colheure.setCellValueFactory(f -> f.getValue().heureProperty());
            coljour.setCellValueFactory(f -> f.getValue().dateProperty());
            colenseignant.setCellValueFactory(f -> f.getValue().enseignantProperty());

        } catch (SQLException ex) {
            Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void add2(ActionEvent event) {
        String classe, matiere, heure, date, enseignant;
        classe = cbclasse.getValue().toString();
        matiere = txtmatiere.getText();
        heure = cbheure.getValue().toString();
        date = cbjour.getValue().toString();
        enseignant = txtmat2.getText();

        if (this.con == null) {
            System.err.println("Error: Connection is null");
            return;
        }
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO `emploidutemps_db`.`cours` (`Classe`, `Matiere`, `Heure`, `Date`, `Enseignantmat`) VALUES (?,?,?,?,?)");
            pst.setString(1, classe);
            pst.setString(2, matiere);
            pst.setString(3, heure);
            pst.setString(4, date);
            pst.setString(5, enseignant);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement de cours");

            alert.setHeaderText("Enregistrement de cours");
            alert.setContentText("L'enregistrement terminé avec succès");
            alert.showAndWait();
            table2();

            // Clear input fields
            cbclasse.setValue(null);
            txtmatiere.setText("");
            cbheure.setValue(null);
            cbjour.setValue(null);
            txtmat2.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Emploi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void request(ActionEvent event) {
        try {
            Main.Face2Controller face2Controller = new Main.Face2Controller();
            face2Controller.openFace2();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
       table();
        table2();
        ObservableList<String> classes = FXCollections.observableArrayList("1er", "2eme", "3eme", "4eme", "5eme", "6eme");
        cbclasse.setItems(classes);
        ObservableList<String> hours = FXCollections.observableArrayList(
                "08H00", "09H00", "10H00", "11H00", "12H00", "13H00", "14H00", "15H00", "16H00", "17H00", "18H00");
        cbheure.setItems(hours);

    }


}