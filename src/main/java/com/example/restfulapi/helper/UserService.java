package com.example.restfulapi.helper;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restfulapi.models.UserInput;
import com.example.restfulapi.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserInput addUserInput(UserInput userInput){
        if(userInput != null) {
            return userRepository.save(userInput);
        }
        return null;
    }

    public UserInput updateUserInput(long id, UserInput userInput) {
        Optional<UserInput> optionalUserInput = userRepository.findById(id);
        if(optionalUserInput.isPresent()) {
            UserInput userInput1 = optionalUserInput.get();
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

    public List<UserInput> getAllUserInput() {
        return userRepository.findAll();
    }

    public UserInput getOneUserInput(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
