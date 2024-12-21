/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;

/**
 *
 * @author juan
 */
public class validarLogin {
    public boolean validar(JTextField usuario,JPasswordField contrasena,JLabel label){
        try {
            
            String u1= usuario.getText();
            String c1= String.valueOf(contrasena.getPassword());
            ResultSet rs=null;
            PreparedStatement ps=null;
            connection objetoconexion= new connection("login");
        
            String consulta ="select * from Usuarios where  Usuarios.ingresoUsuario='"+u1+"' and Usuarios.ingresoContrasena='"+c1+"';";
            ps=objetoconexion.conectar().prepareStatement(consulta);

            rs=ps.executeQuery();
            if (rs.next()) {
                System.out.println("correcto");
                label.setText("");
                return true;
                
            }else{
                System.out.println("Usuario o contraseña incorrecto");
                label.setText("Usuario o contraseña incorrecto");
                return false;
                 
            }
        } catch (Exception e) {
            
            System.out.println("ERROR");  
            return false;
        }
    }
}
