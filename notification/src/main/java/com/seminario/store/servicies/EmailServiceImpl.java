package com.seminario.store.servicies;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {

    private final Environment environment;
    private final JavaMailSender mailSender;
    private static final String SPRING_LOGO_IMAGE = "templates/images/spring.png";
    private final TemplateEngine htmlTemplateEngine;
    private static final String TEMPLATE_NAME = "registration";
    private static final String PNG_MIME = "image/png";

    public EmailServiceImpl(Environment environment, JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
        this.environment = environment;
        this.mailSender = mailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    public void sendEmail(String user, String toEmail, String subject) throws MessagingException, UnsupportedEncodingException {
        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper email;

        email = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        email.setTo(toEmail);
        email.setSubject(subject);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        final Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", "jccortavee@gmail.com");
        ctx.setVariable("name", user);
        ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);

        final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);
        email.setText(htmlContent, true);

        ClassPathResource clr = new ClassPathResource(SPRING_LOGO_IMAGE);
        email.addInline("springLogo", clr, PNG_MIME);
        mailSender.send(mimeMessage);
    }

}
