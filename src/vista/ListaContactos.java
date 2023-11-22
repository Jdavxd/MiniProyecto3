    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
package vista;
/**
 * PROYECTO #3 GRUPO 80
 * @author Julian David Rengifo - 202241016
 * @author Genaro Vegas - 202241850 
 * @Profesor Luis Johany Romo Portilla 
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.table.TableCellEditor;
import modelo.Telefono;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.ContactoModelo;
import colecciones.ContactoDAO;
import java.awt.Color;
import java.awt.Image;


    public class ListaContactos extends JFrame {
        private ContactoDAO ContactoDAO;
        private JTable tablaContactos;
        private DefaultTableModel modeloTabla;
        private ContactoModelo contactoActual;
        private InterfazContacto vista;


      public ListaContactos(ContactoDAO estudianteDAO,InterfazContacto interfazContacto) {
        this.ContactoDAO = estudianteDAO;
        this.vista = interfazContacto;
       // InterfazContacto interfazContacto = new InterfazContacto(estudianteDAO);
        interfazContacto.setListaContactos(this); // Establece la referencia a ListaContactos
        interfazContacto.setVisible(false);
        
        Image icono = new ImageIcon("src/imagenes/directorio-telefonico.png").getImage();
        setIconImage(icono);

        setTitle("Lista de Contactos");
        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        // Crear el modelo de la tabla con columnas
            modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("NOMBRES");
            modeloTabla.addColumn("APELLIDOS");
            modeloTabla.addColumn("TIPO DE CONTACTO");
            modeloTabla.addColumn("DIRECCIONES");
            modeloTabla.addColumn("TELÉFONOS");


        
            // Crear la tabla con el modelo
             tablaContactos = new JTable(modeloTabla) {
                @Override
               public boolean isCellEditable(int row, int column) {
        // Hacer que ninguna celda sea editable, excepto la columna de "Direcciones"
        return column == 5;
    }
               
       public TableCellEditor getCellEditor(int row, int column) {
        int col = convertColumnIndexToModel(column);

        if (col == 5) {
            List<String> direcciones = obtenerDireccionesComoLista(row);
            String[] direccionesArray = direcciones.toArray(new String[0]);
            
            JComboBox<String> cb = new JComboBox<>(direccionesArray);
            return new DefaultCellEditor(cb);
        } else {
            return super.getCellEditor(row, column);
        }
    }
            };
             // Deshabilitar la reordenación de columnas
            tablaContactos.getTableHeader().setReorderingAllowed(false);
             setLocationRelativeTo(null);
             
             for (int i = 0; i < tablaContactos.getColumnModel().getColumnCount(); i++) {
            tablaContactos.getColumnModel().getColumn(i).setResizable(false);
            }
         
         

        // Obtener la lista inicial de estudiantes y agregarlos a la tabla
        List<ContactoModelo> contactos = estudianteDAO.obtenerTodosEstudiantes();
        for (ContactoModelo contacto : contactos) {
            agregarFilaTabla(contacto);
        }
        
        
        // Crear los botones
        JButton btnActualizar = new JButton("Actualizar");
        ImageIcon IconBotActualizar = new ImageIcon("src/imagenes/editar.png");
        btnActualizar.setIcon(IconBotActualizar);
        
        JButton btnEliminar = new JButton("Eliminar");
        ImageIcon IconBotEliminar = new ImageIcon("src/imagenes/eliminar.png");
        btnEliminar.setIcon(IconBotEliminar);
        
        JButton btnNuevo = new JButton("Agregar contacto");
        ImageIcon IconBotAgregar = new ImageIcon("src/imagenes/libro.png");
        btnNuevo.setIcon(IconBotAgregar);
        
        JButton btnSalir = new JButton("Salir");
        ImageIcon IconBotSalir = new ImageIcon("src/imagenes/salida.png");
        btnSalir.setIcon(IconBotSalir);
        
        JButton btnCargarEstudiantes = new JButton("Cargar Estudiantes");
        btnCargarEstudiantes.setBackground(Color.BLACK);
        btnCargarEstudiantes.setForeground(Color.WHITE);

        JButton btnCargarEmpleados = new JButton("Cargar Empleados");
        btnCargarEmpleados.setBackground(Color.BLACK);
        btnCargarEmpleados.setForeground(Color.WHITE);

        JButton btnCargarProfesores = new JButton("Cargar Profesores");
        btnCargarProfesores.setBackground(Color.BLACK);
        btnCargarProfesores.setForeground(Color.WHITE);
        
        JButton btnCargarTodos = new JButton("Cargar Todos");
        btnCargarTodos.setBackground(Color.BLACK);
        btnCargarTodos.setForeground(Color.WHITE);
        //btnActualizar.addActionListener(e -> actualizarTabla());

        btnCargarEstudiantes.addActionListener(e -> cargarEstudiantes());
        btnCargarEmpleados.addActionListener(e -> cargarEmpleados());
        btnCargarProfesores.addActionListener(e -> cargarProfesores());
        btnCargarTodos.addActionListener(e -> cargarTodosLosContactos());
        // Configurar el manejador de eventos para el botón Actualizar
     btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                abrirVentanaEdicion();
            }
        });
        
        
         btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                eliminarContacto();
            }
        });
         
 btnNuevo.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Cierra la instancia de ListaContactos
        setVisible(false);
        dispose();

        // Hace visible la instancia de InterfazContacto que ya creaste
        vista.setVisible(true);
    }
});
 
 btnSalir.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        cerrarAplicacion();
    }
});
         
       
         

        // Crear un panel para agregar el botón y configurar el layout
       add(new JScrollPane(tablaContactos), BorderLayout.CENTER);

        // Crear un panel para agregar el botón y configurar el layout
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.add(btnCargarEstudiantes);
        panelBoton.add(btnCargarEmpleados);
        panelBoton.add(btnCargarProfesores);
        panelBoton.add(btnCargarTodos);
        // Agregar el panel con el botón al contenedor
        add(panelBoton, BorderLayout.NORTH);
        
       
        // Crear un panel para agregar el botón y configurar el layout
        JPanel panelBoton2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton2.add(btnNuevo);
        panelBoton2.add(btnActualizar);        
        panelBoton2.add(btnEliminar);    
        panelBoton2.add(btnSalir);

        

        // Agregar el panel con el botón al contenedor
        add(panelBoton2, BorderLayout.SOUTH);

        // Hacer visible la interfaz
        setVisible(true);
    }
        // Método para agregar una fila a la tabla con la información del estudiante
 private void agregarFilaTabla(ContactoModelo contacto) {
    // Modificar según el tipo de contacto
    String tipoContacto = contacto.getTipoContacto();
    List<String> direcciones = contacto.getDirecciones();
    String obtenerDireccionesComoLista = direcciones.stream().collect(Collectors.joining(", "));
    String telefonos = obtenerTelefonos(contacto);

    Object[] fila = {
            contacto.getNumeroIdentificacion(),
            contacto.getNombres(),
            contacto.getApellidos(),
            tipoContacto,
            obtenerDireccionesComoLista,
            telefonos
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
        
public List<String> obtenerDireccionesComoLista(int filaSeleccionada) {
    String direccionesComoString = (String) modeloTabla.getValueAt(filaSeleccionada, 5);
    // Divide las direcciones utilizando un separador (por ejemplo, comas)
    String[] direccionesArray = direccionesComoString.split(",");

    // Convierte el array en una lista y elimina espacios en blanco
    return Arrays.stream(direccionesArray)
                 .map(String::trim)
                 .collect(Collectors.toList());
}

private void cerrarAplicacion() {
    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cerrar la aplicación?", "Cerrar aplicación", JOptionPane.YES_NO_OPTION);
    if (confirmacion == JOptionPane.YES_OPTION) {
        System.exit(0);
    }
}


private String obtenerTelefonos(ContactoModelo contacto) {
    List<Telefono> telefonos = contacto.getTelefonos();
    StringBuilder sb = new StringBuilder();
    for (Telefono telefono : telefonos) {
        sb.append(telefono.getTipo()).append(": ").append(telefono.getNumero()).append(", ");
    }
    return sb.toString().isEmpty() ? "" : sb.substring(0, sb.length() - 2);
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

 
       private void cargarTodosLosContactos() {
    actualizarTabla();
}
       

private void eliminarContacto() {
    int filaSeleccionada = tablaContactos.getSelectedRow();

    if (filaSeleccionada != -1) {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este contacto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            String numeroIdentificacion = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
            String tipoContacto = (String) modeloTabla.getValueAt(filaSeleccionada, 4);

            ContactoDAO.eliminarContacto(numeroIdentificacion);

            // Actualizar la tabla solo para el tipo de contacto específico
            actualizarTablaPorTipo(tipoContacto);

            JOptionPane.showMessageDialog(this, "Contacto eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // El usuario seleccionó NO, no hacer nada o mostrar un mensaje opcional.
        }
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

    private ContactoModelo obtenerContactoModeloDesdeFila(int filaSeleccionada) {
        // Obtener los valores de la fila seleccionada
 String numeroIdentificacion = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
    String nombres = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
    String apellidos = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
    String tipoContacto = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
    String fechaNacimiento = (String) modeloTabla.getValueAt(filaSeleccionada, 5);

     // Crear una lista vacía de direcciones
        List<String> direcciones = new ArrayList<>();

    // Crear y devolver un nuevo objeto ContactoModelo con los valores obtenidos
    return new ContactoModelo(numeroIdentificacion, nombres, apellidos, fechaNacimiento, direcciones, tipoContacto);
    }
    
       public void notificarActualizacionTabla() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                actualizarTabla();
            }
        });
    }

private void abrirVentanaEdicion() {
    int filaSeleccionada = tablaContactos.getSelectedRow();

    if (filaSeleccionada >= 0) {
        contactoActual = obtenerContactoModeloDesdeFila(filaSeleccionada);
         List<String> direccionesActuales = obtenerDireccionesComoLista(filaSeleccionada);

        // Establecer las direcciones existentes en el objeto contactoActual
        contactoActual.setDirecciones(direccionesActuales);
        String numeroIdentificacion = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
        String nombres = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        String apellidos = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
        String fechaNacimiento = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
        List<String> direcciones = obtenerDireccionesComoLista(filaSeleccionada);
        String tipoContacto = (String) modeloTabla.getValueAt(filaSeleccionada, 4);

        InterfazEditar ventanaEdicion = new InterfazEditar(ListaContactos.this, ContactoDAO, contactoActual, modeloTabla);
        ventanaEdicion  .setVisible(true);
    } else {
        JOptionPane.showMessageDialog(ListaContactos.this, "Seleccione un contacto para actualizar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        setVisible(true);
    }
}
        // Otros métodos y funcionalidades según tus necesidades
    }   