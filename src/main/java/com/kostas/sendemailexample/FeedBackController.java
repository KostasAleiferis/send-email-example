package com.kostas.sendemailexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
public class FeedBackController {

    @Autowired
    EmailConfig emailConfig;

    public FeedBackController(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    @GetMapping("/")
    public String getHome() {
        return "Welcome";
    }

    @PostMapping("/feedback")
    public void sendFeedback(@RequestBody FeedBack feedBack,
                             BindingResult bindingResult) throws ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback is not Valid");
        }

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedBack.getEmail());
        mailMessage.setTo("rc@feedback.com");
        mailMessage.setSubject("New Feedback from :" + feedBack.getName());
        mailMessage.setText(feedBack.getFeedback());

        mailSender.send(mailMessage);

    }
}
