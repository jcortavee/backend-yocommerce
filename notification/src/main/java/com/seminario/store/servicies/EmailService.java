package com.seminario.store.servicies;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    public void sendEmail(String user, String toEmail, String subject) throws MessagingException, UnsupportedEncodingException;
}
