package colecciones;

import modelo.ContactoModelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContactoImplementacionDAO implements ContactoDAO {
    private List<ContactoModelo> estudiantes = new ArrayList<>();
    private List<ContactoModelo> profesores = new ArrayList<>();
    private List<ContactoModelo> empleados = new ArrayList<>();

    private Map<String, List<ContactoModelo>> contactosPorTipo = new HashMap<>();

    @Override
    public void agregarEstudiante(ContactoModelo estudiante) {
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

    public List<ContactoModelo> obtenerTodosProfesores() {
        return new ArrayList<>(profesores);
    }

    public List<ContactoModelo> obtenerTodosEmpleados() {
        return new ArrayList<>(empleados);
    }

    @Override
    public void actualizarEstudiante(ContactoModelo estudiante) {
    for (List<ContactoModelo> estudiantesPorTipo : contactosPorTipo.values()) {
        for (int i = 0; i < estudiantesPorTipo.size(); i++) {
            ContactoModelo e = estudiantesPorTipo.get(i);
            if (e.getNumeroIdentificacion().equals(estudiante.getNumeroIdentificacion())) {
                e.setNombres(estudiante.getNombres());
                e.setApellidos(estudiante.getApellidos());
                e.setFechaNacimiento(estudiante.getFechaNacimiento());
                e.setTipoContacto(estudiante.getTipoContacto());
                   
                break;
            }
        }
    }
}

    public void eliminarEstudiante(ContactoModelo estudiante) {
        Iterator<ContactoModelo> iterator = estudiantes.iterator();
        while (iterator.hasNext()) {
            ContactoModelo actual = iterator.next();
            if (actual.equals(estudiante)) {
                iterator.remove();
                break; // Terminamos el bucle ya que encontramos y eliminamos el estudiante
            }
        }
    }

    @Override
    public List<ContactoModelo> obtenerTodosEstudiantes() {
        List<ContactoModelo> todosEstudiantes = new ArrayList<>();
        for (List<ContactoModelo> estudiantes : contactosPorTipo.values()) {
            todosEstudiantes.addAll(estudiantes);
        }
        return todosEstudiantes;
    }

    @Override
    public void eliminarEstudiante(String numeroIdentificacion) {
        for (List<ContactoModelo> estudiantes : contactosPorTipo.values()) {
            estudiantes.removeIf(estudiante -> estudiante.getNumeroIdentificacion().equals(numeroIdentificacion));
        }
    }

    @Override
    public List<ContactoModelo> obtenerEstudiantesPorTipo(String tipoContacto) {
        return new ArrayList<>(contactosPorTipo.getOrDefault(tipoContacto, new ArrayList<>()));
    }

    // Nuevo método para obtener los tipos de contacto disponibles
   @Override
   public List<String> obtenerTiposDeContacto() {
    // Crear un conjunto para almacenar los tipos de contacto únicos
    Set<String> tiposDeContactoSet = new HashSet<>();

    // Iterar sobre todas las listas de contactos
    for (List<ContactoModelo> estudiantesPorTipo : contactosPorTipo.values()) {
        // Iterar sobre los contactos en cada lista
        for (ContactoModelo estudiante : estudiantesPorTipo) {
            // Agregar el tipo de contacto al conjunto
            tiposDeContactoSet.add(estudiante.getTipoContacto());
        }
    }

    // Convertir el conjunto a una lista y devolverla
    return new ArrayList<>(tiposDeContactoSet);
}
}