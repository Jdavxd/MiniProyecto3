package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ContactoModelo;
import colecciones.ContactoDAO;
import javax.swing.table.DefaultTableModel;

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

    public InterfazEditar(ListaContactos listaContactos, ContactoDAO estudianteDAO, ContactoModelo estudiante,DefaultTableModel modeloTabla) {
        super("Editar Contacto");
        this.estudianteDAO = estudianteDAO;
        this.estudiante = estudiante;
        this.listaContactos = listaContactos;
         this.modeloTabla = modeloTabla;

        setTitle("Editar Contacto");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtIdentificacion = new JTextField(10);
        txtNombres = new JTextField(10);
        txtApellidos = new JTextField(10);
        txtFechaNacimiento = new JTextField(10);
        cmbTipoContacto = new JComboBox<>(estudianteDAO.obtenerTiposDeContacto().toArray(new String[0]));

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
      //  cmbTipoContacto.addItem("Estudiante");
      //  cmbTipoContacto.addItem("Profesor");
      //  cmbTipoContacto.addItem("Empleado");
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

        // Configurar los campos de texto con la información del estudiante
        txtIdentificacion.setText(estudiante.getNumeroIdentificacion());
        txtNombres.setText(estudiante.getNombres());
        txtApellidos.setText(estudiante.getApellidos());
        txtFechaNacimiento.setText(estudiante.getFechaNacimiento());
        cmbTipoContacto.setSelectedItem(estudiante.getTipoContacto());
        
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