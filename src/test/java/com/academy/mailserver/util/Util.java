package com.academy.mailserver.util;

import com.academy.mailserver.model.Email;
import com.github.javafaker.Faker;

public class Util {

    private static Faker faker = new Faker();

    public static Email getFakeEmail(){
        Email email = new Email();
        email.setSubject("Memo");
        email.setSender("no-reply@capgemini.com");
        email.setRecipients(Util.getFakeEmailAddress());
        email.setCc(Util.getFakeEmailAddress());
        email.setBody("Dear Madam,Sir......\n Regards, The team");
        return email;
    }

    public static String getFakeEmailAddress(){

        String email = faker.name().firstName() +
                "." +
                faker.name().lastName() +
                "@capgemini.com";

        return email.toLowerCase();

    }

}
