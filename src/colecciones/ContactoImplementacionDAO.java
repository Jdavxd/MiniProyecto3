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

    private Map<String, List<ContactoModelo>> contactosPorTipo = new HashMap<>();;

    @Override
    public void agregarContacto(ContactoModelo contacto) {
        estudiantes.add(contacto);

        // Verificar el tipo de contacto y agregar a la lista correspondiente
        String tipoContacto = contacto.getTipoContacto();
        switch (tipoContacto) {
            case "Estudiante":
                // Puedes hacer algo específico para estudiantes si lo necesitas
                break;
            case "Profesor":
                profesores.add(contacto);
                break;
            case "Empleado":
                empleados.add(contacto);
                break;
            // Puedes agregar más casos según sea necesario
        }

        // Agregar a la lista general por tipo de contacto
        contactosPorTipo.computeIfAbsent(tipoContacto, k -> new ArrayList<>()).add(contacto);
    }

    @Override
    public List<ContactoModelo> obtenerTodosProfesores() {
        return new ArrayList<>(profesores);
    }

    @Override
    public List<ContactoModelo> obtenerTodosEmpleados() {
        return new ArrayList<>(empleados);
    }

   
    @Override
public void actualizarContacto(ContactoModelo contacto) {
    String tipoContacto = contacto.getTipoContacto();
    List<ContactoModelo> listaEspecifica = contactosPorTipo.get(tipoContacto);

    if (listaEspecifica != null) {
        int index = -1;

        // Buscar el índice del contacto en la lista específica
        for (int i = 0; i < listaEspecifica.size(); i++) {
            ContactoModelo e = listaEspecifica.get(i);
            if (e.getNumeroIdentificacion().equals(contacto.getNumeroIdentificacion())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Actualizar el contacto en la lista específica
            listaEspecifica.set(index, contacto);
        }
    }
}

   
    @Override
    public void eliminarContacto(ContactoModelo contacto) {
    Iterator<ContactoModelo> iterator = estudiantes.iterator();
    while (iterator.hasNext()) {
        ContactoModelo actual = iterator.next();
        if (actual.equals(contacto)) {
            iterator.remove();
            eliminarDeListaEspecifica(contacto, contacto.getTipoContacto());
            break;
        }
    }
}
 
 
private void eliminarDeListaEspecifica(ContactoModelo contacto, String tipoContacto) {
    List<ContactoModelo> listaEspecifica = contactosPorTipo.get(tipoContacto);
    if (listaEspecifica != null) {
        listaEspecifica.remove(contacto);
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
    public void eliminarContacto(String numeroIdentificacion) {
        for (List<ContactoModelo> estudiantes : contactosPorTipo.values()) {
            estudiantes.removeIf(estudiante -> estudiante.getNumeroIdentificacion().equals(numeroIdentificacion));
        }
    }

    @Override
    public List<ContactoModelo> obtenerContactosPorTipo(String tipoContacto) {
    List<ContactoModelo> contactosPorTipoLista = contactosPorTipo.getOrDefault(tipoContacto, new ArrayList<>());
    return new ArrayList<>(contactosPorTipoLista);
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