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
import java.util.List;
import modelo.Telefono;

public class InterfazContacto extends JFrame {
    private ContactoDAO estudianteDAO;
    private JTextField txtIdentificacion;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtFechaNacimiento;
    private JTextField txtDireccion;
    private JTextField txtNumeroTelefono;
    private JComboBox<String> cmbTipoTelefono;
    private JComboBox<String> cmbTipoContacto;
    private JButton btnAgregar; 
    private JButton btnAgregarDireccion;
    private JButton btnAgregarTelefono;
    private ContactoController contactoController;
    private ListaContactos listaContactos;
    
    public InterfazContacto(ContactoDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;

        setTitle("Agregar Contacto");
        setSize(1000, 700);
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
        btnAgregarDireccion = new JButton("Agregar Dirección");
        btnAgregarTelefono = new JButton("Agregar Teléfono");
        cmbTipoContacto = new JComboBox<>();
        cmbTipoContacto.addItem("Estudiante");
        cmbTipoContacto.addItem("Profesor");
        cmbTipoContacto.addItem("Empleado");
        
        txtDireccion = new JTextField();
        cmbTipoTelefono = new JComboBox<>();
        cmbTipoTelefono.addItem("Móvil");
        cmbTipoTelefono.addItem("Casa");
        cmbTipoTelefono.addItem("Oficina");
        txtNumeroTelefono = new JTextField();
       
        

    
        // Establecer tamaños preferidos para los JTextField y JComboBox
        txtIdentificacion.setPreferredSize(new Dimension(300, 30));
        txtNombres.setPreferredSize(new Dimension(300, 30));
        txtApellidos.setPreferredSize(new Dimension(300, 30));
        txtFechaNacimiento.setPreferredSize(new Dimension(300, 30));
        txtNumeroTelefono.setPreferredSize(new Dimension(200, 30));
        txtDireccion.setPreferredSize(new Dimension(300, 30));
        cmbTipoTelefono.setPreferredSize(new Dimension(150, 30));
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
        panel.add(new JLabel("Dirección:"), gbc);
        gbc.gridy++;
        panel.add(txtDireccion, gbc);
        gbc.gridy++;
        panel.add(btnAgregarDireccion, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Número de Teléfono:"), gbc);
        gbc.gridy++;
        panel.add(txtNumeroTelefono, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Tipo de Teléfono:"), gbc);
        gbc.gridy++;
        panel.add(cmbTipoTelefono, gbc);
        gbc.gridy++;
        panel.add(btnAgregarTelefono, gbc);
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
                controlador.agregarContacto();
            }
        });
        setLocationRelativeTo(null);
        
            btnAgregarDireccion.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.agregarDireccion();
        }
    });
            
              btnAgregarTelefono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.agregarTelefono();
            }
        });
              
              
        
        
        
    }
    
     public void setAgregarDireccionesListener(ActionListener listener) {
        btnAgregarDireccion.addActionListener(listener);
    }
     
      public void setContactoController(ContactoController contactoController) {
        this.contactoController = contactoController;
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
   
 public String getDireccion() {
        return txtDireccion.getText();
    }

    public void limpiarDireccion() {
        txtDireccion.setText("");
    }

    public String getTipoTelefono() {
        return cmbTipoTelefono.getSelectedItem().toString();
    }

    public String getNumeroTelefono() {
        return txtNumeroTelefono.getText();
    }
    
    public void limpiarTelefono(){
        txtNumeroTelefono.setText("");
    }
    
    public void agregarTelefono() {
    String tipoTelefono = getTipoTelefono();
    String numeroTelefono = getNumeroTelefono();

    if (!tipoTelefono.isEmpty() && !numeroTelefono.isEmpty()) {
        Telefono nuevoTelefono = new Telefono(numeroTelefono, tipoTelefono);
        // Agrega el nuevo teléfono al modelo de contacto

estudianteDAO.agregarTelefono(getIdentificacion(), nuevoTelefono);
        // Limpia los campos después de agregar
        limpiarTelefono();
    } else {
        JOptionPane.showMessageDialog(this, "Ingresa el tipo y número de teléfono", "Error", JOptionPane.ERROR_MESSAGE);
    }
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