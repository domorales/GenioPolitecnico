/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.controller;

import com.espol.model.ArbolDesicion;
import com.espol.model.Dato;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author dilan
 */
public class VentanaJuegoController implements Initializable {

    @FXML
    private Label labelRespuestaArbol;
    @FXML
    private ImageView imagenTitulo;
    @FXML
    private Pane panelGame;
    @FXML
    private TextArea textRespuesta;
    @FXML
    private Label labelPregunta;
    @FXML
    private TextArea textPregunta;
    @FXML
    private Label labelOracion;
    @FXML
    private Pane panelFormular;
    
    private ArbolDesicion arbolDesicion;
    private Dato dato;
    private Dato datoHijo;
    private Dato datoPadre;
    private Dato datoOriginal;
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private DialogPane dialogPane;
    private static final String  cssDiagPanel= "file:src/main/resources/com/espol/css/"
                + "dialogPane.css";
    private static final String imgRespuesta = "src/main/resources/com/espol/imagenes/"
                                + "respuesta.png";
    private static final String imgPregunta = "src/main/resources/com/espol/imagenes/"
                                + "pregunta.png";
    private static final String enlaceInfo = "info.txt" ;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textPregunta.setTextFormatter(new TextFormatter<>(change -> 
                (change.getControlNewText().matches("[a-zA-Z ]*")? change : null)));
        textRespuesta.setTextFormatter(new TextFormatter<>(change -> 
                (change.getControlNewText().matches("[a-zA-Z ]*")? change : null)));
        arbolDesicion = new ArbolDesicion();
        arbolDesicion.cargarDatos(enlaceInfo);
        initComponents();

        
    }    

    @FXML
    private void buttonNo(ActionEvent event) throws IOException {
        if(dato.esPregunta()){
            dato= arbolDesicion.datoRight(dato);
            labelRespuestaArbol.setText(dato.getInfo());
            if(dato.esRespuesta());
        }
        else {
            imagenTitulo.setImage(new Image(
                        new File(imgRespuesta).toURI().toString()));
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Ayudame, a mejorar mi predicción");
            alert.setHeaderText(null);
            alert.showAndWait();
            panelGame.setVisible(false);
            panelFormular.setVisible(true);

        }
 
    }

    @FXML
    private void buttonYes(ActionEvent event) throws URISyntaxException {
        if(dato.esPregunta()){
            dato= arbolDesicion.datoLeft(dato);
            labelRespuestaArbol.setText(dato.getInfo());
            if(dato.esRespuesta())
                imagenTitulo.setImage(new Image(
                        new File(imgRespuesta).toURI().toString()));
        }
        else{
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Jugar de nuevo");
            alert.setContentText("Quieres jugar de nuevo?");
            alert.showAndWait();
            if(alert.getResult().getText().equals("Aceptar")) initComponents();
            else System.exit(0);
        }  
    }
    
    @FXML
    private void textRespuesta(KeyEvent event) {
        labelPregunta.setText("Escribe una pregunta que me permita "
                + "diferenciar entre " + textRespuesta.getText() + " y "
                + dato.getInfo());
    }

    @FXML
    private void textPregunta(KeyEvent event) {
       labelOracion.setText("Para "+ textRespuesta.getText() + 
               ", la respuesta a la "+ "pregunta: "+"'"+ textPregunta.getText()
               +"'"+", es SI o NO?");
    }

    @FXML
    private void buttonNoForm(ActionEvent event) {
        if(validateInText()){
            saveData();
            arbolDesicion.addNo(datoOriginal, datoPadre, datoHijo);
            arbolDesicion.saveTree(enlaceInfo);
            initComponents();
        }   
    }

    @FXML
    private void buttonYesForm(ActionEvent event) {
        if(validateInText()){
            saveData();
            arbolDesicion.addYes(datoOriginal, datoPadre, datoHijo);
            arbolDesicion.saveTree(enlaceInfo);
            initComponents();
        }   
    }
    
    private void saveData(){
        datoHijo = new Dato("R"," "+textRespuesta.getText());
        datoOriginal = dato;
        datoPadre= new Dato("P"," "+textPregunta.getText());
    }
    
    private void initComponents(){
        panelFormular.setVisible(false);
        panelGame.setVisible(true);
        dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(cssDiagPanel);
        dialogPane.getStyleClass().add("dialog-panel");
        dato = arbolDesicion.getRoot();
        labelRespuestaArbol.setText(dato.getInfo());
        imagenTitulo.setImage(new Image(
                        new File(imgPregunta).toURI().toString()));
        labelRespuestaArbol.setText(dato.getInfo());
        
    }
    
    private boolean validateInText(){
        boolean textNoVali = textRespuesta.getText().isEmpty() || 
                textPregunta.getText().isEmpty();
        if(textNoVali){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setContentText("No se aceptan campos vacios");
            alert.show();
            return false;
        }
        return true;
    }
}
