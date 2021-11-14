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
public class ListaCamas {

    private int cantCamas;
    private Cama[] camas;
    private Paciente[] pacienteConCama;

    public ListaCamas(int cantCamas) {
        camas = new Cama[cantCamas];
        pacienteConCama = new Paciente[cantCamas];
        agregarCamasNuevas(cantCamas);
    }

    public int getCantCamas() {
        return camas.length;
    }

    public void setCantCamas(int cantCamas) {
        this.cantCamas = cantCamas;
    }

    public Cama getCamas(int posicion) {
        return camas[posicion];
    }

    public void setDisponibilidad(boolean disponible,int posCama){
        this.camas[posCama].setDisponibilidad(disponible);
    }
    
    public boolean isDisponible(int posCama){
        return this.camas[posCama].isDisponibilidad();
    }
    
    public void setCamas(Cama camaP, int posicion) {
        this.camas[posicion] = camaP;
    }

    public void agregarCamasNuevas(int cantidadCamas) {
        if ((cantidadCamas - camas.length) >= 0) {
            for (int i = 0; i < camas.length; i++) {
                camas[i] = new Cama(i + 1);
                camas[i].setDisponibilidad(false);
            }
        } else {
            System.out.println("Supera el tamanio permitido");
        }
    }

    public void mostrarCama() {
        for (int i = 0; i < camas.length; i++) {
            System.out.print("La cama " + camas[i].getNumeroCama() + "Esta ");
            if (camas[i].isDisponibilidad()) {
                System.out.println("desocupada");
            } else {
                System.out.println("ocupada");
            }
        }
    }

    public void mostrarCama(int posCama) {
        System.out.println("En la cama " + camas[posCama] + " esta ");
        if (!camas[posCama].isDisponibilidad()) {
            System.out.println("desocupada");
        } else {
            System.out.println("ocupada por " + pacienteConCama[posCama].getNombres());
        }
    }

    public void buscarCama() throws IOException {
        boolean flag = false;
        int posCama;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el numero de la cama:");
        posCama = Integer.parseInt(lector.readLine());

        for (int i = 0; i < camas.length; i++) {
            if (camas[i].getNumeroCama() == camas[posCama].getNumeroCama()) {
                flag = true;
            }
        }

        if (flag) {
            System.out.println("La cama " + posCama + " si se encuentra");
        } else {
            System.out.println("La cama solicitada no se encuetra");
        }
    }

    public void buscarCama(int posCama) throws IOException {

        boolean flag = false;
        String rutPaciente = null;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el rut del paciente:");
        rutPaciente = lector.readLine();

        if (pacienteConCama[posCama].getRut().equals(rutPaciente)) {
            System.out.println("El paciente " + pacienteConCama[posCama].getNombres() + " se encuentra aqui");
        } else {
            System.out.println("El paciente solicitado no se encuentra en esta cama");
        }

    }

    public void modificarCama() throws IOException {
        boolean estado, camaEncontrada = false;
        int posCama;
        String decision = null, cambio = null;
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

            if (decision.equals("si")) {

                System.out.println("La cama" + camas[posCama] + " se encuentra ");
                if (camas[posCama].isDisponibilidad()) {
                    System.out.println("desocupada");
                } else {
                    System.out.println("ocupada por " + pacienteConCama[posCama].getNombres());
                }
                System.out.println("ingrese su disponibilidad: (ocupada/desocupada)");
                cambio = lector.readLine();
                if (cambio.equals("ocupada")) {
                    estado = false;
                    camas[posCama].setDisponibilidad(estado);
                } else {
                    if (cambio.equals("desocupada")) {
                        estado = true;
                        camas[posCama].setDisponibilidad(estado);
                    }
                }

            } else {
                System.out.println("Devolviendo al menu");
            }

        } else {
            System.out.println("La cama solicitada no se encuentra disponible");
        }

    }

    public Paciente eliminarCama() throws IOException {

        boolean encontrado = false;
        Paciente pacienteSinCama = null;
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
                    pacienteSinCama = pacienteConCama[i];
                    camas[i] = null;
                    for (int j = i; j < camas.length - 1; j++) {
                        camas[j] = camas[j + 1];
                    }
                }
            }
            System.out.println("Eliminado");
        } else {
            System.out.println("La cama no ha podido ser encontrada");
        }
        return pacienteSinCama;

    }
    

    /*
        Pacientes
    */
    
    public boolean agregarPaciente(Paciente nuevoPaciente){
        boolean agregado = false;
        int posAgreado = 0;
        for (int i = 0; i < pacienteConCama.length; i++) {
            if (pacienteConCama[i] == null) {
                pacienteConCama[i] = nuevoPaciente;
                posAgreado = i;
                agregado = true;
                camas[i].setDisponibilidad(true);
            }
        }
        
        return agregado;
    } 
    
    public void listarPacientes(){
        for (int i = 0; i < pacienteConCama.length; i++) {
            pacienteConCama[i].gafete();
        }
    }
    
    public void eliminarPaciente() throws IOException {

        boolean encontrado = false;
        String rutPaciente = null;
        int posPaciente = -1;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese rut del paciente:");
        rutPaciente = lector.readLine();

        for (int i = 0; i < pacienteConCama.length; i++) {
            if (pacienteConCama[i].getRut().equals(rutPaciente)) {
                encontrado = true;
                posPaciente = i;
            }
        }

        if (encontrado) {

            pacienteConCama[posPaciente] = null;
            camas[posPaciente].setDisponibilidad(true);

        } else {
            System.out.println("El paciente no ha sido encontrado");
        }

    }
    
    public String rutPaciente(int posPaciente){
        return pacienteConCama[posPaciente].getRut();
    }

    public String diagnosticoPaciente(int posPaciente) {
        return pacienteConCama[posPaciente].getDiagnostico();
    }

    public Paciente retornoPaciente(int posicion) {
        return pacienteConCama[posicion];
    }
    
    public void mostrarGafete(int posPaciente){
        pacienteConCama[posPaciente].gafete();
    }

}
