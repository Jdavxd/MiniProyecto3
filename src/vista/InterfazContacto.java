/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ContactoController;
/**
 *
 * @author julia
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import controlador.ContactoController;
import colecciones.ContactoDAO;

public class InterfazContacto extends JFrame {
    private ContactoDAO estudianteDAO;
    private JTextField txtIdentificacion;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtFechaNacimiento;
    private JComboBox<String> cmbTipoContacto;
    private JButton btnAgregar; 

    
    private ListaContactos listaContactos;
    
    public InterfazContacto(ContactoDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;

        setTitle("Agregar Estudiante");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtIdentificacion = new JTextField(10);
        txtNombres = new JTextField(10);
        txtApellidos = new JTextField(10);
        txtFechaNacimiento = new JTextField(10);
        btnAgregar = new JButton("Agregar");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
         // Inicializar el JComboBox y agregar tipos de contacto
        cmbTipoContacto = new JComboBox<>();
        cmbTipoContacto.addItem("Estudiante");
        cmbTipoContacto.addItem("Profesor");
        cmbTipoContacto.addItem("Empleado");

        add(new JLabel("Identificación:"));
        add(txtIdentificacion);
        add(new JLabel("Nombres:"));
        add(txtNombres);
        add(new JLabel("Apellidos:"));
        add(txtApellidos);
        add(new JLabel("Fecha de Nacimiento:"));
        add(txtFechaNacimiento);
        add(new JLabel("Tipo de Contacto:"));
        add(cmbTipoContacto);
        add(btnAgregar);

        // Asignar el controlador
        ContactoController controlador = new ContactoController(this, estudianteDAO, listaContactos);

        // Configurar el manejador de eventos para el botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.agregarEstudiante();
            }
        });
        setLocationRelativeTo(null);
    }

    public String getIdentificacion() {
        return txtIdentificacion.getText();
    }

    public String getNombres() {
        return txtNombres.getText();
    }

    public String getApellidos() {
        return txtApellidos.getText();
    }

    public String getFechaNacimiento() {
        return txtFechaNacimiento.getText();
    }
    
   public String getTipoContacto() {
        return cmbTipoContacto.getSelectedItem().toString();
    }

    public void limpiarCampos() {
        txtIdentificacion.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtFechaNacimiento.setText("");
    }

    // Setter para el manejador de eventos del botón Agregar
    public void setAgregarEstudianteListener(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }


}