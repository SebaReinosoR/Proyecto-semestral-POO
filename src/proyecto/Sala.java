/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;
import java.io.*;
/**
 *
 * @author cagaj
 */
public class Sala {
    
     private int numeroSala;
    private ListaCamas camasPiso;

    public Sala(int numeroSala, int cantCamas) {
        this.numeroSala = numeroSala;
        camasPiso = new ListaCamas(cantCamas);

    }
    
    /*
        Llamado a las camas
    */
    
    public int getCantCamas(){
        return camasPiso.getCantCamas();
    }
    
    public  void setCantCamas(int cantCama){
        this.camasPiso.setCantCamas(cantCama);
    }
    
    public void mostrarCamas(){
        camasPiso.mostrarCama();
    }
    
    public void setDisponibilidad(boolean disponible,int posCama){
        camasPiso.setDisponibilidad(disponible, posCama);
    }
    
    public boolean isDisponible(int posCama){
        return camasPiso.isDisponible(numeroSala);
    }
    
    public void agregarCamas(int cantidadCamas){
        camasPiso.agregarCamasNuevas(cantidadCamas);
    }
    
    public void buscarCama() throws IOException {
        camasPiso.buscarCama();
    }
    
    public void modificarCamas() throws IOException{
        camasPiso.modificarCama();
    }
    
    public Paciente eliminarCama() throws IOException{
        return camasPiso.eliminarCama(); 
    }
    
    public int sizeCamas(){
        return camasPiso.getCantCamas();
    }
    
    /*
        Llamado a pacientes con camas
    */
    
    public boolean agregarPaciente(Paciente nuevoPaciente){
        return camasPiso.agregarPaciente(nuevoPaciente);
    }
    
    public void mostrarPacientes(){
        camasPiso.listarPacientes();
    }
    
    public void eliminarPaciente() throws IOException{
        camasPiso.eliminarPaciente();
    }
    
    public String getDiagnostico(int posCama){
        return camasPiso.diagnosticoPaciente(posCama);
    }
    
    public Paciente getPaciente(int posCama){
        return camasPiso.retornoPaciente(posCama);
    }

    public void mostrarGafete(int posPaciente){
        camasPiso.mostrarGafete(posPaciente);
    }
    
    public String rutPaciente(int posPaciente){
        return camasPiso.rutPaciente(posPaciente);
    }
    
    
    
    
    

    
    
}
