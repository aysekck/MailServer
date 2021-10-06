package com.academy.mailserver;

import com.academy.mailserver.model.Email;
import com.academy.mailserver.service.EmailSendService;
import com.academy.mailserver.util.Util;

import java.util.ArrayList;
import java.util.List;

public class StreamApplication {

    public static void main(String[] args) {

        // Our Singleton EmailSendService
        EmailSendService emailSendService = EmailSendService.getInstance();

        List<Email> emails = new ArrayList<>();

        for(int i = 0; i < 200; i++) {
            emails.add(Util.getFakeEmail());
        }

        long start = System.currentTimeMillis();

        // Send email to the backend using a Stream of Emails
        emails.stream()
                .peek( x -> System.out.println( x.getRecipients()))
                .forEach( x -> emailSendService.sendEmail(x));

        System.out.println( "Processing stream took: " + ( System.currentTimeMillis() - start) + " millisecs");

    }
}
