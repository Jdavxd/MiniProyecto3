/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colecciones;
import modelo.Estudiante;
/**
 *
 * @author julia
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EstudianteImplementacionDAO implements EstudianteDAO {
    private List<Estudiante> estudiantes = new ArrayList<>();

    @Override
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

      @Override
    public void actualizarEstudiante(Estudiante estudiante) {
        for (Estudiante e : estudiantes) {
            if (e.getNumeroIdentificacion().equals(estudiante.getNumeroIdentificacion())) {
                // Actualizar los atributos del estudiante encontrado
                e.setNombres(estudiante.getNombres());
                e.setApellidos(estudiante.getApellidos());
                e.setFechaNacimiento(estudiante.getFechaNacimiento());
                // Puedes seguir actualizando otros atributos según sea necesario
                break; // Terminamos el bucle ya que encontramos y actualizamos el estudiante
            }
        }
    }

       public void eliminarEstudiante(Estudiante estudiante) {
        Iterator<Estudiante> iterator = listaEstudiantes.iterator();
        while (iterator.hasNext()) {
            Estudiante actual = iterator.next();
            if (actual.equals(estudiante)) {
                iterator.remove();
                break; // Terminamos el bucle ya que encontramos y eliminamos el estudiante
            }
        }
    }

    @Override
    public List<Estudiante> obtenerTodosEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    @Override
    public void eliminarEstudiante(String numeroIdentificacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}