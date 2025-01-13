package com.example.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MainTasklet implements Tasklet {

    private static boolean emailSent = false;
    private JdbcTemplate jdbcTemplate;

 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
//        if (emailSent) {
//            System.out.println("Email already sent. Task will not run again.");
//            return RepeatStatus.FINISHED;
//        }

        
        List<Map<String, Object>> userDataList = jdbcTemplate.queryForList("SELECT * FROM User_table WHERE status = 'inactive'");

        StringBuilder emailContent = new StringBuilder("User Data:\n\n");

        
        for (Map<String, Object> row : userDataList) {
            String name = (String) row.get("name");
            String email = (String) row.get("email");
            String status = (String) row.get("status");

            emailContent.append("Name: ").append(name)
                        .append(", Email: ").append(email)
                        .append(", Status: ").append(status).append("\n");
        }

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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("User Data");

            // Build the HTML email content
            StringBuilder emailContent1 = new StringBuilder();
            emailContent1.append("<html><body>");
            emailContent1.append("<h2>User Data:</h2>");
            emailContent1.append("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">");
            emailContent1.append("<thead><tr><th>Name</th><th>Email</th><th>Status</th></tr></thead>");
            emailContent1.append("<tbody>");

            for (Map<String, Object> row : userDataList) {
                String name = (String) row.get("name");
                String email = (String) row.get("email");
                String status = (String) row.get("status");

                emailContent1.append("<tr>")
                            .append("<td>").append(name).append("</td>")
                            .append("<td>").append(email).append("</td>")
                            .append("<td>").append(status).append("</td>")
                            .append("</tr>");
            }

            emailContent1.append("</tbody></table>");
            emailContent1.append("</body></html>");

            // Set the email content as HTML
            message.setContent(emailContent1.toString(), "text/html");

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully...");
            emailSent = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return RepeatStatus.FINISHED;
        
    }
}