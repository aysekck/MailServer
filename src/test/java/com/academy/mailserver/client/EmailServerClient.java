package com.academy.mailserver.client;


// This is a Singleton class

import com.academy.mailserver.model.Email;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class EmailServerClient {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();
    private final String URL = "http://localhost:9991/api/email";
    private static EmailServerClient emailClient;

    private EmailServerClient(){
    }

    public static EmailServerClient getInstance(){

        if( emailClient == null){
            emailClient = new EmailServerClient();
        }

        return emailClient;

    }

    public String sendEmail(Email email){

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Email> entity = new HttpEntity<>(email, headers);

        // send POST request
//        ResponseEntity<String> response = this.restTemplate.putForEntity(URL, entity, String.class);
        // send PUT request
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);

        // check response status code
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            return null;
        }

    }

}
