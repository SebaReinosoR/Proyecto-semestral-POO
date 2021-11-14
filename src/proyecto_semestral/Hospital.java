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
public class Hospital {

    private String nombreHospital;
    private String direccionHospital;
    private int idHospital;
    private int cantidadPisos;
    private int cantidadSalas;
    private int cantidadCamas;
    private Funcionario[] funcionarios; //listas
    private ArrayList<Paciente> pacientes; //lista
    private HashMap<Integer, Piso> pisos;

    public Hospital(String nombreHospital, String direccionHospital, int idHospital, int cantidadPisos, int cantidadSalas, int cantidadCamas) {
        this.nombreHospital = nombreHospital;
        this.direccionHospital = direccionHospital;
        this.idHospital = idHospital;
        this.cantidadPisos = cantidadPisos;
        this.cantidadSalas = cantidadSalas;
        this.cantidadCamas = cantidadCamas;
        pisos = new HashMap<>();
        llenarPisos(cantidadPisos, cantidadSalas, cantidadCamas);
        pacientes = new ArrayList<>();
        funcionarios = new Funcionario[20];
        rellenoFuncionarios();
        rellenoPaciente();
    }

    public void llenarPisos(int cantidadPisos, int cantidadSalas, int cantidadCamas) {

        for (int i = 0; i < cantidadPisos; i++) {
            Piso pisoAgregado = new Piso(i + 1, cantidadSalas, cantidadCamas);
            pisos.put(i + 1, pisoAgregado);
        }

    }

    /*
        --------------------------------------------------------
        Datos de los pacientes y funcionarios
    */
   
    private void rellenoFuncionarios(){
        
        String nombre,apellido,rut,especialidad;
        int edad;
        
        File f = new File("datos.CSV");
        
        try{
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            int i = 0;
            
            while((line = br.readLine()) != null){
                
                Funcionario nuevoFuncionario;
                String[] splitedLine = line.split(";");
                apellido = splitedLine[0];
                nombre = splitedLine[1];
                rut = splitedLine[2];
                edad = Integer.parseInt(splitedLine[3]);
                especialidad = splitedLine[4]+"";
                
                
                nuevoFuncionario = new Funcionario(especialidad,nombre,apellido,rut,edad);
                funcionarios[i] = nuevoFuncionario;
                i++;
                
            }
            System.out.println("---------------");
            
        }catch(IOException e){
            System.out.println(e.getMessage()+" no se que paso, que no lee");
        }
        
    }
    
    private void rellenoPaciente(){
        
        String nombre,apellido,rut,diagnostico;
        int edad,diaIngreso,mesIngreso,anioIngreso;
        
        File f = new File("datos_PACIENTE.txt");
        
        try{
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            
            
            while((line = br.readLine()) != null){
                Paciente nuevoPaciente;
                String[] splitedLine = line.split(";");
                apellido = splitedLine[0];
                nombre = splitedLine[1];
                rut = splitedLine[2];
                edad = Integer.parseInt(splitedLine[3]);
                diagnostico = splitedLine[4]+"";
                diaIngreso = Integer.parseInt(splitedLine[5]);
                mesIngreso = Integer.parseInt(splitedLine[6]);
                anioIngreso =  Integer.parseInt(splitedLine[7]);
                
                nuevoPaciente = new Paciente(nombre,apellido,rut,edad,diagnostico,diaIngreso,mesIngreso,anioIngreso);
                pacientes.add(nuevoPaciente);
                
                
            }
            System.out.println("---------------");
            
        }catch(IOException e){
            System.out.println(e.getMessage()+" no se que paso, que no lee");
        }
        
        
        
    }
    
    
    /*
        Fin del espacio
        -------------------------------------------------------------
    */
    
    /*
        Manejo de los pisos
     */
    public void mostrarPisos(int posPiso) {
        System.out.println("Usted esta en el piso "+posPiso);
    }

    public void mostrarPisos(){
        for (Integer i : pisos.keySet()) {
            System.out.println("Esta en el piso " + i);
        } 
    }
    
