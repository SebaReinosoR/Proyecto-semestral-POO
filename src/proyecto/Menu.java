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
public class Menu {

    private Hospital hospital;
    private int posPiso, posSala;

    public Menu() {
        hospital = new Hospital("Hospital General Humberto Suazo", "Av. Charles Aranguiz 263", 98136, 5, 15, 10);
        posPiso = 1;
        posSala = 0;
    }

    public void panel() throws IOException {

        int entrada = 0;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("--------------------------------");
            System.out.println("Por favor ingrese una seccion:");
            System.out.println("1.- Pisos\n"
                    + "2.- Salas\n"
                    + "3.- Camas\n"
                    + "4.- Pacientes\n"
                    + "5.- Funcionarios\n"
                    + "0.- Salir\n");

            entrada = Integer.parseInt(lector.readLine());
            switch (entrada) {
                case 1:
                    funcionamientoPisos();
                    break;
                case 2:
                    funcionamientoSalas();
                    break;
                case 3:
                    funcionamientoCamas();
                    break;
                case 4:
                    funcionamientoPacientes();
                    break;
                case 5:
                    funcionamientoFuncionarios();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }

        } while (entrada != 0);

    }

    public int posicionPiso() throws IOException {

        int entrada, numeroPiso = 1;
        boolean flag = false;

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("¿Usted sabe en que piso se encuentra?\n"
                + "1.-Si\n"
                + "2.-No\n");

        entrada = Integer.parseInt(lector.readLine());

        switch (entrada) {
            case 1:
                System.out.println("Por favor ingresar en el piso que se encuentra:");
                numeroPiso = Integer.parseInt(lector.readLine());
                break;
            case 2:
                int pivote;
                do {
                    System.out.println("Seleccione en que piso esta posicionado"); //necesito re-formular la pregunta
                    for (int i = 0; i < hospital.entregarSizePiso(); i++) {
                        if (i < hospital.entregarSizePiso() - 1) {
                            System.out.print((i + 1) + " - ");
                        } else {
                            if (i == hospital.entregarSizePiso() - 1) {
                                System.out.println(i + 1);
                            }
                        }
                    }
                    pivote = Integer.parseInt(lector.readLine());
                    if (pivote < hospital.entregarSizePiso()) {
                        numeroPiso = pivote;
                        flag = true;
                    } else {
                        System.out.println("Ingrese un numero valido");
                    }
                } while (flag != true);

        }

        return numeroPiso;
    }

    public int posicionSala() throws IOException {
        int entrada, numeroSala = 1;
        boolean flag = false;

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("¿Usted sabe en que sala se encuentra?\n"
                + "1.-Si\n"
                + "2.-No\n");

        entrada = Integer.parseInt(lector.readLine());

        switch (entrada) {
            case 1:
                System.out.println("Por favor ingresar en la sala que se encuentra:");
                numeroSala = Integer.parseInt(lector.readLine());
                break;
            case 2:
                do {
                    System.out.println("Seleccione en que sala esta posicionado"); //necesito re-formular la pregunta
                    for (int i = 0; i < hospital.entregarSizeSala(posPiso); i++) {
                        System.out.print(i + " - ");
                    }
                    if (Integer.parseInt(lector.readLine()) < hospital.entregarSizePiso()) {
                        numeroSala = Integer.parseInt(lector.readLine());
                        flag = true;
                    } else {
                        System.out.println("Ingrese un numero valido");
                    }
                } while (flag != true);
                break;
        }

        return numeroSala;
    }

    public void funcionamientoPisos() throws IOException {

        posPiso = posicionPiso();
        int entrada;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("Que desea realizar:\n"
                    + "1.- Mostrar pisos\n"
                    + "2.- Mostrar todos los pisos\n"
                    + "3.- Agregar piso\n"
                    + "4.- Modificar piso\n"
                    + "5.- Eliminar piso\n"
                    + "0.- Salir\n");
            entrada = Integer.parseInt(lector.readLine());

            switch (entrada) {
                case 1:
                    hospital.mostrarPiso(posPiso);
                    break;
                case 2:
                    hospital.mostrarPiso();
                    break;
                case 3:
                    hospital.addPiso();
                    break;
                case 4:
                    hospital.corregirPiso();
                    break;
                case 5:
                    hospital.removePiso();
                    break;
                case 0:
                    System.out.println("saliendo....");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }

        } while (entrada != 0);

    }

    public void funcionamientoSalas() throws IOException {

        int entrada;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Usted se encuentra en el piso " + posPiso + ", ¿Desea cambiar de piso?\n"
                + "1.-Si\n"
                + "2.-No\n");
        entrada = Integer.parseInt(lector.readLine());

        switch (entrada) {
            case 1:
                posPiso = posicionPiso();
                break;
            case 2:
                System.out.println("Entendible");
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }

        do {
            System.out.println("Que desea realizar:\n"
                    + "1.- Mostrar Sala \n"
                    + "2.- Mostrar Todas las Salas\n"
                    + "3.- Agregar Sala\n"
                    + "4.- Modificar Sala\n"
                    + "5.- Eliminar Sala\n"
                    + "0.- Salir\n");
            entrada = Integer.parseInt(lector.readLine());

            switch (entrada) {
                case 1:
                    hospital.mostrarSala(posPiso, posSala);
                    System.out.println("");
                    break;
                case 2:
                    hospital.mostrarSala(posPiso);
                    break;
                case 3:
                    hospital.addSala(posPiso);
                    break;
                case 4:
                    hospital.corregirSala(posPiso, posSala);
                    break;
                case 5:
                    hospital.removeSala(posPiso);
                    break;
                case 0:
                    System.out.println("saliendo....");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }

        } while (entrada != 0);

    }

    public void funcionamientoCamas() throws IOException {

        int entrada;
        posSala = posicionSala();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("Que desea realizar:\n"
                    + "1.- Mostrar Todas las Camas \n"
                    + "2.- Agregar Cama\n"
                    + "3.- Modificar Cama\n"
                    + "4.- Eliminar Cama\n"
                    + "0.- Salir\n");

            entrada = Integer.parseInt(lector.readLine());

            switch (entrada) {
                case 1:
                    hospital.mostrarCamas(posPiso, posSala);
                    break;
                case 2:
                    int cantidadCamas;
                    System.out.println("Ingrese la cantidad de camas que desea agregar:");
                    cantidadCamas = Integer.parseInt(lector.readLine());
                    hospital.addCama(posPiso, posSala, cantidadCamas);
                    break;
                case 3:
                    hospital.corregirCama(posPiso, posSala);
                    break;
                case 4:
                    hospital.removeCama(posPiso, posSala);
                    break;
                case 0:
                    System.out.println("saliendo....");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }

        } while (entrada != 0);

    }

    public void funcionamientoPacientes() throws IOException {
       /*
            Dividir esto en 2 secciones 
            que son los pacientes con camas, y los pacientes en cola
        */
         int entrada;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("Que desea realizar:\n"
                    + "1.- Mostrar en las camas\n"
                    + "2.- Mostrar Pacientes en cola"
                    + "2.- Ficha del Paciente\n"
                    + "3.- Agregar paciente\n"
                    + "4.- Modificar paciente\n"
                    + "5.- Eliminar paciente\n"
                    + "6.- Filtrar pacientes por Diagnostico\n"
                    + "7.- Urgencias del hospital\n"
                    + "0.- Salir\n");
            entrada = Integer.parseInt(lector.readLine());

            switch (entrada) {
                case 1:
                    hospital.mostrarPacientesConCamasSala(posPiso, posSala);
                    break;
                case 2:
                    break;
                case 3:
                    pantallaGafete();
                    break;
                case 4:
                    hospital.agregarPaciente();
                    break;
                case 5:
                    hospital.modificarPaciente();
                    break;
                case 6:
                    hospital.eliminarPaciente();
                    break;
                case 7:
                    this.filtrarGravedad();
                    break;
                case 8:
                    this.urgencia();
                    break;
                case 0:
                    System.out.println("saliendo....");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }

        } while (entrada != 0);
       
       
    }

    public int pantallaGafete() throws IOException {

        int posCama = 0;
        int valorPiso = 0, valorSala, valorCama;
        String rut;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el rut del paciente = (formato 12.345.789-8)");
        rut = lector.readLine();

        while (valorPiso != hospital.sizePiso()) {
            valorSala = 0;
            while (valorSala != hospital.sizeSala(posPiso)) {
                valorCama = 0;
                while (valorCama != hospital.sizeCama(valorPiso, valorSala)) {
                    if (rut.equals(hospital.rutPaciente(valorCama, valorSala, valorPiso))) {
                        hospital.mostrarGafete(valorCama, valorSala, valorPiso);
                    }
                    valorCama++;
                }
                valorSala++;
            }
            valorPiso++;
        }

        return posCama;
    }
   
    public void filtrarGravedad() throws IOException {
        int filtroPac;
        boolean flag = false;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese el estado de gravedad a filtrar:");
        System.out.println("(Los estados de gravedad son del 1 (siendo el más bajo) hasta el 5 (siendo el mas alto))");
        filtroPac = Integer.parseInt(lector.readLine());

        filtro(filtroPac);

    }

    public void filtro(int gravedad) {

        for (int j = 0; j < hospital.sizePacientes(); j++) {
            if (hospital.diagnosticoPaciente(j).equals(gravedad+"")) {
                hospital.mostrarGafete(j);
                System.out.println("-------------------------");
            }
        }

    }
    
     public void urgencia() {
        int valorPac = 0;
        ArrayList<Paciente> pacientesConUrgencia = new ArrayList<>();
        while (valorPac != hospital.sizePacientes()) {
            if(hospital.diagnosticoPaciente(valorPac).equals("5")){
                pacientesConUrgencia.add(hospital.retornoPaciente(valorPac));
            }else{
                if(hospital.diagnosticoPaciente(valorPac).equals("4")){
                    pacientesConUrgencia.add(hospital.retornoPaciente(valorPac));
                }
            }
        }
        
         for (int i = 0; i < pacientesConUrgencia.size(); i++) {
             pacientesConUrgencia.get(i).gafete();
             System.out.println("-------------------------------");
         }
    }

    
    
    
    public void funcionamientoFuncionarios() {

        /*
            Si no fuera por que las personas necesitan 
            una wea pa guiarse, no existiria esta mierda
        */
        
    }
}
