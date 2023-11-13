/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import vista.InterfazEstudiante;
import colecciones.EstudianteDAO;
import modelo.Estudiante;
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

public class EstudianteController {
    private InterfazEstudiante vista;
    private EstudianteDAO estudianteDAO;
     private ListaContactos listaContactos;

    public EstudianteController(InterfazEstudiante vista, EstudianteDAO estudianteDAO, ListaContactos listaContactos ) {
        this.vista = vista;
        this.estudianteDAO = estudianteDAO;
        this.listaContactos = listaContactos;

        // Configurar el manejador de eventos para el botón Agregar
        this.vista.setAgregarEstudianteListener(new AgregarEstudianteListener());
    }
    
    
    public void setListaContactos(ListaContactos listaContactos) {
    this.listaContactos = listaContactos;
}

  public void agregarEstudiante() {
    // Obtener información de la vista
    String identificacion = vista.getIdentificacion();
    String nombres = vista.getNombres();
    String apellidos = vista.getApellidos();
    String fechaNacimiento = vista.getFechaNacimiento();

    // Crear una instancia de Estudiante con la información
    Estudiante nuevoEstudiante = new Estudiante();
    nuevoEstudiante.setNumeroIdentificacion(identificacion);
    nuevoEstudiante.setNombres(nombres);
    nuevoEstudiante.setApellidos(apellidos);
    nuevoEstudiante.setFechaNacimiento(fechaNacimiento);

    // Agregar el estudiante utilizando el DAO
    estudianteDAO.agregarEstudiante(nuevoEstudiante);
    
 
    // Limpiar los campos de la vista
    vista.limpiarCampos();
    
     List<Estudiante> listaEstudiantes = estudianteDAO.obtenerTodosEstudiantes();
    System.out.println("Lista de estudiantes después de agregar:");
    for (Estudiante estudiante : listaEstudiantes) {
        System.out.println(estudiante); // Esto llama automáticamente al método toString()
    }
    
    

    // Mostrar mensaje de éxito en un cuadro de diálogo
    
    JOptionPane.showMessageDialog(vista, "Estudiante agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    
     if (listaContactos != null) {
        listaContactos.dispose();
    }
      SwingUtilities.invokeLater(() -> {
        listaContactos = new ListaContactos(estudianteDAO);
        setListaContactos(listaContactos);
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

            // Crear una instancia de Estudiante con la información
            Estudiante nuevoEstudiante = new Estudiante();
            nuevoEstudiante.setNumeroIdentificacion(identificacion);
            nuevoEstudiante.setNombres(nombres);
            nuevoEstudiante.setApellidos(apellidos);
            nuevoEstudiante.setFechaNacimiento(fechaNacimiento);

            // Agregar el estudiante utilizando el DAO
            estudianteDAO.agregarEstudiante(nuevoEstudiante);

            // Limpiar los campos de la vista
            vista.limpiarCampos();
            
       
             
             
        }
    }
}