/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.controller;

import com.espol.geniopolitecnico.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author dilan
 */
public class VistaPrincipalController implements Initializable {

    @FXML
    private Button buttonPlay;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void buttonExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void buttonPlay(ActionEvent event) {
        try {
            App.setRoot("VentanaJuego");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    
}
