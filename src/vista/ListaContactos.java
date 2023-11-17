/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.ContactoModelo;
import colecciones.ContactoDAO;

public class ListaContactos extends JFrame {
    private ContactoDAO ContactoDAO;
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;

    public ListaContactos(ContactoDAO estudianteDAO) {
        this.ContactoDAO = estudianteDAO;

        setTitle("Lista de Contactos");
        setSize(900, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el modelo de la tabla con columnas
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Identificación");
        modeloTabla.addColumn("Nombres");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Fecha de Nacimiento");
        modeloTabla.addColumn("Tipo de contacto");


        // Crear la tabla con el modelo
         tablaContactos = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que ninguna celda sea editable
                return false;
            }
        };
         // Deshabilitar la reordenación de columnas
        tablaContactos.getTableHeader().setReorderingAllowed(false);
         setLocationRelativeTo(null);
         
         

        // Obtener la lista inicial de estudiantes y agregarlos a la tabla
        List<ContactoModelo> contactos = estudianteDAO.obtenerTodosEstudiantes();
        for (ContactoModelo contacto : contactos) {
            agregarFilaTabla(contacto);
        }
        
        
        // Crear el botón "Actualizar"
        JButton btnActualizar = new JButton("Actualizar");
        // Configurar el manejador de eventos para el botón Actualizar
        
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCargarEstudiantes = new JButton("Cargar Estudiantes");
        JButton btnCargarEmpleados = new JButton("Cargar Empleados");
        JButton btnCargarProfesores = new JButton("Cargar Profesores");
        //btnActualizar.addActionListener(e -> actualizarTabla());

        btnCargarEstudiantes.addActionListener(e -> cargarEstudiantes());
        btnCargarEmpleados.addActionListener(e -> cargarEmpleados());
        btnCargarProfesores.addActionListener(e -> cargarProfesores());
        
        // Configurar el manejador de eventos para el botón Actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                abrirVentanaEdicion();
            }
        });
        
        
         btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                eliminarContacto();
            }
        });
         
       
         

        // Crear un panel para agregar el botón y configurar el layout
       add(new JScrollPane(tablaContactos), BorderLayout.CENTER);

        // Crear un panel para agregar el botón y configurar el layout
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnActualizar);        
        panelBoton.add(btnEliminar);
        panelBoton.add(btnCargarEstudiantes);
        panelBoton.add(btnCargarEmpleados);
        panelBoton.add(btnCargarProfesores);


        // Agregar el panel con el botón al contenedor
        add(panelBoton, BorderLayout.SOUTH);

        // Hacer visible la interfaz
        setVisible(true);
    }

    // Método para agregar una fila a la tabla con la información del estudiante
       private void agregarFilaTabla(ContactoModelo estudiante) {
        // Modificar según el tipo de contacto
        String tipoContacto = estudiante.getTipoContacto();

        Object[] fila = {
                estudiante.getNumeroIdentificacion(),
                estudiante.getNombres(),
                estudiante.getApellidos(),
                estudiante.getFechaNacimiento(),
                tipoContacto
        };
        modeloTabla.addRow(fila);
    }
       
       
    private void cargarEstudiantes() {
    modeloTabla.setRowCount(0);  // Limpiar la tabla antes de agregar las filas actualizadas
    List<ContactoModelo> estudiantes = ContactoDAO.obtenerContactosPorTipo("Estudiante");
    for (ContactoModelo estudiante : estudiantes) {
        agregarFilaTabla(estudiante);
    }
}
           
           
       private void cargarEmpleados() {
        modeloTabla.setRowCount(0);  // Limpiar la tabla antes de agregar las filas actualizadas
        List<ContactoModelo> empleados = ContactoDAO.obtenerContactosPorTipo("Empleado");
        for (ContactoModelo empleado : empleados) {
            agregarFilaTabla(empleado);
        }
    }
       
       private void cargarProfesores() {
    modeloTabla.setRowCount(0);  // Limpiar la tabla antes de agregar las filas actualizadas
    List<ContactoModelo> profesores = ContactoDAO.obtenerContactosPorTipo("Profesor");
    for (ContactoModelo profesor : profesores) {
        agregarFilaTabla(profesor);
    }
}
       
       
       

       
private void eliminarContacto() {
    int filaSeleccionada = tablaContactos.getSelectedRow();

    if (filaSeleccionada != -1) {
        String numeroIdentificacion = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        String tipoContacto = (String) modeloTabla.getValueAt(filaSeleccionada, 4);

        ContactoDAO.eliminarContacto(numeroIdentificacion);

        // Actualizar la tabla solo para el tipo de contacto específico
        actualizarTablaPorTipo(tipoContacto);

        JOptionPane.showMessageDialog(this, "Contacto eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un Contacto para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public void actualizarTablaPorTipo(String tipoContacto) {
    List<ContactoModelo> contactosPorTipoLista = ContactoDAO.obtenerContactosPorTipo(tipoContacto);

    // Limpiar la tabla antes de agregar las filas actualizadas
    modeloTabla.setRowCount(0);

    // Agregar las filas actualizadas solo para el tipo de contacto específico
    for (ContactoModelo contacto : contactosPorTipoLista) {
        agregarFilaTabla(contacto);
    }
}
       
       
    
    

    // Método para actualizar la tabla cuando sea necesario
public void actualizarTabla() {
    modeloTabla.setRowCount(0);  // Limpiar la tabla antes de agregar las filas actualizadas
    List<ContactoModelo> contactos = ContactoDAO.obtenerTodosEstudiantes();
    for (ContactoModelo contacto : contactos) {
        agregarFilaTabla(contacto);
    }
}

public void actualizarTablaDesdeOtraClase() {
    actualizarTabla();
}



private void abrirVentanaEdicion() {
    int filaSeleccionada = tablaContactos.getSelectedRow();

    if (filaSeleccionada >= 0) {
        String numeroIdentificacion = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        String nombres = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        String apellidos = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
        String fechaNacimiento = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
        String tipoContacto = (String) modeloTabla.getValueAt(filaSeleccionada, 4);

        ContactoModelo contactoSeleccionado = new ContactoModelo(numeroIdentificacion, nombres, apellidos, fechaNacimiento, tipoContacto);

        InterfazEditar ventanaEdicion = new InterfazEditar(ListaContactos.this, ContactoDAO, contactoSeleccionado, modeloTabla);
        ventanaEdicion.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(ListaContactos.this, "Seleccione un contacto para actualizar", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
}
    // Otros métodos y funcionalidades según tus necesidades
}