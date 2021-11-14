/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.*;
import java.util.*;
/**
 *
 * @author cagaj
 */
public class ListaSalas {

    private int numSalas;
    private HashMap<Integer, Sala> salas;
    private int numCama;

    public ListaSalas(int numSalas, int numCama) {
        this.numSalas = numSalas;
        this.numCama = numCama;
        salas = new HashMap<>();
        llenarSalas(numSalas, numCama);
    }

    private void llenarSalas(int cantidadSalas, int cantidadCamas) {
        for (int i = 0; i < cantidadSalas; i++) {
            Sala nuevaSala = new Sala(i + 1,cantidadCamas);
            salas.put(i, nuevaSala);
        }
    }

    /*
        Funciones de las salas
    */
    
    public int sizeSalas() {
        return salas.size();
    }

    public void mostrarSalas(int posPiso) {
        for (int i : salas.keySet()) {
            System.out.println("Estas en la sala " + i + " del piso " + posPiso) //numeroPiso (debe llamarse desde piso) );
                    ;
        }
    }

    public void ingresarSala() throws IOException {

        int numeroCamas, numeroSala;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Sala nuevaSala;

        if (salas.size() - 1 > numSalas) {
            System.out.println("Ingrese la cantidad de camas que tendra la sala:");
            numeroCamas = Integer.parseInt(lector.readLine());
            numeroSala = salas.size() - 1;
            nuevaSala = new Sala(numeroSala, numeroCamas);
            salas.put(numeroSala, nuevaSala);
        } else {
            System.out.println("Pasa el limite de camas totales");
        }
    }

    public void mostrarSala(int posSala) {
        System.out.print("Estas en la sala " + posSala + " del piso ");//numeroPiso (debe llamarse desde piso));
                
    }

    public void buscarSala() throws IOException {

        int keySalaBuscar = 0;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el codigo de la sala a buscar:");
        keySalaBuscar = Integer.parseInt(lector.readLine());

        if (salas.containsKey(keySalaBuscar)) {
            System.out.println("La sala " + keySalaBuscar + " si se encuentra");
        } else {
            System.out.println("La sala solicitada no se encuentra");
        }

    }

    public void modificarSala(int posSala) throws IOException {

        int cantidadCamas;
        String decision = null;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        if (salas.containsKey(posSala)) {
            System.out.println("Sala " + posSala + " con "
                    + salas.get(posSala).getCantCamas() + " camas.");
            System.out.println("¿Desea modificar el numero de camas? (si/no)");
            decision = lector.readLine();
            if (decision.equals("si")) {
                System.out.println("Ingrese la nueva cantidad de camas:");
                cantidadCamas = Integer.parseInt(lector.readLine());
                salas.get(posSala).setCantCamas(cantidadCamas);
            } else {
                if (decision.equals("no")) {
                    System.out.println("Devolviendo al menu");
                }
            }
        }
    }

    public void eliminarSalaPorSeleccion() throws IOException {

        int keySalaEliminar;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el numero de la sala a eliminar:");
        keySalaEliminar = Integer.parseInt(lector.readLine());

        if (salas.containsKey(keySalaEliminar)) {
            salas.remove(keySalaEliminar);
            System.out.println("Sala borrada.");
        } else {
            System.out.println("La sala solicitada no se encuentra.");
        }

    }

    public void setSalas(HashMap<Integer, Sala> salas) {
        this.salas = salas;
    }
    
    
    /*
        Funciones de camas
    */
    
    public void mostrarCamas(int posSala) {
        salas.get(posSala).mostrarCamas();
    }

    public void agregarCamas(int posSala, int cantidadCamas) {
        salas.get(posSala).agregarCamas(cantidadCamas);
    }

    public void buscarCama(int posSala) throws IOException {
        salas.get(posSala).buscarCama();
    }

    public void modificarCamas(int posSala) throws IOException {
        salas.get(posSala).modificarCamas();
    }

    public Paciente eliminarCama(int posSala) throws IOException {
        return salas.get(posSala).eliminarCama();
    }

    public int sizeCamas(int posSala) {
        return salas.get(posSala).getCantCamas();
    }
    
    public void setDisponibilidad(boolean disponibiliad,int posCama,int posSala){
        salas.get(posSala).setDisponibilidad(disponibiliad, posCama);
    }
    
    public boolean isDisponible(int posCama,int posSala){
        return salas.get(posSala).isDisponible(posCama);
    }
    
    /*
        Funciones de pacientes con camas
    */

    public boolean agregarPaciente(Paciente nuevoPaciente,int posSala){
        return salas.get(posSala).agregarPaciente(nuevoPaciente);
    }
    
    public void mostrarPacientes(int posSala){
        salas.get(posSala).mostrarPacientes();
    }
    
    public void eliminarPaciente(int posSala) throws IOException{
        salas.get(posSala).eliminarPaciente();
    }
    
    public String getDiagnostico(int posSala, int posCama) {
        return salas.get(posSala).getDiagnostico(posCama);
    }

    public Paciente getPaciente(int posSala, int posCama) {
        return salas.get(posSala).getPaciente(posCama);
    }
    
    public void mostrarGafete(int posPaciente,int posSala){
        salas.get(posSala).mostrarGafete(posPaciente);
    }
    
    public String rutPaciente(int posPaciente,int posSala){
        return salas.get(posSala).rutPaciente(posPaciente);
    }
    
    /*
        Getters & Setters
    */
    
    public int getNumSalas() {
        return numSalas;
    }

    public void setNumSalas(int numSalas) {
        this.numSalas = numSalas;
    }

    public int getNumCama() {
        return numCama;
    }

    public void setNumCama(int numCama) {
        this.numCama = numCama;
    }

    

    
}
