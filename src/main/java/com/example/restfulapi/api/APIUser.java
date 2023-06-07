package com.example.restfulapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.restfulapi.models.UserInput;
import com.example.restfulapi.models.UserInfo;

@Controller
public class APIUser {
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInput userInput) {
        try {
            String name = UserHelper.normalizeName(userInput.getName());
            int age = UserHelper.calculateAge(userInput.getDateOfBirth());
            String email = UserHelper.validateEmail(userInput.getEmail());
            String phoneRegion = UserHelper.phoneRegion(userInput.getPhoneNumber());
            String address = userInput.getAddress();
            UserInfo userInfo = new UserInfo(name, age, email, phoneRegion, address);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
