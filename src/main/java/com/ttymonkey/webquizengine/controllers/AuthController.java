package com.ttymonkey.webquizengine.controllers;

import com.ttymonkey.webquizengine.models.dto.User;
import com.ttymonkey.webquizengine.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final UserService service;

    @Autowired
    public AuthController(
        UserService service
    ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody User user) {
        try {
            System.out.printf("%s %s%n",user.getEmail(), user.getPassword());
            service.create(user);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
