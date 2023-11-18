/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import vista.InterfazContacto;
import modelo.ContactoModelo;
/**
 *
 * @author julia
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import vista.ListaContactos;
import colecciones.ContactoDAO;

public class ContactoController {
    private InterfazContacto vista;
    private ContactoDAO ContactoDAO;
     private ListaContactos listaContactos;

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


    // Crear una instancia de ContactoModelo con la información
   ContactoModelo nuevoContacto = new ContactoModelo(identificacion, nombres, apellidos, fechaNacimiento,tipoContacto);
    nuevoContacto.setNumeroIdentificacion(identificacion);
    nuevoContacto.setNombres(nombres);
    nuevoContacto.setApellidos(apellidos);
    nuevoContacto.setFechaNacimiento(fechaNacimiento);
    nuevoContacto.setTipoContacto(tipoContacto);

    // Agregar el estudiante utilizando el DAO
    ContactoDAO.agregarContacto(nuevoContacto);
    
    // Limpiar los campos de la vista
    vista.limpiarCampos();
    
  List<ContactoModelo> listaEstudiantes = ContactoDAO.obtenerTodosEstudiantes();
System.out.println("Lista de estudiantes después de agregar:");
for (ContactoModelo estudiante : listaEstudiantes) {
    // Imprimir la información del estudiante y su tipo de contacto
    System.out.println("Tipo de Contacto: " + estudiante.getTipoContacto());
    System.out.println(estudiante); // Esto llama automáticamente al método toString()
    System.out.println("-----------");
}
    
    

    // Mostrar mensaje de éxito en un cuadro de diálogo
    
    JOptionPane.showMessageDialog(vista, "Estudiante agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    
     if (listaContactos != null) {
        listaContactos.setVisible(false);
    }
      SwingUtilities.invokeLater(() -> {
        listaContactos = new ListaContactos(ContactoDAO);
        setListaContactos(listaContactos);
        listaContactos.actualizarTabla();
    });
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

            // Crear una instancia de ContactoModelo con la información
            ContactoModelo nuevoEstudiante = new ContactoModelo(identificacion, nombres, apellidos, fechaNacimiento,tipoContacto);
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