package proyecto;


import java.awt.Label;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juan
 */
public class Horas {
    public boolean Horas(JTextField nombre,JTextField descripcion,JTextField lugar,JTextField horas,String user){
        if(!nombre.getText().equals("") && !descripcion.getText().equals("") && !lugar.getText().equals("") && !horas.getText().equals("")){
            System.out.println("bien");
            int numeroHoras=Integer.parseInt(horas.getText());
         
            PreparedStatement ps=null;
            connection objetoconexion= new connection("login");
            String consulta="insert into registroHoras(nombre,descripcion,lugar,horas,usuario) values ('"+nombre.getText()+"', '"+descripcion.getText()+"','"+lugar.getText()+"',"+numeroHoras+",'"+user+"');";
            try {
                ps=objetoconexion.conectar().prepareStatement(consulta);
                int rows=ps.executeUpdate();
                        if(rows>0){
                        String mensaje="Usted ha agregado una nueva hora en su plataforma";
                        String asunto="Registro de una nueva hora";
                        envioCorreo ec= new envioCorreo();
                        ec.enviar(user,mensaje,asunto);
                        return true;}
            } catch (SQLException ex) {
                Logger.getLogger(Horas.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error");
                return false;
            }
            
    }
        else{
            System.out.println("mal");
        }
        return false;
    }
    public DefaultTableModel cHoras(String user,JTable tabla,JLabel totalHoras){
        PreparedStatement ps=null;
        ResultSet rs=null;
        String consulta="SELECT * FROM registrohoras WHERE usuario = '"+user+"';";
        connection c1= new connection("login");
        try {
            ps=c1.conectar().prepareStatement(consulta);
            rs=ps.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            while (rs.next()) {
            String nombre=rs.getString("nombre");
            String lugar=rs.getString("lugar");
            String descripcion=rs.getString("descripcion");
            String fecha=rs.getString("fecha");
            int horas = rs.getInt("horas");    
            
            modelo.addRow(new Object[]{nombre,lugar,descripcion,horas,fecha});
            System.out.println('a');
                
                
                
            } return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(Horas.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR");
        }
        return null;
    }
    public boolean eHoras(String user, String act){
        try {
            PreparedStatement ps=null;
            int rs=0;
            String texto="delete from registroHoras where usuario = '"+user+"' and nombre ='"+act+"';";
            connection c1= new connection("login");
            ps=c1.conectar().prepareStatement(texto);
            rs=ps.executeUpdate();
            if(rs>0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Horas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean editHoras(String user,String act,String desc, String lugar, int horas){
         try {
            PreparedStatement ps=null;
            int rs=0;
            String texto="update registroHoras set nombre ='"+act+"', lugar ='"+lugar+"', descripcion = '"+desc+"', horas= '"+horas+"', fecha = now() where usuario = '"+user+"' and nombre ='"+act+"';";
            connection c1= new connection("login");
            ps=c1.conectar().prepareStatement(texto);
            rs=ps.executeUpdate();
            if(rs>0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Horas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
