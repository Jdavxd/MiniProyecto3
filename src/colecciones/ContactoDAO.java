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
