    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package colecciones;
/**
 * PROYECTO #3 GRUPO 80
 * @author Julian David Rengifo - 202241016
 * @author Genaro Vegas - 202241850 
 * @Profesor Luis Johany Romo Portilla 
 */
    import modelo.ContactoModelo;
    import java.util.List;
    import modelo.Telefono;

    public interface ContactoDAO {
        void agregarContacto(ContactoModelo contacto);
        void actualizarContacto(ContactoModelo contacto, String nuevoTipoContacto, String idVieja);
        void eliminarContacto(String numeroIdentificacion);
        void agregarTelefono(String numeroIdentificacion, Telefono telefono);
        List<ContactoModelo> obtenerTodosProfesores();
        List<ContactoModelo> obtenerTodosEmpleados();
        List<ContactoModelo> obtenerTodosEstudiantes();
        //void eliminarContacto(ContactoModelo contacto);
        List<ContactoModelo> obtenerContactosPorTipo(String tipoContacto);
        // Nuevo método para obtener los tipos de contacto disponibles
        List<String> obtenerTiposDeContacto();


    }
