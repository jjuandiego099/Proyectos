/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.swing.JTextField;


/**
 *
 * @author juan
 */
public class envioCorreo {
    String eCorreo ="jchaparro167@unab.edu.co";
    String contra ="oedu jpti jsyb rkal";
    
    public void enviar(String text,String mensaje,String titulo){
    String correo = text;
    Properties p= new Properties();
    p.put("mail.smtp.host","smtp.gmail.com");
    p.setProperty("mail.smtp.starttls.enable", "true");
    p.put("mail.smtp.port","587");
    p.setProperty("mail.smtp.port","587");
    p.put("mail.smtp.user",eCorreo);
    p.setProperty("mail.smtp.auth","true");
     Session sesion = Session.getInstance(p, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(eCorreo, contra);
            }
        });
    MimeMessage m= new  MimeMessage(sesion); 
     try {
            // Crear el mensaje
            Message message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress(eCorreo));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject(titulo);
            message.setText(mensaje);

            // Enviar el mensaje
            Transport.send(message);
            System.out.println("Correo enviado exitosamente!");

        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo");
        }
    }
    
    }
    
    

