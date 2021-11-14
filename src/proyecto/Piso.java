/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.IOException;

/**
 *
 * @author cagaj
 */
public class Piso {
    
    private int numeroPiso;
    private ListaSalas listaSalas;

    public Piso (int numPiso, int cantSalas, int cantCamas){
        this.numeroPiso= numPiso;
        listaSalas = new ListaSalas(cantSalas,cantCamas);
    }

    public int getNumeroPiso() {
        return numeroPiso;
    }

    public void setNumeroPiso(int numeroPiso) {
        this.numeroPiso = numeroPiso;
    }

    public void setSalas(int sala){

            listaSalas.setNumSalas(sala);
    }
    
    public String getDiagnostico(int posSala,int posCama){
        return listaSalas.getDiagnostico(posSala,posCama);
    }

    public Paciente getPaciente(int posSala,int posCama){
        return listaSalas.getPaciente(posSala,posCama);
    }
    
    
    /*
        Llamados de listaSalas
    */
    public int tamanioSala(){
        return listaSalas.sizeSalas();
    }
    
    public void mostrarSalaPorPos(int posSala){
        listaSalas.mostrarSala(posSala);
    }
    
    public void mostrarSalas(int posPiso){
        listaSalas.mostrarSalas(posPiso);
    }
    
    public void agregarSala() throws IOException{
        listaSalas.ingresarSala();
    }

    public void modificarSala(int posSala) throws IOException{
        listaSalas.modificarSala(posSala);
    }

    public void eliminarSala() throws IOException{
        listaSalas.eliminarSalaPorSeleccion();
    }
    
    /*
        Llamado de camas
    */
    
    public void mostrarCamas(int posSala){
        listaSalas.mostrarCamas(posSala);
    }
   
    public void agregarCama(int posSala,int cantCamas){
        listaSalas.agregarCamas(posSala, cantCamas);
    } 
    
    public void modificarCama(int posSala) throws IOException{
        listaSalas.modificarCamas(posSala);
    }
    
    public Paciente eliminarCama(int posSala) throws IOException{
        return listaSalas.eliminarCama(posSala);
    }
    
    public int  sizeCamas(int posSala){
        return listaSalas.sizeCamas(posSala);
    }
    
    public void setDisponibilidad(boolean disponiblidad,int posCama,int posSala){
        listaSalas.setDisponibilidad(disponiblidad, posCama, posSala);
    }
    
    public boolean isDisponible(int posCama,int posSala){
        return listaSalas.isDisponible(posCama, posSala);
    }
    
    
    /*
        Llamado pacientes con camas
    */
    
    public boolean agregarPaciente(Paciente nuevoPaciente,int posSala){
        return listaSalas.agregarPaciente(nuevoPaciente, posSala);
    }
    
    public void mostrarPacientesConCama(int posSala){
        listaSalas.mostrarPacientes(posSala);
    }
    
    public void mostrarTodosPacientesCamas(){
        for (int i = 0; i < listaSalas.sizeSalas(); i++) {
            listaSalas.mostrarPacientes(i);
            System.out.println("----------------");
        }
    }
    
    public void eliminarPacienteConCama(int posSala) throws IOException{
        listaSalas.eliminarPaciente(numeroPiso);
    }
    
    public void mostrarGafete(int posPaciente,int posSala){
        listaSalas.mostrarGafete(posPaciente, posSala);
    }
    
    public String rutPaciente(int posPaciente,int posSala){
        return listaSalas.rutPaciente(posPaciente, posSala);
    }
    
    
}

