package com.academy.mailserver;

import com.academy.mailserver.model.Email;
import com.academy.mailserver.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailServerApplication implements CommandLineRunner {

	@Autowired
	EmailService emailService;

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(MailServerApplication.class, args);
		System.out.println("http://localhost:9991/swagger-ui/");

	}

	@Override
	public void run(String... args) throws Exception {
		Email email = new Email();
		email.setCc("a@k.com");
		email.setRecipients("x@k.com");
		email.setSubject("hoi wassup");
		email.setSender("g@s.com");
		email.setBody("Dear Sir, Madam.....");

		emailService.save(email);
	}
}
