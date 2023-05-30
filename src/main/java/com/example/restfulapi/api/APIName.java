package com.example.restfulapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.restfulapi.dto.NameInput;

@Controller
public class APIName {
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createName(@RequestBody NameInput nameInput) {
        String firstName = nameInput.getFirstName();
        String midName = nameInput.getMidName();
        String lastName = nameInput.getLastName();

       if(!isValidName(firstName) || !isValidName(midName) || !isValidName(lastName)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
       String fullName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase()
    + " " + midName.substring(0, 1).toUpperCase() + midName.substring(1).toLowerCase()
    + " " + firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        return ResponseEntity.ok(fullName);
    }
     public boolean isValidName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
