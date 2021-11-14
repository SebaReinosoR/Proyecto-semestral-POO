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
public class ListaPisos {

    private HashMap<Integer, Piso> pisos;

    public ListaPisos() {
        pisos = new HashMap<>();
    }

    /*
        funciones de los pisos
     */
    public void llenarPisos(int cantidadPisos, int cantidadSalas, int cantidadCamas) {

        for (int i = 0; i < cantidadPisos; i++) {
            Piso pisoAgregado = new Piso(i + 1, cantidadSalas, cantidadCamas);
            pisos.put(i + 1, pisoAgregado);
        }

    }

    public void mostrarPisoPorPos(int posPiso) {
        System.out.println("Usted esta en el piso " + posPiso);
        for (int i = 0; i < pisos.get(posPiso).tamanioSala(); i++) {
            pisos.get(posPiso).mostrarSalaPorPos(i);
            System.out.println(posPiso);
        }

    }

    public void mostrarPisos() {
        for (int i = 1; i <= pisos.size(); i++) {
            this.mostrarPisoPorPos(i);
            System.out.println("------------------------");
        }
    }

    public void agregarPiso() throws IOException {

        int numeroPiso, cantidadSalas, cantidadCamas;
        Piso nuevoPiso;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el numero del piso:");
        numeroPiso = Integer.parseInt(lector.readLine());

        System.out.println("Ingrese la cantidad de salas:");
        cantidadSalas = Integer.parseInt(lector.readLine());

        System.out.println("Ingrese la cantidad de salas:");
        cantidadCamas = Integer.parseInt(lector.readLine());

        nuevoPiso = new Piso(numeroPiso, cantidadSalas, cantidadCamas); //MODIFICAR

        if (!pisos.containsValue(nuevoPiso)) {
            pisos.put(numeroPiso, nuevoPiso);
            System.out.println("pisos agregado con exito");
        } else {
            System.out.println("El piso ya se encuentra");
        }

    }

    public void buscarPiso() throws IOException {

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int keyPisoBuscar = 0;

        System.out.println("Ingrese el numero del piso a buscar:");
        keyPisoBuscar = Integer.parseInt(lector.readLine());

        if (pisos.containsKey(keyPisoBuscar)) {
            System.out.println("el piso " + keyPisoBuscar + " SI se encuentra");
        } else {
            System.out.println("el piso " + keyPisoBuscar + " NO se encuentra");
        }

    }

    public void modificarPiso() throws IOException {

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int keyPisoModificar = 0;

        System.out.println("Ingrese el numero del piso a Modificar:");
        keyPisoModificar = Integer.parseInt(lector.readLine());
        String decision = null;
        if (pisos.containsKey(keyPisoModificar)) {

            System.out.println("Desea modificar la cantidad de salas: (si/no)");
            decision = lector.readLine();

            if (decision.equals("si")) {
                int salas = 0;
                System.out.println("Ingrese el nuevo numero de salas:");
                salas = Integer.parseInt(lector.readLine());
                pisos.get(keyPisoModificar).setSalas(salas);
                System.out.println("Cambio exitoso.");
            } else {
                if (decision.equals("no")) {
                    System.out.println("Terminando accion");
                }
            }

        }
    }

    public void eliminarPiso() throws IOException {

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int keyPisoEliminar;

        System.out.println("Ingrese el numero del piso a eliminar");
        keyPisoEliminar = Integer.parseInt(lector.readLine());

        if (pisos.containsKey(keyPisoEliminar)) {
            System.out.println("El piso " + keyPisoEliminar + " sera eliminado");
            pisos.remove(keyPisoEliminar);
        } else {
            System.out.println("El piso solicitado no se encuentra/no existe");
        }

    }

    public int sizePisos() {
        return pisos.size();
    }

    public String diagnosticoPaciente(int posPiso, int posSala, int posCama) {
        return pisos.get(posPiso).getDiagnostico(posSala, posCama);
    }

    public Paciente pacienteSolicitado(int posPiso, int posSala, int posCama) {
        return pisos.get(posPiso).getPaciente(posSala, posCama);
    }

    /*
        Llamados a las salas
     */
    public int sizeSalas(int posPiso) {
        return pisos.get(posPiso).tamanioSala();
    }
    
    public void mostrarSala(int posPiso,int posSala){
        pisos.get(posPiso).mostrarSalaPorPos(posSala);
    }
    
    public void exponerSalas(int posPiso){
        pisos.get(posPiso).mostrarSalas(posPiso);
    }
    
    public void agregarSala(int posPiso) throws IOException{
        pisos.get(posPiso).agregarSala();
    }
    
    public void modificarSala(int posPiso,int posSala) throws IOException{
        pisos.get(posPiso).modificarSala(posSala);
    }
    
    public void eliminarSala(int posPiso) throws IOException{
        pisos.get(posPiso).eliminarSala();
    }
    
    /*
        Llamado a las camas
    */
    
    public void mostrarCamas(int posPiso,int posSala){
        pisos.get(posPiso).mostrarCamas(posSala);
    }
    
    public void agregarCamas(int posPiso,int posSala,int cantCamas){
        pisos.get(posPiso).agregarCama(posSala, cantCamas);
    }
    
    public void modificarCama(int posPiso,int posSala) throws IOException{
        pisos.get(posPiso).modificarCama(posSala);
    }
    
    public Paciente eliminarCama(int posPiso,int posSala) throws IOException{
        return pisos.get(posPiso).eliminarCama(posSala);
    }
    
    public int sizeCama(int posPiso,int posSala){
        return pisos.get(posPiso).sizeCamas(posSala);
    }
    
    public void setDisponibilidad(boolean disponible,int posCama,int posSala,int posPiso){
        pisos.get(posPiso).setDisponibilidad(disponible, posCama, posSala);
    }
    
    public boolean isDisponible(int posCama,int posSala,int posPiso){
        return pisos.get(posPiso).isDisponible(posCama, posSala);
    }
    
    /*
        Llamado a los pacientes con camas
    */
    
    public boolean agregarPaciente(Paciente nuevoPaciente,int posSala,int posPiso){
        return pisos.get(posPiso).agregarPaciente(nuevoPaciente, posSala);
    }
    
    public void mostrarPacientesConCamas(int posPiso,int posSala){
        pisos.get(posPiso).mostrarPacientesConCama(posSala);
    }
    
    public void mostrarTodosPacientesCamas(){
        for (int i = 1; i < pisos.size(); i++) {
            for (int j = 0; j < pisos.get(i).tamanioSala(); j++) {
                pisos.get(i).mostrarPacientesConCama(j);
                System.out.println("--------------------");
            }
        }
    }
    
    public void eliminarPaciente(int posPiso,int posSala) throws IOException{
        pisos.get(posPiso).eliminarPacienteConCama(posSala);
    }
    
    public void mostrarGafete(int posPaciente,int posSala,int posPiso){
        pisos.get(posPiso).mostrarGafete(posPaciente, posSala);
    }
    
    public String rutPaciente(int posPaciente,int posSala,int posPiso){
        return pisos.get(posPiso).rutPaciente(posPaciente, posSala);
    }
    

}
