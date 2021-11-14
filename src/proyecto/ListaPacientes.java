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
public class ListaPacientes {

    private ArrayList<Paciente> pacientes;

    public ListaPacientes() {
        pacientes = new ArrayList<>();
        rellenoPaciente();
    }

    private void rellenoPaciente() {

        String nombre, apellido, rut, diagnostico;
        int edad, diaIngreso, mesIngreso, anioIngreso;

        File f = new File("datos_PACIENTE.txt");

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";

            while ((line = br.readLine()) != null) {
                Paciente nuevoPaciente;
                String[] splitedLine = line.split(";");
                apellido = splitedLine[0];
                nombre = splitedLine[1];
                rut = splitedLine[2];
                edad = Integer.parseInt(splitedLine[3]);
                diagnostico = splitedLine[4] + "";
                diaIngreso = Integer.parseInt(splitedLine[5]);
                mesIngreso = Integer.parseInt(splitedLine[6]);
                anioIngreso = Integer.parseInt(splitedLine[7]);

                nuevoPaciente = new Paciente(nombre, apellido, rut, edad, diagnostico, diaIngreso, mesIngreso, anioIngreso);
                pacientes.add(nuevoPaciente);

            }
            System.out.println("---------------");

        } catch (IOException e) {
            System.out.println(e.getMessage() + " no se que paso, que no lee");
        }

    }

    public void mostrarTotalPaciente() {
        System.out.println("En espera hay " + pacientes.size() + " pacientes");
    }

    public void agregarPaciente() throws IOException {
        String nombre, apellido, rut, diagnostico;
        short edad, diaIngreso, mesIngreso, anioIngreso;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Paciente nuevoPaciente;

        System.out.println("Ingrese su nombre:");
        nombre = lector.readLine();
        System.out.println("Ingrese su apellido");
        apellido = lector.readLine();
        System.out.println("Ingrese su rut:");
        rut = lector.readLine();
        System.out.println("Ingrese su edad:");
        edad = Short.parseShort(lector.readLine());
        System.out.println("Ingrese el dia:");
        diaIngreso = Short.parseShort(lector.readLine());
        System.out.println("Ingrese el mes: (indice numerico)");
        mesIngreso = Short.parseShort(lector.readLine());
        System.out.println("Ingrese el anio:");
        anioIngreso = Short.parseShort(lector.readLine());
        System.out.println("Diagnostico del paciente:");
        diagnostico = lector.readLine();

        nuevoPaciente = new Paciente(nombre, apellido, rut, edad, diagnostico, diaIngreso, mesIngreso, anioIngreso);
        pacientes.add(nuevoPaciente);

    }

    public void modificarPaciente() throws IOException {

        String rutPaciente;
        int posPaciente = 0, indicacion = 0;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = false;

        System.out.println("Ingrese el rut del paciente");
        rutPaciente = lector.readLine();
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getRut().equals(rutPaciente)) {
                posPaciente = i;
                flag = true;
            }
        }

        if (flag) {
            do {
                System.out.println("Que desea modificar:\n"
                        + "1.- Nombre\n"
                        + "2.- Apellido\n"
                        + "3.- Edad\n"
                        + "4.- Diagnostico\n"
                        + "0.- Salir\n");
                indicacion = Integer.parseInt(lector.readLine());

                switch (indicacion) {
                    case 1:
                        System.out.println("Ingrese el nombre:");
                        pacientes.get(posPaciente).setNombres(lector.readLine());
                        break;
                    case 2:
                        System.out.println("Ingrese el apellido:");
                        pacientes.get(posPaciente).setApellidos(lector.readLine());
                        break;
                    case 3:
                        System.out.println("Ingrese la edad:");
                        pacientes.get(posPaciente).setEdad(Short.parseShort(lector.readLine()));
                        break;
                    case 4:
                        System.out.println("Ingree el diagnostico:");
                        pacientes.get(posPaciente).setDiagnostico(lector.readLine());
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            } while (indicacion != 0);
        } else {
            System.out.println("No hay paciente con ese rut");
        }

    }

    public void eliminarPaciente() throws IOException {
        int posPaciente = 0;
        Boolean flag = false;
        String rutPaciente;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el rut del paciente:");
        rutPaciente = lector.readLine();

        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getRut().equals(rutPaciente)) {
                posPaciente = i;
                flag = true;
            }
        }

        if (flag) {
            pacientes.remove(posPaciente);
        } else {
            System.out.println("No hay un rut coincidente");
        }

    }
    
    public void agregarPaciente(Paciente nuevoPaciente){
        pacientes.add(nuevoPaciente);
    }
    
    public void mostrarGafete(){
        for (int i = 0; i < pacientes.size(); i++) {
            pacientes.get(i).gafete();
        }
    }
    
    public int sizeListaPacientes(){
        return pacientes.size();
    }
    
    public String diagnosticoPaciente(int posPaciente){
        return pacientes.get(posPaciente).getDiagnostico();
    }
    
    public Paciente getPaciente(int posPaciente){
        return pacientes.get(posPaciente);
    }
    
    public void mostrarGafete(int pos){
        pacientes.get(pos).gafete();
    }
    

}
