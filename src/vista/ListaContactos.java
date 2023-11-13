/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import colecciones.EstudianteDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import modelo.Estudiante;

public class ListaContactos extends JFrame {
    private EstudianteDAO estudianteDAO;
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;

    public ListaContactos(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;

        setTitle("Lista de Contactos");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el modelo de la tabla con columnas
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Identificación");
        modeloTabla.addColumn("Nombres");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Fecha de Nacimiento");

        // Crear la tabla con el modelo
         tablaContactos = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que ninguna celda sea editable
                return false;
            }
        };
         
         
         
         

        // Obtener la lista inicial de estudiantes y agregarlos a la tabla
        List<Estudiante> contactos = estudianteDAO.obtenerTodosEstudiantes();
        for (Estudiante contacto : contactos) {
            agregarFilaTabla(contacto);
        }

        // Agregar la tabla al contenedor
        add(new JScrollPane(tablaContactos));

        // Hacer visible la interfaz
        setVisible(true);
    }

    // Método para agregar una fila a la tabla con la información del estudiante
    private void agregarFilaTabla(Estudiante estudiante) {
        Object[] fila = {estudiante.getNumeroIdentificacion(), estudiante.getNombres(), estudiante.getApellidos(), estudiante.getFechaNacimiento()};
        modeloTabla.addRow(fila);
    }
    
    

    // Método para actualizar la tabla cuando sea necesario
public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        List<Estudiante> contactos = estudianteDAO.obtenerTodosEstudiantes();
        for (Estudiante contacto : contactos) {
            agregarFilaTabla(contacto);
        }
    }

    // Otros métodos y funcionalidades según tus necesidades
}