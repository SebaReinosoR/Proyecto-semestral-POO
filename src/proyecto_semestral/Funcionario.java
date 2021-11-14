/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_semestral;

/**
 *
 * @author cagaj
 */
public class Funcionario extends Persona{
    
    private String especialidad;
    /*
    public Funcionario(String especialidad,Persona persona) {
        
        this.especialidad = especialidad;
    }
    **/
    
    public Funcionario(String especialidad, String nombres, String apellidos, String rut, int edad) {
        super(nombres, apellidos, rut, edad);
        this.especialidad = especialidad;
    }

    
    
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
}
