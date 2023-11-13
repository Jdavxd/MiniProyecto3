package colecciones;

import modelo.Estudiante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EstudianteImplementacionDAO implements EstudianteDAO {
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Estudiante> profesores = new ArrayList<>();
    private List<Estudiante> empleados = new ArrayList<>();

    private Map<String, List<Estudiante>> contactosPorTipo = new HashMap<>();

    @Override
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);

        // Verificar el tipo de contacto y agregar a la lista correspondiente
        String tipoContacto = estudiante.getTipoContacto();
        switch (tipoContacto) {
            case "Estudiante":
                // Puedes hacer algo específico para estudiantes si lo necesitas
                break;
            case "Profesor":
                profesores.add(estudiante);
                break;
            case "Empleado":
                empleados.add(estudiante);
                break;
            // Puedes agregar más casos según sea necesario
        }

        // Agregar a la lista general por tipo de contacto
        contactosPorTipo.computeIfAbsent(tipoContacto, k -> new ArrayList<>()).add(estudiante);
    }

    public List<Estudiante> obtenerTodosProfesores() {
        return new ArrayList<>(profesores);
    }

    public List<Estudiante> obtenerTodosEmpleados() {
        return new ArrayList<>(empleados);
    }

    @Override
    public void actualizarEstudiante(Estudiante estudiante) {
        for (List<Estudiante> estudiantesPorTipo : contactosPorTipo.values()) {
            for (int i = 0; i < estudiantesPorTipo.size(); i++) {
                Estudiante e = estudiantesPorTipo.get(i);
                if (e.getNumeroIdentificacion().equals(estudiante.getNumeroIdentificacion())) {
                    e.setNombres(estudiante.getNombres());
                    e.setApellidos(estudiante.getApellidos());
                    e.setFechaNacimiento(estudiante.getFechaNacimiento());
                    e.setTipoContacto(estudiante.getTipoContacto());
                    estudiantesPorTipo.set(i, e);
                    break;
                }
            }
        }
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        Iterator<Estudiante> iterator = estudiantes.iterator();
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
        List<Estudiante> todosEstudiantes = new ArrayList<>();
        for (List<Estudiante> estudiantes : contactosPorTipo.values()) {
            todosEstudiantes.addAll(estudiantes);
        }
        return todosEstudiantes;
    }

    @Override
    public void eliminarEstudiante(String numeroIdentificacion) {
        for (List<Estudiante> estudiantes : contactosPorTipo.values()) {
            estudiantes.removeIf(estudiante -> estudiante.getNumeroIdentificacion().equals(numeroIdentificacion));
        }
    }

    @Override
    public List<Estudiante> obtenerEstudiantesPorTipo(String tipoContacto) {
        return new ArrayList<>(contactosPorTipo.getOrDefault(tipoContacto, new ArrayList<>()));
    }

    // Nuevo método para obtener los tipos de contacto disponibles
   @Override
    public List<String> obtenerTiposDeContacto() {
        return new ArrayList<>(contactosPorTipo.keySet());
    }
}