package com.example.restfulapi.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulapi.models.UserInput;
import com.example.restfulapi.helper.EmailSenderSevice;
import com.example.restfulapi.helper.UserHelper;
import com.example.restfulapi.helper.UserService;
import com.example.restfulapi.models.UserInfo;

@RestController
@RequestMapping("/user")
public class APIUser {
    @Autowired
    private EmailSenderSevice emailSenderSevice;

    @Autowired
    private UserService userService;

    @PostMapping("/mail")
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInput userInput) {
        try {
            String name = UserHelper.normalizeName(userInput.getName());
            int age = UserHelper.calculateAge(userInput.getDateOfBirth());
            String email = UserHelper.validateEmail(userInput.getEmail());
            String phoneRegion = UserHelper.phoneRegion(userInput.getPhoneNumber());
            String address = userInput.getAddress();
            UserInfo userInfo = new UserInfo(name, age, email, phoneRegion, address);
            UserHelper.createTxtFile(name, age, email, phoneRegion, address);
            ClassPathResource resource = new ClassPathResource("account_info.txt");
            String fileUrl = resource.getURL().toString();
            emailSenderSevice.sendMailWithAttachment(email, "This is email body", "This is subject", fileUrl);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public UserInput addUser(@RequestBody UserInput user) {
        return userService.addUserInput(user);
    }

    @PutMapping("update")
    public UserInput updateUser(@RequestParam("id") long id, @RequestBody UserInput user) {
        return userService.updateUserInput(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") long id) {
        return userService.deleteUserInput(id);
    }

    @GetMapping("/list")
    public List<UserInput> getAllUsers() {
        return userService.getAllUserInput();
    }
}
    
