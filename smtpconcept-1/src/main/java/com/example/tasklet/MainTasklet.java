package com.example.tasklet;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import javax.mail.*;
import javax.mail.internet.*;


public class MainTasklet implements Tasklet {
	
	private String Name;
	private String Gmail;
	private long Ph_No;
	private String Status;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    	 String host = "smtp.gmail.com";
         final String user = "karthikyuvaraj21@gmail.com";
         final String password = "xqed srtp hdhc eopv";

         String to = "karthikthunder21112002@gmail.com";
         Properties props = new Properties();
         props.put("mail.smtp.host", host);
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.ssl.protocols", "TLSv1.2");
         props.put("mail.smtp.port", 587);
         props.put("mail.smtp.auth", "true");

         Session session = Session.getInstance(props, new javax.mail.Authenticator() {
             protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                 return new javax.mail.PasswordAuthentication(user, password);
             }

  
    	    });  
    	  
    	    try {  
    	     MimeMessage message = new MimeMessage(session);  
    	     message.setFrom(new InternetAddress(user)); 
    	     String SelectQuery = "Select * from new";
    	        try (Connection conn = ConnectionsJdbc.getConnection();
    	             PreparedStatement pstmt = conn.prepareStatement(SelectQuery)) {
    	            ResultSet rs = pstmt.executeQuery();
    	            StringBuilder send = new StringBuilder();

    	            send.append("<!DOCTYPE html>\r\n")
    	                .append("<html lang=\"en\">\r\n")
    	                .append("<head>\r\n")
    	                .append("    <meta charset=\"UTF-8\">\r\n")
    	                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n")
    	                .append("    <title>Simple Table</title>\r\n")
    	                .append("    <style>\r\n")
    	                .append("        /* Table Styling */\r\n")
    	                .append("        table {\r\n")
    	                .append("            width: 80%;\r\n")
    	                .append("            border-collapse: collapse;\r\n")
    	                .append("            margin: 20px auto;\r\n")
    	                .append("            font-family: Arial, sans-serif;\r\n")
    	                .append("        }\r\n")
    	                .append("        /* Table Header */\r\n")
    	                .append("        th {\r\n")
    	                .append("            padding: 12px 15px;\r\n")
    	                .append("            text-align: left;\r\n")
    	                .append("            font-weight: bold;\r\n")
    	                .append("        }\r\n")
    	                .append("        /* Table Cells */\r\n")
    	                .append("        td {\r\n")
    	                .append("            padding: 12px 15px;\r\n")
    	                .append("            border: 1px solid #ddd;\r\n")
    	                .append("            text-align: left;\r\n")
    	                .append("        }\r\n")
    	                .append("        /* Border Styling */\r\n")
    	                .append("        td, th {\r\n")
    	                .append("            border: 1px solid #ddd;\r\n")
    	                .append("        }\r\n")
    	                .append("        /* Responsive Design for Mobile */\r\n")
    	                .append("        @media screen and (max-width: 600px) {\r\n")
    	                .append("            table {\r\n")
    	                .append("                width: 100%;\r\n")
    	                .append("                font-size: 14px;\r\n")
    	                .append("            }\r\n")
    	                .append("            td, th {\r\n")
    	                .append("                padding: 8px;\r\n")
    	                .append("            }\r\n")
    	                .append("        }\r\n")
    	                .append("    </style>\r\n")
    	                .append("</head>\r\n")
    	                .append("<body>\r\n")
    	                .append("    <table>\r\n")
    	                .append("        <tr>\r\n")
    	                .append("            <th>Name</th>\r\n")
    	                .append("            <th>Gmail</th>\r\n")
    	                .append("            <th>Ph_No</th>\r\n")
    	                .append("            <th>Status</th>\r\n")
    	                .append("        </tr>\r\n");

    	            while (rs.next()) {
    	            	 if(rs.getString("Status").equals("Deactivate")) {
    	                send.append("        <tr>\r\n")
    	                    .append("            <td>").append(rs.getString("Name")).append("</td>\r\n")
    	                    .append("            <td>").append(rs.getString("Gmail")).append("</td>\r\n")
    	                    .append("            <td>").append(rs.getString("Ph_No")).append("</td>\r\n")
    	                    .append("            <td>Deactivated</td>\r\n")
    	                    .append("        </tr>\r\n");
    	            }
    	            }

    	            send.append("    </table>\r\n")
    	                .append("</body>\r\n")
    	                .append("</html>\r\n");

    	            String finalHtml = send.toString();
    	            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	    	    	 message.setSubject("Your Account is 'Activated'");  
	    	    	 message.setContent(finalHtml, "text/html");
		    	     Transport.send(message);  
    				while (rs.next()) {
    	                    		    	    
    		    	
    		    	     if(rs.getString("Status").equals("Activate")) {
    		    	    	 message.addRecipient(Message.RecipientType.TO,new InternetAddress(rs.getString("Gmail")));  
    		    	    	 message.setSubject("Your Account is 'Activated'");  
        		    	     message.setText("Hi, "+ rs.getString("Name") + ". Your Account is working");  
        		    	     Transport.send(message);  
    		    	     } else {
    		    	    	  
    		    	     }
    		    	     
    				}
    				
    				conn.close();

    	           
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	     
    	  
    	     System.out.println("message sent successfully...");  
    	   


    	     } catch (MessagingException e) {e.printStackTrace();}  
    	   
    	  
    	return RepeatStatus.FINISHED;
    }
}


