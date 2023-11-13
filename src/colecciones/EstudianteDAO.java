/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colecciones;
import java.util.ArrayList;
import modelo.Estudiante;
/**
 *
 * @author julia
 */
import java.util.List;

public interface EstudianteDAO {
    void agregarEstudiante(Estudiante estudiante);
    List<Estudiante> obtenerTodosProfesores();
    List<Estudiante> obtenerTodosEmpleados();
    void actualizarEstudiante(Estudiante estudiante);
    void eliminarEstudiante(Estudiante estudiante);
    List<Estudiante> obtenerTodosEstudiantes();
    void eliminarEstudiante(String numeroIdentificacion);
    List<Estudiante> obtenerEstudiantesPorTipo(String tipoContacto);
    // Nuevo método para obtener los tipos de contacto disponibles
    List<String> obtenerTiposDeContacto();
}