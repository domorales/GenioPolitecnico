/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.model;

/**
 *
 * @author dilan
 */
public class Node {

    private Node left;
    private Node right;
    private Dato dato;

    public Node(Dato dato) {
        this.dato = dato;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getInfo() {
        return dato.getInfo();
    }

    public void setInfo(String info) {
        this.dato.setInfo(info);
    }

    public String getTipo() {
        return dato.getTipo();
    }

    public void setTipo(String tipo) {
        this.dato.setTipo(tipo);
    }

    public Dato getDato() {
        return dato;
    }

    public void setDato(Dato dato) {
        this.dato = dato;
    }

    public boolean changeNode(Dato datoPadre, Dato datoLeft , Dato datoRight ){
        if(datoPadre== null && datoLeft==null && datoRight==null)
            return  false;
        this.dato = datoPadre;
        this.left = new Node(datoLeft);
        this.right = new Node(datoRight);
        
        return true;
    
    }
    
    

}
