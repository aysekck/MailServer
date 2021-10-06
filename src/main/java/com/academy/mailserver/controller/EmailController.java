package com.academy.mailserver.controller;

import com.academy.mailserver.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import com.academy.mailserver.model.Email;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmailController {

    protected static final Logger logger = LogManager.getLogger();

    EmailService emailService;
    String version;
    
    @Autowired
    public EmailController(EmailService emailService, @Value("${version}") String version){
        this.emailService = emailService;
        this.version = version;
    }

    // Controller  Methode toevoegen (endpoint) die een resirect uitvoert
    // GET  die redirect naat https:// security.com:8443/api/email/getversion
    // Google: Spring Boot redirect

    @RequestMapping(value = "/redirect")
    public ResponseEntity<Void> redirect () {
        logger.info("inside 'redirect'");
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://security.com:8443/api/getversion")).build();

    }

    @GetMapping("/getversion")
    public ResponseEntity<String> getVersion(HttpServletRequest request, HttpServletResponse response) {

        logger.info("Inside 'getVersion'");
        String s = "No Cookies";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
             s = Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
            logger.info(s);
        }
        // create a cookie
        Cookie cookie = new Cookie("username", "Yusha");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        // Add cookie to response
        response.addCookie(cookie);

        return ResponseEntity.ok( version + "- Cookies: " +s);

    }

    @PutMapping(value = "/email", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> saveEmail(@RequestBody final Email email) {




        emailService.save(email);
       //return ResponseEntity.ok("Ok!") ;
        return new ResponseEntity("{ \"status\": \"Ok!\"}", HttpStatus.CREATED);

    }

    @GetMapping(value = "/email/{id}", produces = "application/json")
    public ResponseEntity<Email> getEmailById(@PathVariable final Long id) {

        logger.info("Inside 'getEmailById'");

        try {
            Email email = emailService.findById(id).orElse(null);
            return ResponseEntity.ok( email);
        } catch (Exception e) {
            return ResponseEntity.ok( null);
        }

    }

    @GetMapping(value = "/email", produces = "application/json")
    public ResponseEntity<Iterable<Email>> getAllEmails() {

        logger.info("Inside 'getAllEmails'");

        try {
            Iterable<Email> emails = emailService.findAll();
            return ResponseEntity.ok( emails);
        } catch (Exception e) {
            return ResponseEntity.ok( null); // Collections.emptyList()
        }

    }

    @DeleteMapping(value = "/email/{email}", produces = "text/plain")
    public ResponseEntity<String> deleteEmailById( @PathVariable final Long id) {

        logger.info("Inside 'deleteEmailById'");

        try {
            emailService.deleteById(id);
            return ResponseEntity.ok( "Item with id: " + id + " is deleted");
        } catch (Exception e) {
            return ResponseEntity.ok( "Item with id: " + id + " may NOT be deleted");
        }

    }


}

