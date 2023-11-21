/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import vista.InterfazContacto;
import modelo.ContactoModelo;
/**
 * @author Julian Rengifo 2241016
 * @author Genaro Vegas 2241850
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import vista.ListaContactos;
import colecciones.ContactoDAO;
import java.util.ArrayList;
import modelo.Telefono;

public class ContactoController {
    private InterfazContacto vista;
    private ContactoDAO ContactoDAO;
     private ListaContactos listaContactos;
    private ContactoModelo contactoActual = new ContactoModelo("", "", "", "", new ArrayList<>(), "");

    public ContactoController(InterfazContacto vista, ContactoDAO ContactoDAO, ListaContactos listaContactos ) {
        this.vista = vista;
        this.ContactoDAO = ContactoDAO;
        this.listaContactos = listaContactos;

        // Configurar el manejador de eventos para el botón Agregar
        this.vista.setAgregarEstudianteListener(new AgregarEstudianteListener());
        
    }
    
    
    public void setListaContactos(ListaContactos listaContactos) {
    this.listaContactos = listaContactos;
}

public void agregarContacto() {
    // Obtener información de la vista
    String identificacion = vista.getIdentificacion();
    String nombres = vista.getNombres();
    String apellidos = vista.getApellidos();
    String fechaNacimiento = vista.getFechaNacimiento();
    String tipoContacto = vista.getTipoContacto();
    List<String> direcciones = vista.obtenerDireccionesDesdeVista(); // Obtener direcciones desde la vista
    
    // Crear una instancia de ContactoModelo con la información
    ContactoModelo nuevoContacto = new ContactoModelo(identificacion, nombres, apellidos, fechaNacimiento, direcciones, tipoContacto);
    nuevoContacto.setNumeroIdentificacion(identificacion);
    nuevoContacto.setNombres(nombres);
    nuevoContacto.setApellidos(apellidos);
    nuevoContacto.setFechaNacimiento(fechaNacimiento);
    //nuevoContacto.setDirecciones(direcciones);
    nuevoContacto.setTipoContacto(tipoContacto);   
    

    // Configurar el contacto actual
    contactoActual = nuevoContacto;

    // Agregar el contacto utilizando el DAO
    ContactoDAO.agregarContacto(nuevoContacto);
   // nuevoContacto.setDirecciones (null);
    vista.limpiarDireccionesTemporales();
    // Limpiar los campos de la vista
    vista.limpiarCampos();
    // Mostrar mensaje de éxito en un cuadro de diálogo
    JOptionPane.showMessageDialog(vista, "Contacto agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

    // Actualizar la lista de contactos
    if (listaContactos != null) {
        listaContactos.setVisible(false);
    }
    
    SwingUtilities.invokeLater(() -> {
        listaContactos = new ListaContactos(ContactoDAO, vista);
        setListaContactos(listaContactos);
        listaContactos.actualizarTabla();
    });
}


  

public void agregarDireccion() {
    // Verifica si hay un contacto actual
    if (contactoActual != null) {
        // Obtiene la dirección desde la vista
        String direccion = vista.getDireccion();


        // Verifica que la dirección no esté vacía antes de agregarla
        if (!direccion.isEmpty()) {
            // Agrega la dirección al contacto actual
            contactoActual.agregarDireccion(direccion);

            // Limpia la caja de texto de dirección en la vista
            vista.limpiarDireccion();

            // Imprime un mensaje de prueba
            System.out.println("Dirección agregadaaaa: " + direccion);
        } else {
            // Muestra un mensaje de error si la dirección está vacía
            JOptionPane.showMessageDialog(vista, "La dirección no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        System.out.println("contactoActual es null. Verifica su inicialización.");
    }
}



public void agregarTelefono() {
    // Obtener información de la vista
    String numeroIdentificacion = vista.getIdentificacion();
    String tipoTelefono = vista.getTipoTelefono();
    String numeroTelefono = vista.getNumeroTelefono();

    // Validar que ambos campos no estén vacíos
    if (!tipoTelefono.isEmpty() && !numeroTelefono.isEmpty()) {
        // Crear una instancia de la clase Telefono
        Telefono nuevoTelefono = new Telefono(numeroTelefono, tipoTelefono);

        // Llamar al método agregarTelefono en el DAO
        ContactoDAO.agregarTelefono(numeroIdentificacion, nuevoTelefono);

        // Limpiar los campos de la vista
        vista.limpiarTelefono();

        // Puedes imprimir información o realizar otras acciones aquí según tus necesidades
        System.out.println("Teléfono agregado: " + nuevoTelefono);
    } else {
        // Mostrar un mensaje de error si algún campo está vacío
        JOptionPane.showMessageDialog(vista, "Debe completar ambos campos de teléfono", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
  
  
  
  
  

    // Manejador de eventos para el botón Agregar
    private class AgregarEstudianteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtener información de la vista
            String identificacion = vista.getIdentificacion();
            String nombres = vista.getNombres();
            String apellidos = vista.getApellidos();
            String fechaNacimiento = vista.getFechaNacimiento();
            String tipoContacto = vista.getTipoContacto();
            List<String> direcciones = vista.obtenerDireccionesDesdeVista();
            // Crear una instancia de ContactoModelo con la información
            ContactoModelo nuevoEstudiante = new ContactoModelo(identificacion, nombres, apellidos, fechaNacimiento,direcciones,tipoContacto);
            nuevoEstudiante.setNumeroIdentificacion(identificacion);
            nuevoEstudiante.setNombres(nombres);
            nuevoEstudiante.setApellidos(apellidos);
            nuevoEstudiante.setFechaNacimiento(fechaNacimiento);
            nuevoEstudiante.setTipoContacto(tipoContacto);


            // Limpiar los campos de la vista
            vista.limpiarCampos();
            
       
             
             
        }
    }
    
    
    
}