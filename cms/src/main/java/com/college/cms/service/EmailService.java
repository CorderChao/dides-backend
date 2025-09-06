package com.college.cms.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    private final JavaMailSender mailSender;
    
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void sendApplicationConfirmation(String toEmail, String applicantName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Application Submission Confirmation - DDES");
        message.setText("Dear " + applicantName + ",\n\n" +
                "Your application to Dodoma Institute of Development and Entrepreneurship Studies (DIDES) " +
                "has been received successfully.\n\n" +
                "We will review your application and contact you shortly.\n\n" +
                "Thank you for choosing DIDES!\n\n" +
                "Best regards,\n" +
                "Admissions Office\n" +
                "Dodoma Institute of Development and Entrepreneurship Studies");
        
        mailSender.send(message);
    }
}