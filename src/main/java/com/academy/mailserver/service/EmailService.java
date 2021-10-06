package com.academy.mailserver.service;

import com.academy.mailserver.model.Email;

import java.util.Optional;

public interface EmailService {

    public Email save(Email email);

    public Iterable<Email> saveAll(Iterable<Email> emails);

    public Optional<Email> findById(Long id);

    public boolean existsById(Long id);

    public Iterable<Email> findAll();

    public Iterable<Email> findAllById(Iterable<Long> ids);

    public long count();

    public void deleteById(Long id);

    public void delete(Email email);

    public void deleteAll(Iterable<? extends Email> emails);

    public void deleteAll();

}

