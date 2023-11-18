/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ContactoController;
/**
 * @author Julian Rengifo
 * @author Genaro Vegas 2241850
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.ContactoController;
import colecciones.ContactoDAO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

        setTitle("Agregar Contacto");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear un panel para alinear los componentes en el centro
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes entre los componentes
    
        // Componentes
        txtIdentificacion = new JTextField();
        txtNombres = new JTextField();
        txtApellidos = new JTextField();
        txtFechaNacimiento = new JTextField();
        btnAgregar = new JButton("Agregar");
    
        cmbTipoContacto = new JComboBox<>();
        cmbTipoContacto.addItem("Estudiante");
        cmbTipoContacto.addItem("Profesor");
        cmbTipoContacto.addItem("Empleado");
    
        // Establecer tamaños preferidos para los JTextField y JComboBox
        txtIdentificacion.setPreferredSize(new Dimension(400, 30));
        txtNombres.setPreferredSize(new Dimension(400, 30));
        txtApellidos.setPreferredSize(new Dimension(400, 30));
        txtFechaNacimiento.setPreferredSize(new Dimension(400, 30));
        cmbTipoContacto.setPreferredSize(new Dimension(150, 30));
    
        // Añadir componentes al panel
        panel.add(new JLabel("Identificación:"), gbc);
        gbc.gridy++;
        panel.add(txtIdentificacion, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Nombres:"), gbc);
        gbc.gridy++;
        panel.add(txtNombres, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Apellidos:"), gbc);
        gbc.gridy++;
        panel.add(txtApellidos, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Fecha de Nacimiento:"), gbc);
        gbc.gridy++;
        panel.add(txtFechaNacimiento, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Tipo de Contacto:"), gbc);
        gbc.gridy++;
        panel.add(cmbTipoContacto, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; // Para centrar el botón
        panel.add(btnAgregar, gbc);

    
        // Agregar el panel al centro del JFrame
        add(panel, BorderLayout.CENTER);

        /*
        txtIdentificacion = new JTextField();
        txtIdentificacion.setMaximumSize(new Dimension(500, 20));
        txtNombres = new JTextField();
        txtNombres.setMaximumSize(new Dimension(500,20));
        txtApellidos = new JTextField();
        txtApellidos.setMaximumSize(new Dimension(500, 20));
        txtFechaNacimiento = new JTextField();
        txtFechaNacimiento.setMaximumSize(new Dimension(500, 20));
        btnAgregar = new JButton("Agregar");
        
        

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        
        
         // Inicializar el JComboBox y agregar tipos de contacto
        cmbTipoContacto = new JComboBox<>();
        cmbTipoContacto.addItem("Estudiante");
        cmbTipoContacto.addItem("Profesor");
        cmbTipoContacto.addItem("Empleado");
        
        cmbTipoContacto.setMaximumSize(new Dimension(500, 20));

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
        */

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