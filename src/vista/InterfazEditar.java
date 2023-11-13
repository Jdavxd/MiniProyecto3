/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import vista.ListaContactos;
/**
 *
 * @author julia
 */
import colecciones.EstudianteDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Estudiante;

public class InterfazEditar extends JFrame {
    private EstudianteDAO estudianteDAO;
    private Estudiante estudiante;
    private ListaContactos listaContactos;
    private JTextField txtIdentificacion;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtFechaNacimiento;

    public InterfazEditar(ListaContactos listaContactos, EstudianteDAO estudianteDAO, Estudiante estudiante) {
        super("Editar Estudiante");
        this.estudianteDAO = estudianteDAO;
        this.estudiante = estudiante;
        this.listaContactos = listaContactos;
        
        setTitle("Agregar Estudiante");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtIdentificacion = new JTextField(10);
        txtNombres = new JTextField(10);
        txtApellidos = new JTextField(10);
        txtFechaNacimiento = new JTextField(10);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Identificación:"));
        add(txtIdentificacion);
        add(new JLabel("Nombres:"));
        add(txtNombres);
        add(new JLabel("Apellidos:"));
        add(txtApellidos);
        add(new JLabel("Fecha de Nacimiento:"));
        add(txtFechaNacimiento);
       

        // Configurar los campos de texto con la información del estudiante
        txtIdentificacion.setText(estudiante.getNumeroIdentificacion());
        txtNombres.setText(estudiante.getNombres());
        txtApellidos.setText(estudiante.getApellidos());
        txtFechaNacimiento.setText(estudiante.getFechaNacimiento());

         // Crear un panel para el botón y configurar el diseño
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        
        

        
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                guardarCambios();
                
            }
        });
panelBoton.add(btnGuardar);
     add(panelBoton);

        // Centrar la ventana en relación con la ventana principal
        setLocationRelativeTo(null);
    }

    private void guardarCambios() {
        // Actualizar el objeto Estudiante con la nueva información
        estudiante.setNumeroIdentificacion(txtIdentificacion.getText());
        estudiante.setNombres(txtNombres.getText());
        estudiante.setApellidos(txtApellidos.getText());
        estudiante.setFechaNacimiento(txtFechaNacimiento.getText());

        // Actualizar el estudiante en la lista
        estudianteDAO.actualizarEstudiante(estudiante);

        // Cerrar la ventana de edición
        dispose();
      
        JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
         listaContactos.actualizarTablaDesdeOtraClase();
    }
}
