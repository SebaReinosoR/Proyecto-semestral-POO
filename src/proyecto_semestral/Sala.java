/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_semestral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author cagaj
 */
public class Sala {
    
    private int numeroSala;
    private int cantCamas;
    private Cama[] camas;
    private Paciente[] pacientesConCama;
    
    public Sala(int numeroSala, int cantCamas) {
        this.numeroSala = numeroSala;
        this.cantCamas = cantCamas;
        camas = new Cama[cantCamas];
        pacientesConCama = new Paciente[cantCamas];
        llenarPos(cantCamas);
    }

    public void llenarPos(int cantidadCamas){
        if((cantidadCamas-camas.length) >= 0){
            for (int i = 0; i < camas.length; i++) {
                camas[i] = new Cama(i+1);
                camas[i].setDisponibilidad(false);
            }
        }else{
            System.out.println("Supera el tamanio permitido");
        }
               
    }
    
     public void mostrarCama(){
        for (int i = 0; i < camas.length; i++) {
            System.out.print("La cama "+camas[i].getNumeroCama()+"Esta ");
            if (camas[i].isDisponibilidad()) {
                System.out.println("desocupada");
            }else{
                System.out.println("ocupada");
            }
        }
    }
    
    public void mostrarCama(int posCama){
        System.out.println("En la cama "+camas[posCama]+" esta ");
        if (!camas[posCama].isDisponibilidad()) {
                System.out.println("desocupada");
            }else{
                System.out.println("ocupada por "+pacientesConCama[posCama].getNombres());
            }
    }
    
    public void buscarCama() throws IOException{
        boolean flag = false;
        int posCama;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
        
        System.out.println("Ingrese el numero de la cama:");
        posCama = Integer.parseInt(lector.readLine());
        
        for (int i = 0; i < camas.length; i++) {
            if (camas[i].getNumeroCama() == camas[posCama].getNumeroCama()) {
                flag =  true;
            }
        }
        
        if (flag) {
            System.out.println("La cama "+posCama+" si se encuentra");
        }else{
            System.out.println("La cama solicitada no se encuetra");
        }
    }
    
    public void buscarCama(int posCama) throws IOException{
        
        boolean flag = false;
        String rutPaciente = null;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
        
        System.out.println("Ingrese el rut del paciente:");
        rutPaciente = lector.readLine();
        
        if (pacientesConCama[posCama].getRut().equals(rutPaciente)) {
            System.out.println("El paciente "+pacientesConCama[posCama].getNombres()+" se encuentra aqui");
        }else{
            System.out.println("El paciente solicitado no se encuentra en esta cama");
        }
        
        
    }
    
    public void modificarCama() throws IOException{
        boolean estado,camaEncontrada = false;
        int posCama;
        String decision = null,cambio = null;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
        
        System.out.println("Ingrese el numero de la cama:");
        posCama = Integer.parseInt(lector.readLine());
        
        for (int i = 0; i < camas.length; i++) {
            if (camas[i].getNumeroCama() == posCama) {
                camaEncontrada = true;
            }
        }
        
        if (camaEncontrada) {
            System.out.println("¿Desea cambiar el estado de la cama?: (si/no)");
            decision = lector.readLine();
            
            if(decision.equals("si")) {
                
                System.out.println("La cama"+camas[posCama]+" se encuentra ");
                if (camas[posCama].isDisponibilidad()) {
                    System.out.println("desocupada");
                }else{
                    System.out.println("ocupada por "+pacientesConCama[posCama].getNombres());
                }
                System.out.println("ingrese su disponibilidad: (ocupada/desocupada)");
                cambio = lector.readLine();
                if (cambio.equals("ocupada")) {
                    estado = false;
                    camas[posCama].setDisponibilidad(estado);
                }else{
                    if (cambio.equals("desocupada")) {
                        estado = true;
                        camas[posCama].setDisponibilidad(estado);
                    }
            }
                
            }else{
                System.out.println("Devolviendo al menu");
            }
            
        }else{
            System.out.println("La cama solicitada no se encuentra disponible");
        }
        
        
    }
    
    public void eliminarCama() throws IOException{
        
        boolean encontrado = false;
        int posCama;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
        
        System.out.println("Ingrese el numero de la cama:");
        posCama = Integer.parseInt(lector.readLine());
        
        for (int i = 0; i < camas.length; i++) {
            if (camas[i].getNumeroCama() == posCama) {
                encontrado = true;
            }
        }
        
        if (encontrado) {
            for (int i = 0; i < camas.length; i++) {
                if (camas[posCama] == camas[i]) {
                    camas[i] = null;
                    for (int j = i; j < camas.length-1; j++) {
                        camas[j] = camas[j+1];
                    }
                }
            }
        }else{
            System.out.println("La cama no ha podido ser encontrada");
        }
        
    }
      
    public void eliminarPaciente() throws IOException{
        
        boolean encontrado = false;
        String rutPaciente = null;
        int posPaciente = -1;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); 
        
        System.out.println("Ingrese rut del paciente:");
        rutPaciente = lector.readLine();
        
        for (int i = 0; i < pacientesConCama.length; i++) {
            if (pacientesConCama[i].getRut().equals(rutPaciente)) {
                encontrado = true;
                posPaciente = i;
            }
        }
        
        if (encontrado) {
            
            pacientesConCama[posPaciente] = null;
            camas[posPaciente].setDisponibilidad(true);
            
        }else{
            System.out.println("El paciente no ha sido encontrado");
        }
        
    }

    public String diagnosticoPaciente(int posCama){
        return pacientesConCama[posCama].getDiagnostico();
    }
    
    public Paciente retornoPaciente(int posicion){
        return pacientesConCama[posicion];
    }
    
    /*
        getters and setters de Sala
    */
    
    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getCantCamas() {
        return camas.length;
    }

    public void setCantCamas(int cantCamas) {
        this.cantCamas = cantCamas;
    }
    
    
    
    
}
