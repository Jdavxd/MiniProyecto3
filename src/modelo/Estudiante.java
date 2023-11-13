/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author julia
 */
import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private List<String> direcciones;
    private List<Telefono> telefonos;

    public Estudiante() {
        this.direcciones = new ArrayList<>();
        this.telefonos = new ArrayList<>();
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<String> getDirecciones() {
        return direcciones;
    }

    public void agregarDireccion(String direccion) {
        this.direcciones.add(direccion);
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void agregarTelefono(Telefono telefono) {
        this.telefonos.add(telefono);
    }
    
    @Override
       public String toString() {
        return 
                numeroIdentificacion + '\'' +
                 nombres + '\'' +
                 apellidos + '\'' +
                 fechaNacimiento + '\'';
    }


} 