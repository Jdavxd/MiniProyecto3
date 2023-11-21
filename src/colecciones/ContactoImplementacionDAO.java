    package colecciones;

    import modelo.ContactoModelo;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.HashSet;
    import java.util.Iterator;
    import java.util.List;
    import java.util.Map;
    import java.util.Set;
import modelo.Telefono;

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
    public void actualizarContacto(ContactoModelo contacto, String nuevoTipoContacto, String idVieja) {
        // Obtener el tipo de contacto anterior y la identificación del contacto
        String tipoContactoAnterior = contacto.getTipoContacto();
        String idContacto = contacto.getNumeroIdentificacion();
       // String idViejo = contacto.getNumeroIdentificacion();

        // Obtener la lista anterior y buscar el contacto
        List<ContactoModelo> listaEspecificaAnterior = contactosPorTipo.get(tipoContactoAnterior);
        if (listaEspecificaAnterior != null) {
            Iterator<ContactoModelo> iterator = listaEspecificaAnterior.iterator();
            while (iterator.hasNext()) {
                ContactoModelo c = iterator.next();
                if (c.getNumeroIdentificacion().equals(idContacto)) {
                    // Encontramos el contacto, eliminamos el antiguo y salimos del bucle
                    iterator.remove();
                    break;
                }
            }

            // Si la lista anterior queda vacía, también elimínala
            if (listaEspecificaAnterior.isEmpty()) {
                contactosPorTipo.remove(tipoContactoAnterior);
            }
        }

        // Actualizar el tipo de contacto en el objeto
        contacto.setTipoContacto(nuevoTipoContacto);

        // Iterar sobre todas las listas y eliminar el contacto si está presente
        for (List<ContactoModelo> lista : contactosPorTipo.values()) {
            lista.removeIf(c -> c.getNumeroIdentificacion().equals(idContacto));
           
        }

        // Obtener o crear la lista específica para el nuevo tipo de contacto
        List<ContactoModelo> listaEspecificaNueva = contactosPorTipo.computeIfAbsent(nuevoTipoContacto, k -> new ArrayList<>());

        // Verificar si el contacto ya existe en la lista nueva
        boolean contactoExistente = listaEspecificaNueva.stream()
                .anyMatch(c -> c.getNumeroIdentificacion().equals(idContacto));

        // Agregar el contacto a la lista nueva solo si no existe ya
        if (!contactoExistente) {
            listaEspecificaNueva.add(contacto);
        }
    }

     //lista.removeIf(c -> !c.getNumeroIdentificacion().equals("0"));
      //      System.out.println("ID VIEJO"+idVieja);
      //      System.out.println("ID NUEVO"+idContacto);


    public void eliminarContacto(ContactoModelo contacto) {
        // Iterar sobre todas las listas y eliminar el contacto si está presente
        for (List<ContactoModelo> lista : contactosPorTipo.values()) {
            lista.remove(contacto);
        }
    }




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
            // Implementación para obtener tipos de contacto disponibles
            List<String> tiposDeContacto = new ArrayList<>();
            tiposDeContacto.add("Estudiante");
            tiposDeContacto.add("Profesor");
            tiposDeContacto.add("Empleado");
            return tiposDeContacto;
        }
        
        
 
        @Override
    public void agregarTelefono(String numeroIdentificacion, Telefono telefono) {
        // Obtener la lista específica para el tipo de contacto actual del número de identificación
        List<ContactoModelo> listaEspecifica = contactosPorTipo.get(obtenerTipoContactoPorIdentificacion(numeroIdentificacion));

        // Buscar el contacto por número de identificación
        if (listaEspecifica != null) {
            for (ContactoModelo contacto : listaEspecifica) {
                if (contacto.getNumeroIdentificacion().equals(numeroIdentificacion)) {
                    // Agregar el teléfono al contacto
                    contacto.agregarTelefono(telefono);
                    break;
                }
            }
        }
    }

private String obtenerTipoContactoPorIdentificacion(String numeroIdentificacion) {
    for (List<ContactoModelo> lista : contactosPorTipo.values()) {
        for (ContactoModelo contacto : lista) {
            if (contacto.getNumeroIdentificacion().equals(numeroIdentificacion)) {
                return contacto.getTipoContacto();
            }
        }
    }
    return null; // Si no se encuentra el contacto
}

  

    
 


    }