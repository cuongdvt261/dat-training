package com.example.restfulapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restfulapi.models.Users;
import com.example.restfulapi.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users addUserInput(Users userInput){
        if(userInput != null) {
            return userRepository.save(userInput);
        }
        return null;
    }

    public Users updateUserInput(long id, Users userInput) {
        Optional<Users> optionalUserInput = userRepository.findById(id);
        if(optionalUserInput.isPresent()) {
            Users userInput1 = optionalUserInput.get();
            userInput1.setName(userInput.getName());
            userInput1.setAddress(userInput.getAddress());
            userInput1.setDateOfBirth(userInput.getDateOfBirth());
            userInput1.setEmail(userInput.getEmail());
            userInput1.setPhoneNumber(userInput.getPhoneNumber());

            return userRepository.save(userInput1);
        }
        return null;
    }

    public boolean deleteUserInput(long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Users> getAllUserInput() {
        return userRepository.findAll();
    }

    public Users getOneUserInput(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
