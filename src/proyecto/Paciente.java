/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author cagaj
 */
public class Paciente extends Persona {

    private String diagnostico;
    private int diaIngreso;
    private int mesIngreso;
    private int anioIngreso;
    private int diaSalida;
    private int mesSalida;
    private int anioSalida;

    public Paciente(String nombres, String apellidos, String rut, int edad, String diagnostico, int diaIngreso, int mesIngreso, int añoIngreso) {
        super(nombres, apellidos, rut, edad);
        this.diagnostico = diagnostico;
        this.diaIngreso = diaIngreso;
        this.mesIngreso = mesIngreso;
        this.anioIngreso = añoIngreso;
    }

    public Paciente(String nombres, String apellidos, String rut, short edad, String diagnostico) {
        super(nombres, apellidos, rut, edad);
        this.diagnostico = diagnostico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String fechaIngreso() {
        return diaIngreso + " " + mesIngreso + " " + anioIngreso;
    }

    public void setFechaIngreso(int diaIngreso, int mesIngreso, int anioIngreso) {
        this.diaIngreso = diaIngreso;
        this.mesIngreso = mesIngreso;
        this.anioIngreso = anioIngreso;
    }

    public String fechaAlta() {
        return diaSalida + " " + mesSalida + " " + anioSalida + " ";
    }

    public void setFechaSalida(int diaSalida, int mesSalida, int anioSalida) {
        this.diaSalida = diaSalida;
        this.mesSalida = mesSalida;
        this.anioSalida = anioSalida;
    }

    @Override
    public void gafete() {
        System.out.println("Nombre: "+super.getNombres()+" "+super.getApellidos()+"\n"
                + "Rut: "+super.getRut()+"\n"
                + "Edad: "+super.getEdad()+"\n"
                + "Diagnostico: "+this.diagnostico+"\n"
                + "Fecha Ingreso: "+this.fechaIngreso());
    }

    
    
    
}
