    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package vista;



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


    public class ListaContactos extends JFrame {
        private ContactoDAO ContactoDAO;
        private JTable tablaContactos;
        private DefaultTableModel modeloTabla;
        private ContactoModelo contactoActual;
        private InterfazContacto vista;
        
        
        public ListaContactos(ContactoDAO estudianteDAO,InterfazContacto interfazContacto) {
            this.ContactoDAO = estudianteDAO;
            this.vista = interfazContacto;
            this.contactoActual = new ContactoModelo("", "", "", "", new ArrayList<>(), "");
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
            modeloTabla.addColumn("Direcciones");
            modeloTabla.addColumn("Teléfonos");



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

    public ListaContactos(ContactoDAO estudianteDAO) {

        

        this.ContactoDAO = estudianteDAO;

        setTitle("Lista de Contactos");
        setSize(900, 450);
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
        ImageIcon IconBotActualizar = new ImageIcon("src/imagenes/Edit1-2.png");
        btnActualizar.setIcon(IconBotActualizar);
        
        JButton btnEliminar = new JButton("Eliminar");
        
        ImageIcon IconBotEliminar = new ImageIcon("src/imagenes/BtnElim1-2.png");
        btnEliminar.setIcon(IconBotEliminar);
        /*Image img = IconBotEliminar.getImage();

        // Redimensionar el icono al tamaño del botón
        var newImg = img.getScaledInstance(btnEliminar.getWidth(), btnEliminar.getHeight(), java.awt.Image.SCALE_SMOOTH);

        // Establecer el icono redimensionado en el botón
        IconBotEliminar = new ImageIcon(newImg);
        btnEliminar.setIcon(IconBotEliminar);
        */
        
        JButton btnCargarEstudiantes = new JButton("Cargar Estudiantes");
        btnCargarEstudiantes.setBackground(Color.BLACK);
        btnCargarEstudiantes.setForeground(Color.WHITE);

        JButton btnCargarEmpleados = new JButton("Cargar Empleados");
        btnCargarEmpleados.setBackground(Color.BLACK);
        btnCargarEmpleados.setForeground(Color.WHITE);

        JButton btnCargarProfesores = new JButton("Cargar Profesores");
        btnCargarProfesores.setBackground(Color.BLACK);
        btnCargarProfesores.setForeground(Color.WHITE);
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
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.add(btnCargarEstudiantes);
        panelBoton.add(btnCargarEmpleados);
        panelBoton.add(btnCargarProfesores);


        // Agregar el panel con el botón al contenedor
        add(panelBoton, BorderLayout.NORTH);
        
       
        // Crear un panel para agregar el botón y configurar el layout
        JPanel panelBoton2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton2.add(btnActualizar);        
        panelBoton2.add(btnEliminar);

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
            contacto.getFechaNacimiento(),
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
        String fechaNacimiento = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
        String tipoContacto = (String) modeloTabla.getValueAt(filaSeleccionada, 4);

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
    }
}
        // Otros métodos y funcionalidades según tus necesidades
    }   