    public void agregarPiso() throws IOException {

        int numeroPiso, cantidadSalas;
        Piso nuevoPiso;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingrese el numero del piso:");
        numeroPiso = Integer.parseInt(lector.readLine());

        System.out.println("Ingrese la cantidad de salas:");
        cantidadSalas = Integer.parseInt(lector.toString());

        nuevoPiso = new Piso(numeroPiso, cantidadSalas, cantidadCamas);

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
                pisos.get(keyPisoModificar).setCantidadSalas(salas);
                System.out.println("Cambio exitoso.");
            } else {
                if (decision.equals("no")) {
                    System.out.println("Terminando accion");
                }
            }

        }
    }

    public void eliminarPiso() {

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int keyPisoEliminar = 0;

        if (pisos.containsKey(keyPisoEliminar)) {
            System.out.println("El piso " + keyPisoEliminar + " sera eliminado");
            pisos.remove(keyPisoEliminar);
        } else {
            System.out.println("El piso solicitado no se encuentra/no existe");
        }

    }

    public int sizePisos() {
        return this.cantidadPisos;
    }

    /*
        Solicitudes de la salas
     */
    public void mostrarSala(int numeroPiso) {
        pisos.get(numeroPiso).mostrarSalas();
    }

    public void agregarSala(int numeroPiso) throws IOException {
        pisos.get(numeroPiso).ingresarSala();
    }

    public void modificarSala(int numeroPiso) throws IOException {
        pisos.get(numeroPiso).modificarSalas();
    }

    public void eliminarSala(int numeroPiso) throws IOException {
        pisos.get(numeroPiso).eliminarSala();
    }

    public int tamanioSala(int numeroPiso) {
        return pisos.get(numeroPiso).sizeSalas();
    }

    public void mostrarSala(int numeroPiso, int posSala) {
        pisos.get(numeroPiso).mostrarSalas(posSala);
    }

    /*
        Solicitudes de las camas
     */
    public void mostrarCama(int numeroPiso, int numeroSala) {
        pisos.get(numeroPiso).mostrarCamas(numeroSala);
    }

    public void agregarCama(int numeroPiso, int numeroSala, int cantidadCamas) {
        pisos.get(numeroPiso).agregarCamas(numeroSala, cantidadCamas);
    }

    public void modificarCama(int numeroPiso, int numeroSala) throws IOException {
        pisos.get(numeroPiso).modificarCamas(numeroSala);
    }

    public void eliminarCama(int numeroPiso, int numeroSala) throws IOException {
        pisos.get(numeroPiso).eliminarCama(numeroSala);
    }

    public int mostrarCamasSala(int numeroPiso, int numeroSala) {
        int cantidad;
        cantidad = pisos.get(numeroPiso).sizeSalas() * pisos.get(numeroPiso).sizeCamas(numeroSala);
        return cantidad;
    }

    public int mostrarCamasPiso(int numeroPiso) {
        int cantCamas, cantCamasPisos;
        cantCamas = mostrarCamasSala(numeroPiso, pisos.get(numeroPiso).sizeSalas());
        cantCamasPisos = pisos.size() * cantCamas;
        return cantCamasPisos;
    }
    
    

    /*
        Funciones de los pacientes
     */
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
    
    public String diagnosticoPaciente(int posPiso,int posSala,int posCama){
        return pisos.get(posPiso).getDiagnostico(posSala,posCama);
    }
    
    public Paciente pacienteSolicitado(int posPiso,int posSala,int posCama){
        return pisos.get(posPiso).getPaciente(posSala, posCama);
    }
    /*
        Funciones de los Funcionario
     */
    public void mostrarFuncionario() {

        System.out.println("Especialidad" + " " + "Nombres" + " " + "Apellidos" + " " + "Rut" + " " + "Edad"); // idea encabezado
        for (int i = 0; i < funcionarios.length; i++) {
            System.out.println(funcionarios[i].getEspecialidad() + "" + funcionarios[i].getNombres() + "" + funcionarios[i].getApellidos() + "" + funcionarios[i].getRut() + "" + funcionarios[i].getEdad());
        }

    }

    public void agregarFuncionario() throws IOException {

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String nombres, apellidos, rut, especialidad;
        short edad, i;
        Funcionario nuevoFuncionario;

        System.out.println("Ingrese nombres del funcionario");
        nombres = lector.readLine();
        System.out.println("Ingrese apellidos del funcionario");
        apellidos = lector.readLine();
        System.out.println("Ingrese rut del funcionario");
        rut = lector.readLine();
        System.out.println("Ingrese especialidad del funcionario");
        especialidad = lector.readLine();
        System.out.println("Ingrese edad del funcionario");
        edad = Short.parseShort(lector.readLine());

        nuevoFuncionario = new Funcionario(especialidad, nombres, apellidos, rut, edad); //crea el objeto

        for (int j = 0; j < funcionarios.length && funcionarios[j] != null; j++) {
            if (funcionarios[j] == nuevoFuncionario) {
                System.out.println("Funcionario ya existe ");
                break;
            } else {
                funcionarios[j] = nuevoFuncionario;
                System.out.println("Funcionario agregado con exito");
            }

        }

    }

    public void modificarFuncionario() throws IOException 
    { // EL UNICO PROBLEMA ES QUE SI QUIERE EDITAR EL RUT ES IMPOSIBLE
        
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String rut, cambio;
        int condicional, repet;

        System.out.println("Ingrese Rut del funcionario");
        rut = lector.readLine();

        for (int i = 0; i < funcionarios.length && funcionarios[i] != null; i++) { //verificar si existe en la lista            

            if (funcionarios[i].getRut().equals(rut)) {
                do {
                    System.out.println("Funcionario encontrado "
                            + "\n Que desea modificar"
                            + "\n 1-Nombre"
                            + "\n 2-Apellido"
                            + "\n 3-Rut"
                            + "\n 4-Edad"
                            + "\n 5-Especialidad"
                            + "\n 0-Salir");
                    condicional = Integer.parseInt(lector.toString());

                    switch (condicional) {

                        case 1:
                            System.out.println("Ingrese nuevos nombres");
                            cambio = lector.readLine();
                            funcionarios[i].setNombres(cambio);
                            if (funcionarios[i].getNombres().equals(cambio)) {
                                System.out.println("El nombre fue cambiado con exito");
                            } else {
                                System.out.println("El cambio del nombre fallo");
                            }
                            break;
                        case 2:
                            System.out.println("Ingrese nuevos apellidos");
                            cambio = lector.readLine();
                            funcionarios[i].setApellidos(cambio);
                            if (funcionarios[i].getApellidos().equals(cambio)) {
                                System.out.println("El apellido fue cambiado con exito");
                            } else {
                                System.out.println("El cambio del apellido fallo");
                            }
                            break;
                        case 3:
                            System.out.println("Ingrese nuevo rut");
                            cambio = lector.readLine();
                            funcionarios[i].setRut(cambio);
                            if (funcionarios[i].getRut().equals(cambio)) {
                                System.out.println("El rut fue cambiado con exito");
                            } else {
                                System.out.println("El cambio del rut fallo");
                            }
                            break;
                        case 4:
                            System.out.println("Ingrese nueva Edad");
                            cambio = lector.readLine();
                            funcionarios[i].setEdad(Short.parseShort(cambio));
                            if (funcionarios[i].getEdad() == Short.parseShort(cambio)) {
                                System.out.println("La edad fue cambiada con exito");
                            } else {
                                System.out.println("El cambio de la edad fallo");
                            }
                            break;
                        case 5:
                            System.out.println("Ingrese nueva especialidad");
                            cambio = lector.readLine();
                            funcionarios[i].setApellidos(cambio);
                            if (funcionarios[i].getApellidos().equals(cambio)) {
                                System.out.println("La especialidad fue cambiada con exito");
                            } else {
                                System.out.println("El cambio de la especialidad fallo");
                            }
                            break;
                        case 0:
                            System.out.println("Esta saliendo...");
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida.");
                    }
                    System.out.println("Desea realizar otro cambio?" + "\n1-SI" + "\n2-NO");
                    repet = Integer.parseInt(lector.readLine());
                } while (repet != 2);
                break;
            }

        }
        System.out.println("El funcionario no existe en el hospital");

    }

    public void eliminarFuncionario() throws IOException {
        
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String rut;
        System.out.println("Ingrese Rut del funcionario");
        rut = lector.readLine();
        
        for (int j = 0; j < funcionarios.length && funcionarios[j] != null; j++) {
            if (funcionarios[j].getRut().equals(rut)) {
                funcionarios[j] = null;
                System.out.println("El funcionario fue eliminado con exito");
            }
        }
   
    }

    /*
        Setters and getters del Hospital 
        (y una funcion Override que es la de toString)
    
     */
    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public String getDireccionHospital() {
        return direccionHospital;
    }

    public void setDireccionHospital(String direccionHospital) {
        this.direccionHospital = direccionHospital;
    }

    public int getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    @Override
    public String toString() {
        return "Hospital{" + "nombreHospital=" + nombreHospital + ", direccionHospital=" + direccionHospital + ", idHospital=" + idHospital + '}';
    }


    /*
        ESTO ES SOLO DE PRUEBA
        YA QUE EN EL INTENTO DE ASIGNAR VALORES
        COLAPSABA
        Y TUVIMOS QUE PREGUNTAR
        1 POR 1
        QUE ES LO QUE PODIA PASAR
        TRATANDO DE VER NIVEL POR NIVEL
     */
}
