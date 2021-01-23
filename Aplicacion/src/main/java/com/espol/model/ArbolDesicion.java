/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author dilan
 */
public class ArbolDesicion {
    Node root;
    
    
    public boolean isEmpty(){
        return root==null;
    }
    
    public boolean remove (Dato data){
        Node padre = shearchParent(data);
        if (padre == null ) return  false;
        else if (padre.getLeft()!= null  && padre.getLeft().getInfo().equals(data)){
            padre.setLeft(null);
            return true;
        }
        else {
            padre.setRight(null);
            return true;
        }
    }
    
    public boolean add(Dato child , Dato parent){
        Node hijo = new Node(child);
        if(isEmpty() && parent==null){
            root = hijo;
            return true;
        }
        Node nc = shearchNode(parent);
        Node nce= shearchNode(child);
        if(nce==null && nc!= null){
            if(nc.getLeft()==null){
                nc.setLeft(hijo);
                return true;
            }else if(nc.getRight()==null){
                nc.setRight(hijo);
                return true;
            }
        }
        return false;
    
    }
    
    private Node shearchNode( Dato data){
        return shearchNode( data ,root);
    
    }
    
    
    private Node shearchNode(Dato data, Node n) {
        if (n == null) {
            return n;
        } else if (n.getInfo().equals(data.getInfo()) && 
                n.getTipo().equals(data.getTipo())) {
            return n;
        } else {
            Node nright = shearchNode(data, n.getRight());
            if (nright != null) {
                return nright;
            }
            return shearchNode(data, n.getLeft());
        }
    }
    
    
    private Node shearchParent( Dato data){
        return shearchParent( data ,root);
    
    }
    
    private Node shearchParent(Dato data, Node n){
        if (n == null) {
            return n;
        }
        else if( (n.getLeft()!= null && n.getLeft().getInfo().equals(data)) 
                || (n.getRight().getInfo().equals(data)  && n.getRight()!=null ) ){
                return n;
        }
        else {
                Node n1 = shearchParent(data , n.getLeft());
                if(n1 != null){
                    return n1;
                }
                return shearchParent(data , n.getRight());
        }
    }
  
    public void anchura() {
        if (!isEmpty()) {
            Queue<Node> cola = new LinkedList<>();
            cola.offer(root);
            while (!cola.isEmpty()) {
                Node nodo = cola.poll();
                System.out.println(nodo.getInfo() + " ");
                if (nodo.getLeft() != null ) {
                    cola.offer(nodo.getLeft());
                }
                if (nodo.getRight() != null) 
           
                    cola.offer(nodo.getRight());
            }

        }
    }
    
    public int size(){        
        return size(root);
    }
    
    private int size(Node n){
        if(n==null){
            return 0;
        }
        
        return  1 + size(n.getRight()) + size(n.getLeft());
    }
    
    public int altura(){
        return  1+altura(root);
    }
    
   private int altura(Node n){
        int Altder=0;
        int Altizq=0;
        if(n!=null){
             Altder = (n.getRight() == null? 0:1 + altura (n.getRight()));
             Altizq = (n.getLeft() == null? 0:1 + altura (n.getLeft())); 
        }
        return Math.max(Altder,Altizq);
    }
    
  
     
    public boolean cargarDatos(String path){
        Queue<Dato> datos = Dato.leerInfo(path);
        this.root = new Node(datos.poll());
        return add(root, datos);
    }
   
    private boolean add(Node node, Queue<Dato> cola) {
        if (node == null)  return false;
        if (!cola.isEmpty() && node.getTipo().equals("P")) {
            Dato dato = cola.poll();
            node.setLeft(new Node(dato));
            add(node.getLeft(), cola);
            dato = cola.poll();
            node.setRight(new Node(dato));
            add(node.getRight(), cola);
        }
        return true;
    }
    
    public Dato getRoot(){
        return root.getDato();
    }
    
    public boolean saveTree(String path){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            saveTree(root , bw);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    private void saveTree(Node nodo, BufferedWriter bw){
        if(nodo!=null){
            try {
                bw.write("#"+nodo.getTipo()+ nodo.getInfo()+"\n");
                saveTree(nodo.getLeft(),bw);
                saveTree(nodo.getRight() , bw);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } 
    }
    
    public void addYes(Dato datoOriginal , Dato datoPadre , Dato datoHijo){
        Node nodo = shearchNode( datoOriginal ,root);
        nodo.changeNode(datoPadre,datoHijo, datoOriginal);
    }
    
    public void addNo(Dato datoOriginal , Dato datoPadre , Dato datoHijo){
        Node nodo = shearchNode( datoOriginal ,root);
        nodo.changeNode(datoPadre, datoOriginal,datoHijo);
    }
    
    
    public Dato datoRight(Dato dato){
        return shearchNode(dato).getRight().getDato();
    
    }
    
    public Dato datoLeft(Dato dato){
        return shearchNode(dato).getLeft().getDato();
    
    }
    
}
