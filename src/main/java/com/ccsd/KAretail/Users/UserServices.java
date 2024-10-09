package com.ccsd.KAretail.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.init.UnmarshallerRepositoryPopulatorFactoryBean;
import org.springframework.stereotype.Service;


@Service
public class UserServices {
     
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id){
        return userRepository.findById(id);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User registerUser(User user){
        user.setRole(2);
        return userRepository.save(user);
    }

    public User registrerUser(User user){
        if (userRepository.findByUsername(user.getUsername()) != null){
            throw  new RuntimeException("Username already taken");
        }
        return userRepository.save(user);
    }

    public User updateUser(String id, User userDetails) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {

            User user = userOpt.get();
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFullname(userDetails.getFullname());
            user.setPhoneNo(userDetails.getPhoneNo());
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }
        return null;
    }
    
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
