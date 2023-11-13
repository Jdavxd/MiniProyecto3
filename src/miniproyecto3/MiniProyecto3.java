/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package miniproyecto3;

import colecciones.ContactoImplementacionDAO;
import javax.swing.SwingUtilities;
import vista.InterfazContacto;
import vista.ListaContactos;
import colecciones.ContactoDAO;

/**
 *
 * @author julia
 */
public class MiniProyecto3 {

    /**
     * @param args the command line arguments
     */
      public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ContactoDAO estudianteDAO = new ContactoImplementacionDAO();
                new InterfazContacto(estudianteDAO).setVisible(true);  

            }
        });
    }
    
}
