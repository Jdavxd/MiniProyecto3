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
    void actualizarEstudiante(Estudiante estudiante);
    void eliminarEstudiante(String numeroIdentificacion);
    List<Estudiante> obtenerTodosEstudiantes();
    List<Estudiante> listaEstudiantes = new ArrayList<>();
}