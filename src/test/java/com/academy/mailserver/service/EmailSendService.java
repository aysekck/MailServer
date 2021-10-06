package com.academy.mailserver.service;

// This is a Singleton

import com.academy.mailserver.client.EmailServerClient;
import com.academy.mailserver.model.Email;

public class EmailSendService {

    private static EmailSendService emailSendService;

    private EmailSendService(){
    }

    public static EmailSendService getInstance(){
        if(emailSendService == null){
            emailSendService = new EmailSendService();
        }
        return emailSendService;
    }

    public String sendEmail(Email email){

        EmailServerClient emailServerClient = EmailServerClient.getInstance();

        String status = emailServerClient.sendEmail(email);

        return status;

    }

}
