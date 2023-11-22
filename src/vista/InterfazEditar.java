package vista;
/**
 * PROYECTO #3 GRUPO 80
 * @author Julian David Rengifo - 202241016
 * @author Genaro Vegas - 202241850 
 * @Profesor Luis Johany Romo Portilla 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ContactoModelo;
import colecciones.ContactoDAO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class InterfazEditar extends JFrame {
    private ContactoDAO estudianteDAO;
    private ContactoModelo estudiante;
    private ListaContactos listaContactos;
    private DefaultTableModel modeloTabla;
    private JTextField txtIdentificacion;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtFechaNacimiento;
    private JComboBox<String> cmbTipoContacto;
    private List<String> direcciones;
    private JTextField txtDireccion;
    private JTextField txtNumeroTelefono;
    private JComboBox<String> cmbTipoTelefono;
    
    private JButton btnAgregar; 
    private JButton btnAgregarDireccion;
    private JButton btnAgregarTelefono;
    private List<String> direccionesTemporales;

    public InterfazEditar(ListaContactos listaContactos, ContactoDAO estudianteDAO, ContactoModelo estudiante,DefaultTableModel modeloTabla) {
        super("Editar Contacto");
        this.estudianteDAO = estudianteDAO;
        this.estudiante = estudiante;
        this.listaContactos = listaContactos;
         this.modeloTabla = modeloTabla;
         this.direcciones = direcciones;

        setTitle("Editar Contacto");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        btnAgregarDireccion = new JButton();
        
        ImageIcon IconAgregar = new ImageIcon("src/imagenes/Mas8-1.jpg");
        btnAgregarDireccion.setIcon(IconAgregar);
        
        btnAgregarTelefono = new JButton();
        ImageIcon IconAgregar2 = new ImageIcon("src/imagenes/Mas9-1.jpg");
        btnAgregarTelefono.setIcon(IconAgregar2);
        
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
       direccionesTemporales = new ArrayList<>();
        
    
        // Añadir componentes al panel
        panel.add(new JLabel("Identificación:"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(txtIdentificacion, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1;
        gbc.gridy++;
        panel.add(txtNombres, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(txtApellidos, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Fecha de Nacimiento:"), gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(txtFechaNacimiento, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(new JLabel("Dirección:"), gbc);
        gbc.gridy = 3;
        panel.add(txtDireccion, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(btnAgregarDireccion, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Número de Teléfono:"), gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(txtNumeroTelefono, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(new JLabel("Tipo de Teléfono:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(cmbTipoTelefono, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(btnAgregarTelefono, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Tipo de Contacto:"), gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(cmbTipoContacto, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        /*gbc.anchor = GridBagConstraints.CENTER;*/ // Para centrar el botón
        gbc.gridx = 0;
        panel.add(btnAgregar, gbc);
        

    
        // Agregar el panel al centro del JFrame
        add(panel, BorderLayout.CENTER);

     /*   txtIdentificacion = new JTextField(10);
        txtNombres = new JTextField(10);
        txtApellidos = new JTextField(10);
        txtFechaNacimiento = new JTextField(10);
        cmbTipoContacto = new JComboBox<>(estudianteDAO.obtenerTiposDeContacto().toArray(new String[0])); */

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
      //  cmbTipoContacto.addItem("Estudiante");
      //  cmbTipoContacto.addItem("Profesor");
      //  cmbTipoContacto.addItem("Empleado");
    /*    add(new JLabel("Identificación:"));
        add(txtIdentificacion);
        add(new JLabel("Nombres:"));
        add(txtNombres);
        add(new JLabel("Apellidos:"));
        add(txtApellidos);
        add(new JLabel("Fecha de Nacimiento:"));
        add(txtFechaNacimiento);
        add(new JLabel("Tipo de Contacto:"));
        add(cmbTipoContacto);  */

        // Configurar los campos de texto con la información del estudiante
        txtIdentificacion.setText(estudiante.getNumeroIdentificacion());
        txtNombres.setText(estudiante.getNombres());
        txtApellidos.setText(estudiante.getApellidos());
        txtFechaNacimiento.setText(estudiante.getFechaNacimiento());
        
      /*  txtDireccion.setText(estudiante.getDireccion());
        txtNumeroTelefono.setText(estudiante.getNumeroTelefono()); */
        
        cmbTipoContacto.setSelectedItem(estudiante.getTipoContacto());
        
        cmbTipoTelefono.setSelectedItem(estudiante.getTipoContacto());
        
        estudiante.setNumeroIdentificacion("0");

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
        String idVieja = estudiante.getNumeroIdentificacion();
        String nuevoTipoContacto = cmbTipoContacto.getSelectedItem().toString();
        // Actualizar el objeto ContactoModelo con la nueva información
        estudiante.setNumeroIdentificacion(txtIdentificacion.getText());
        estudiante.setNombres(txtNombres.getText());
        estudiante.setApellidos(txtApellidos.getText());
        estudiante.setFechaNacimiento(txtFechaNacimiento.getText());
        estudiante.setTipoContacto(nuevoTipoContacto);
        // Actualizar el estudiante en la lista
        estudianteDAO.actualizarContacto(estudiante, nuevoTipoContacto,idVieja);
        //listaContactos.actualizarTablaPorTipo(nuevoTipoContacto);

        // Cerrar la ventana de edición
        dispose();

        // Mostrar la lista actualizada correspondiente al tipo de contacto en la ventana principal
        JOptionPane.showMessageDialog(this, "Contacto actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        listaContactos.actualizarTablaPorTipo(estudiante.getTipoContacto());
    }
}