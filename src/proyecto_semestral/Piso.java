/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_semestral;

import java.io.*;
import java.util.*;

/**
 *
 * @author cagaj
 */
public class Piso {
    
    private int numeroPiso;
    private int cantidadSalas;
    private HashMap<Integer,Sala> salas;

    public Piso(int numeroPiso, int cantidadSalas, int cantidadCamas) {
        this.numeroPiso = numeroPiso;
        this.cantidadSalas = cantidadSalas;
        salas = new HashMap<>();
        llenarSalas(cantidadSalas,cantidadCamas);
    }
    
    public void llenarSalas(int cantidadSalas,int cantidadCamas){
        for (int i = 0; i < cantidadSalas; i++) {
            Sala nuevaSala = new Sala(i+1,cantidadCamas);
            salas.put(i, nuevaSala);
        }
    }
    
    
    /*
        Funciones para el manejo de las salas
    */
    public int sizeSalas(){
        return salas.size();
    }
    
    public void mostrarSalas(){
        for (int i : salas.keySet()) {
            System.out.println("Estas en la sala "+i+" del piso"+numeroPiso);
        }
    }
    
    public void ingresarSala() throws IOException{
  
        int numeroCamas,numeroSala;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
        Sala nuevaSala;
        
        if(salas.size()-1 > cantidadSalas){
            System.out.println("Ingrese la cantidad de camas que tendra la sala:");
            numeroCamas = Integer.parseInt(lector.readLine());
            numeroSala = salas.size()-1;
            nuevaSala = new Sala(numeroSala,numeroCamas);
            salas.put(numeroSala, nuevaSala);
        }else{
            System.out.println("Pasa el limite de camas totales");
        }
    }
    
    public void mostrarSalas(int posSala){
        System.out.println("Estas en la sala "+posSala+" del piso"+numeroPiso); 
    }
    
    public void buscarSala() throws IOException{
        
        int keySalaBuscar = 0;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
        
        System.out.println("Ingrese el codigo de la sala a buscar:");
        keySalaBuscar = Integer.parseInt(lector.readLine());
        
        if(salas.containsKey(keySalaBuscar)){
            System.out.println("La sala "+keySalaBuscar+" si se encuentra" );
        }else{
            System.out.println("La sala solicitada no se encuentra");
        }
        
    }
     
    public void modificarSalas() throws IOException{
        
        int keySalaModificar,cantidadCamas;
        String decision = null;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
        
        System.out.println("Ingrese el codigo de la sala a modificar:");
        keySalaModificar = Integer.parseInt(lector.readLine());
        
        if(salas.containsKey(keySalaModificar)){
            System.out.println("Sala "+keySalaModificar+" con "
                    +salas.get(keySalaModificar).getCantCamas()+" camas.");
            System.out.println("¿Desea modificar el numero de camas? (si/no)");
            decision = lector.readLine();
            if(decision.equals("si")){
                System.out.println("Ingrese la nueva cantidad de camas:");
                cantidadCamas = Integer.parseInt(lector.readLine());
                salas.get(keySalaModificar).setCantCamas(cantidadCamas);
            }else{
                if(decision.equals("no")){
                    System.out.println("Devolviendo al menu");
                }
            } 
        }
        
        
        
    }
     
    public void eliminarSala() throws IOException{
        
         int keySalaEliminar;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
        
        System.out.println("Ingrese el numero de la sala a eliminar:");
        keySalaEliminar = Integer.parseInt(lector.readLine());
        
        if (salas.containsKey(keySalaEliminar)) {
            salas.remove(keySalaEliminar);
            System.out.println("Sala borrada.");
        }else{
            System.out.println("La sala solicitada no se encuentra.");
        }
        
    }
    
    /*
        Llamada las funcionamientos de las camas/pacientes
    */
    
    public void mostrarCamas(int posSala){
        salas.get(posSala).mostrarCama();
    }
    
    public void agregarCamas(int posSala,int cantidadCamas){
        salas.get(posSala).llenarPos(cantidadCamas);
    }
    
    public void buscarCama(int posSala) throws IOException{
        salas.get(posSala).buscarCama();
    }
    
    public void modificarCamas(int posSala) throws IOException{
        salas.get(posSala).modificarCama();
    }
    
    public void eliminarCama(int posSala) throws IOException{
        salas.get(posSala).eliminarCama();
        salas.get(posSala).eliminarPaciente(); //ESTO HAY QUE REACOMODARLO
                                               //HAY QUE COLOCARLO EN UNA NUEVA CAMA
    }
    
    public int sizeCamas(int posSala){
        return salas.get(posSala).getCantCamas();
    }
    
    public String getDiagnostico(int posSala,int posCama){
        return salas.get(posSala).diagnosticoPaciente(posCama);
    }
    
    public Paciente getPaciente(int posSala,int posCama){
        return salas.get(posSala).retornoPaciente(posCama);
    }
   /*
        getters y setters de Piso
    */
    
    public int getNumeroPiso() {
        return numeroPiso;
    }

    public void setNumeroPiso(int numeroPiso) {
        this.numeroPiso = numeroPiso;
    }

    public int getCantidadSalas() {
        return cantidadSalas;
    }

    public void setCantidadSalas(int cantidadSalas) {
        this.cantidadSalas = cantidadSalas;
    }
    
   
    
  
}
