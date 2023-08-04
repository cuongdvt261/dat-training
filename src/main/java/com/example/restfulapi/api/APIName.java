package com.example.restfulapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.restfulapi.helper.NameHelper;
import com.example.restfulapi.models.NameInput;

@Controller
public class APIName {
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createName(@RequestBody NameInput nameInput) {
        try {
            String firstName = nameInput.getFirstName();
            String midName = nameInput.getMidName();
            String lastName = nameInput.getLastName();
            if (!NameHelper.isValidName(firstName) || !NameHelper.isValidName(midName) || !NameHelper.isValidName(lastName)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            String fullName = NameHelper.formatName(firstName, midName, lastName);

            return ResponseEntity.ok(fullName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
