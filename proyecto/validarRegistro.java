/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author juan
 */
public class validarRegistro {
    public boolean validaregistro(String user, JPasswordField contra1,JPasswordField contra2,JLabel label) {
        String pattern = "^[a-zA-Z0-9]{1,15}@unab\\.edu\\.co$";
        String u1=user;
        String c1= String.valueOf(contra1.getPassword());
        String c2= String.valueOf(contra2.getPassword());
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(user);
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs=null;
        ResultSet rs2=null;
        if( m.matches()&& c1.equals(c2)){
          
            try{
                connection objetoConexion = new connection("login");
              
                ps=objetoConexion.conectar().prepareStatement("SELECT COUNT(*) FROM Usuarios WHERE ingresoUsuario = '"+u1+"';");
                rs=ps.executeQuery();
                if(rs.next()){
                    int count=rs.getInt(1);
                    if(count!=0){
                        label.setText("ya existe una cuenta con este correo");
                        return false;
                        
                    }
                    else{
                        String mensaje="Usted se ha registrado exitosamente en la app de Horas Libres de francho y sus amigos";
                        String asunto="Registro en nueva app";
                        envioCorreo ec= new envioCorreo();
                        ec.enviar(user,mensaje,asunto);
                        ps1=objetoConexion.conectar().prepareStatement("insert into Usuarios(ingresoUsuario,ingresoContrasena,horas) values ('"+u1+"', '"+c1+"',0);");
                        int rows=ps1.executeUpdate();
                        if(rows>0){
                        label.setText("");}
                        return true;
                    }
                }
                
            }catch (SQLException ex) {
                Logger.getLogger(validarRegistro.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR");
                return false;
            } 
        }else{
            label.setText("Datos ingresados incorrectamente");
            return false;
            
        }
        return false;
        
    }
}
