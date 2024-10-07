package com.ccsd.KAretail.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public User updateUser(String id, User userDetails) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {

            User user = userOpt.get();
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setFullname(userDetails.getFullname());
            user.setPhoneNo(user.getPhoneNo());
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }
        return null;
    }
    
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
    // public Customer register(Customer customer) {
    //     if(customerRepository.findById(customer.getEmail()) != null) {
    //         throw new RuntimeException("Email already exists");
    //     }
    //     customer.setPassword(customer.getPassword());
    //     return customerRepository.save(customer);
    // }
}
