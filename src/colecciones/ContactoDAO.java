/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colecciones;
import java.util.ArrayList;
import modelo.ContactoModelo;
/**
 *
 * @author julia
 */
import java.util.List;

public interface ContactoDAO {
    void agregarEstudiante(ContactoModelo estudiante);
    List<ContactoModelo> obtenerTodosProfesores();
    List<ContactoModelo> obtenerTodosEmpleados();
    List<ContactoModelo> obtenerTodosEstudiantes();
    void actualizarEstudiante(ContactoModelo estudiante);
    void eliminarEstudiante(ContactoModelo estudiante);
    void eliminarEstudiante(String numeroIdentificacion);
    List<ContactoModelo> obtenerEstudiantesPorTipo(String tipoContacto);
    // Nuevo método para obtener los tipos de contacto disponibles
    List<String> obtenerTiposDeContacto();
}