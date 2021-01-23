/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author dilan
 */
public class Dato {
    private String tipo;
    private String info;

    public Dato(String tipo, String info) {
        this.tipo = tipo;
        this.info = info;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public boolean esRespuesta(){
        return "R".equals(tipo);    
    }
    
    public boolean esPregunta(){
        return "P".equals(tipo);    
    }
    
    public static Queue<Dato> leerInfo(String path){
        Queue<Dato> colaDatos = new LinkedList<>();
        String[] datosSplit;
        String linea;
        try(BufferedReader br = new BufferedReader(new FileReader(path,
        StandardCharsets.UTF_8))) {
            while((linea = br.readLine())!=null){
                if(linea.contains("R")){
                    datosSplit = linea.split("#R");
                    colaDatos.offer(new Dato("R", datosSplit[1]));
                }
                else {
                    datosSplit = linea.split("#P");
                    colaDatos.offer(new Dato("P", datosSplit[1])); 
                
                }
                   
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return colaDatos;
    }
}
