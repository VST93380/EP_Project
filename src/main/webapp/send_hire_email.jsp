<%@ page import="java.util.Properties" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="java.util.Base64" %>
<%
    String email = request.getParameter("email");
    String subject = "Congratulations! You are selected!";
    String body = "Dear Candidate,\n\nCongratulations! We are pleased to inform you that you have been selected for the job.\n\nBest regards,\nYour Company";

    String from = "2100031989@kluniversity.in"; // Your Outlook email address
    String password = "143Amma143*"; // Your Outlook email password

    try {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.office365.com");

        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);

        out.print("Email sent successfully!");
    } catch (Exception e) {
        out.print("Failed to send email: " + e.getMessage());
        e.printStackTrace(); // Print the exception details for debugging purposes
    }
%>
