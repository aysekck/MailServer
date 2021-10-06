package com.academy.mailserver.service;

import com.academy.mailserver.repository.EmailRepository;
import com.academy.mailserver.model.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {

    EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository){
      this.emailRepository = emailRepository;
    }

    @Override
    public Email save(Email email){
        return emailRepository.save(email);
    }

    @Override
    public Iterable<Email> saveAll(Iterable<Email> emails){
        return emailRepository.saveAll(emails);
    }

    @Override
    public Optional<Email> findById(Long id){
        return emailRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id){
        return emailRepository.existsById(id);
    }

    @Override
    public Iterable<Email> findAll(){
        return emailRepository.findAll();
    }

    @Override
    public Iterable<Email> findAllById(Iterable<Long> ids){
        return emailRepository.findAllById(ids);
    }

    @Override
    public long count(){
        return emailRepository.count();
    }

    @Override
    public void deleteById(Long id){
        emailRepository.deleteById(id);
    }

    @Override
    public void delete(Email email){
        emailRepository.delete(email);
    }

    @Override
    public void deleteAll(Iterable<? extends Email> emails){
        emailRepository.deleteAll(emails);
    }

    @Override
    public void deleteAll(){
        emailRepository.deleteAll();
    }

}

